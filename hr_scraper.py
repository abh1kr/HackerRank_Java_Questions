#!/usr/bin/env python3
"""
HackerRank Java scraper -> one folder per question, each with Solution.java
(problem statement as a comment + boilerplate), ready to git push.

Usage:
    pip install requests
    python hr_scraper.py                      # all difficulties
    python hr_scraper.py --levels Easy Medium # basic + intermediate only
    python hr_scraper.py --out my-repo --limit 30

Notes:
- Uses HackerRank's public JSON endpoints (no login needed for public challenges).
- If you get HTTP 403 (Cloudflare), open hackerrank.com in your browser, copy the
  'Cookie' header from DevTools > Network, and pass it: --cookie "paste here"
- Problem statements belong to HackerRank. Keep the repo private, or switch
  --mode link to store only title + URL instead of the full statement.
"""
import argparse
import html
import os
import re
import textwrap
import time
from html.parser import HTMLParser

import requests

BASE = "https://www.hackerrank.com/rest/contests/master"
LEVEL_MAP = {"easy": "Easy", "medium": "Medium", "hard": "Hard"}


class HTMLToText(HTMLParser):
    BLOCK = {"p", "div", "br", "li", "h1", "h2", "h3", "h4", "pre", "tr"}

    def __init__(self):
        super().__init__()
        self.out = []

    def handle_starttag(self, tag, attrs):
        if tag in self.BLOCK:
            self.out.append("\n")
        if tag == "li":
            self.out.append("- ")

    def handle_endtag(self, tag):
        if tag in self.BLOCK:
            self.out.append("\n")

    def handle_data(self, data):
        self.out.append(data)

    def text(self):
        t = html.unescape("".join(self.out))
        t = t.replace("*/", "* /")  # never close the Java comment early
        return re.sub(r"\n{3,}", "\n\n", t).strip()


def html_to_text(s):
    p = HTMLToText()
    p.feed(s or "")
    return p.text()


def slugify(s):
    return re.sub(r"[^a-z0-9]+", "-", s.lower()).strip("-")


def get_json(session, url, retries=3):
    for i in range(retries):
        r = session.get(url, timeout=30)
        if r.status_code == 200:
            return r.json()
        if r.status_code in (403, 429):
            print(f"  HTTP {r.status_code} - waiting, then retrying "
                  f"({i + 1}/{retries})")
            time.sleep(5 * (i + 1))
            continue
        r.raise_for_status()
    raise RuntimeError(f"Failed: {url} (blocked? try --cookie)")


def list_challenges(session, track="java"):
    items, offset = [], 0
    while True:
        data = get_json(
            session,
            f"{BASE}/tracks/{track}/challenges?offset={offset}&limit=50",
        )
        models = data.get("models", [])
        if not models:
            break
        items.extend(models)
        offset += len(models)
        if offset >= data.get("total", 0):
            break
        time.sleep(0.5)
    return items


def build_java(name, slug, level, body_text, mode):
    url = f"https://www.hackerrank.com/challenges/{slug}/problem"
    header = [f"Title: {name}", f"Difficulty: {level}", f"Link: {url}"]
    if mode == "full":
        header += ["", body_text]
    comment = "\n".join(
        " * " + line if line else " *"
        for line in "\n".join(header).splitlines()
    )
    return f"""/*
{comment}
 */

import java.io.*;
import java.util.*;

public class Solution {{
    public static void main(String[] args) {{
        Scanner sc = new Scanner(System.in);
        // write your solution here

        sc.close();
    }}
}}
"""


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("--out", default="hackerrank-java")
    ap.add_argument("--levels", nargs="*", default=["Easy", "Medium", "Hard"])
    ap.add_argument("--limit", type=int, default=0, help="0 = no limit")
    ap.add_argument("--mode", choices=["full", "link"], default="full")
    ap.add_argument("--cookie", default="")
    args = ap.parse_args()

    wanted = {LEVEL_MAP.get(l.lower(), l) for l in args.levels}

    s = requests.Session()
    s.headers.update({
        "User-Agent": "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 "
                      "(KHTML, like Gecko) Chrome/124.0 Safari/537.36",
        "Accept": "application/json",
    })
    if args.cookie:
        s.headers["Cookie"] = args.cookie

    print("Fetching challenge list...")
    challenges = list_challenges(s)
    challenges = [c for c in challenges
                  if c.get("difficulty_name", "Easy") in wanted]
    if args.limit:
        challenges = challenges[:args.limit]
    print(f"{len(challenges)} challenges to process")

    os.makedirs(args.out, exist_ok=True)
    for n, c in enumerate(challenges, 1):
        slug, name = c["slug"], c["name"]
        level = c.get("difficulty_name", "Unknown")
        folder = os.path.join(args.out, f"{n:03d}-{slugify(name)}")
        java_path = os.path.join(folder, "Solution.java")
        if os.path.exists(java_path):  # never overwrite your solutions
            print(f"[{n}] skip (exists): {name}")
            continue
        try:
            body = ""
            if args.mode == "full":
                d = get_json(s, f"{BASE}/challenges/{slug}")["model"]
                body = html_to_text(d.get("body_html", ""))
                time.sleep(0.7)
            os.makedirs(folder, exist_ok=True)
            with open(java_path, "w", encoding="utf-8") as f:
                f.write(build_java(name, slug, level, body, args.mode))
            print(f"[{n}] {level:6} {name}")
        except Exception as e:
            print(f"[{n}] FAILED {name}: {e}")

    print(f"\nDone. cd {args.out} && git init && git add . && git commit -m 'init'")


if __name__ == "__main__":
    main()
