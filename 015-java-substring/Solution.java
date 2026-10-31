/*
 * Title: Java Substring
 * Difficulty: Easy
 * Link: https://www.hackerrank.com/challenges/java-substring/problem
 *
 * Given a string, , and two indices,  and , print a substring consisting of all characters in the inclusive range from  to . You'll find the String class' substring method helpful in completing this challenge. 
 *
 * Input Format
 *
 * The first line contains a single string denoting . 
 *
 * The second line contains two space-separated integers denoting the respective values of  and .
 *
 * Constraints
 *
 * - 
 *
 * - 
 *
 * - String  consists of English alphabetic letters (i.e., ) only.
 *
 * Output Format
 *
 * Print the substring in the inclusive range from  to .
 *
 * Sample Input
 *
 * Helloworld
 * 3 7
 *
 * Sample Output
 *
 * lowo
 *
 * Explanation
 *
 * In the diagram below, the substring is highlighted in green:
 */

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        int start = in.nextInt();
        int end = in.nextInt();
        in.close();

        // substring's end index is exclusive, and the range here is start..end-1
        System.out.println(s.substring(start, end));
    }
}
