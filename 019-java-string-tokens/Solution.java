/*
 * Title: Java String Tokens
 * Difficulty: Easy
 * Link: https://www.hackerrank.com/challenges/java-string-tokens/problem
 *
 * Given a string, , matching the regular expression [A-Za-z !,?._'@]+, split the string into tokens. We define a token to be one or more consecutive English alphabetic letters. Then, print the number of tokens, followed by each token on a new line.
 *
 * Note: You may find the String.split method helpful in completing this challenge.
 *
 * Input Format
 *
 * A single string, .
 *
 * Constraints
 *
 * - 
 *
 * -  is composed of any of the following: English alphabetic letters, blank spaces, exclamation points (!), commas (,), question marks (?), periods (.), underscores (_), apostrophes ('), and at symbols (@).
 *
 * Output Format
 *
 * On the first line, print an integer, , denoting the number of tokens in string  (they do not need to be unique). Next, print each of the  tokens on a new line in the same order as they appear in input string .
 *
 * Sample Input
 *
 * He is a very very good boy, isn't he?
 *
 * Sample Output
 *
 * 10
 * He
 * is
 * a
 * very
 * very
 * good
 * boy
 * isn
 * t
 * he
 *
 * Explanation
 *
 * We consider a token to be a contiguous segment of alphabetic characters. There are a total of  such tokens in string , and each token is printed in the same order in which it appears in string .
 */

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        if (s == null) s = "";

        // a token = one or more consecutive English letters
        // (using find() avoids the empty first token that split() gives for leading punctuation)
        Matcher m = Pattern.compile("[A-Za-z]+").matcher(s);
        List<String> tokens = new ArrayList<>();
        while (m.find()) tokens.add(m.group());

        StringBuilder sb = new StringBuilder();
        sb.append(tokens.size()).append('\n');
        for (String t : tokens) sb.append(t).append('\n');
        System.out.print(sb);
    }
}
