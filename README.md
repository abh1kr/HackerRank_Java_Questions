# HackerRank Java Scraper

`hr_scraper.py` downloads the questions from HackerRank's **Java** track and turns each one into its own folder with a ready-to-edit `Solution.java`. The question (title, difficulty, link and full statement) is written at the top of the file as a comment, so you read the problem and write your code in the same file.

## What you get

```
hackerrank-java/
├── 001-welcome-to-java/
│   └── Solution.java
├── 002-java-stdin-and-stdout-i/
│   └── Solution.java
├── 003-java-if-else/
│   └── Solution.java
└── ...
```

Each `Solution.java` looks like this:

```java
/*
 * Title: Java If-Else
 * Difficulty: Easy
 * Link: https://www.hackerrank.com/challenges/java-if-else/problem
 *
 * <the full question statement as plain text>
 */

import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // write your solution here

        sc.close();
    }
}
```

- **Folder name:** `NNN-question-name` (number + question title).
- **File name:** always `Solution.java`. HackerRank uses `class Solution`, so your code can be pasted back into the site unchanged.
- **Question as comments:** the statement is plain text inside the header comment.

## Requirements

- Python 3.8 or newer
- The `requests` library
- A JDK (not just a JRE) if you want to compile and run the solutions

```powershell
pip install requests
```

## Usage

Run it from the folder where you want the output folder to be created:

```powershell
python hr_scraper.py
```

That scrapes every difficulty into a folder called `hackerrank-java`.

### Options

| Option | Default | What it does |
|---|---|---|
| `--out NAME` | `hackerrank-java` | Name of the output folder. |
| `--levels ...` | `Easy Medium Hard` | Which difficulties to include. Easy is the basic level, Medium is intermediate and Hard is advanced. |
| `--limit N` | `0` (no limit) | Only process the first N questions. |
| `--mode full` or `link` | `full` | `full` writes the whole statement into the comment. `link` writes only the title, difficulty and URL. |
| `--cookie "..."` | none | Sends a browser cookie with every request. Only needed if you get HTTP 403 errors. |

### Examples

```powershell
# basic + intermediate only
python hr_scraper.py --levels Easy Medium

# everything, into a custom folder
python hr_scraper.py --levels Easy Medium Hard --out my-hackerrank-repo

# try it on 5 questions first
python hr_scraper.py --limit 5

# link-only comments (no statement text)
python hr_scraper.py --mode link
```

## How it works

1. It reads the list of Java challenges from HackerRank's public endpoint `https://www.hackerrank.com/rest/contests/master/tracks/java/challenges`.
2. It keeps only the difficulties you asked for.
3. For each question it downloads the details from `https://www.hackerrank.com/rest/contests/master/challenges/<slug>` and converts the HTML statement to plain text.
4. It creates the numbered folder and writes `Solution.java`.

It waits a short time between requests so it doesn't hammer the site.

### Safe to re-run

If a folder already has a `Solution.java`, that question is skipped, so **your solutions are never overwritten**.

> **Keep the same options on every run.** Folder numbers come from the position in the filtered list. If you re-run with different `--levels` or `--limit`, the numbers shift and you can end up with duplicate folders for the same question. Pick your options once and stay with them.

## If you get HTTP 403 (blocked)

HackerRank sometimes blocks scripts. If that happens:

1. Open hackerrank.com in your browser and log in.
2. Press F12, open the **Network** tab and refresh the page.
3. Click the first request (`java`), then open **Headers**, then **Request Headers**.
4. Copy the value of the `Cookie` line.
5. Run the script with it in **single quotes**:

```powershell
python hr_scraper.py --cookie 'paste_value_here'
```

**Treat the cookie like a password.** It contains your login session. Don't paste it into chats, don't save it in a file, and don't commit it. If it ever leaks, log out of HackerRank and log back in to invalidate it. Cookies containing double quotes break PowerShell quoting, so pass a single cookie value (such as `cf_clearance=...`) rather than the whole string.


## Known limitations

- **Missing math in statements.** HackerRank draws math (such as `n`, `1 ≤ n ≤ 100`) with MathJax, and the script's plain-text conversion drops it. You may see sentences like "Given an integer, , perform..." where the variable is missing. Check the original problem page when a constraint matters.
- **CSS noise.** Some statements start with leftover MathJax style lines (`.MathJax_SVG ...`) inside the comment. They are harmless and can be deleted.
- **Locked starter code is not included.** Many questions give you hidden starter code on HackerRank. The scraper only creates a generic empty `main`, so you may need to add the missing methods or classes yourself.
- **Public questions only.** It uses public endpoints and cannot fetch private or contest questions.
- **Website changes.** If HackerRank changes its endpoints or field names, the script may stop working.

## Copyright and terms

The question statements belong to HackerRank. Keep your repository **private**, or use `--mode link` so only titles and links are stored, and check HackerRank's terms of service before sharing scraped content.
