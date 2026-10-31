/*
 * Title: Java String Reverse
 * Difficulty: Easy
 * Link: https://www.hackerrank.com/challenges/java-string-reverse/problem
 *
 * A palindrome is a word, phrase, number, or other sequence of characters which reads the same backward or forward.  
 *
 * Given a string , print Yes if it is a palindrome, print No otherwise. 
 *
 * Constraints
 *
 * -  will consist at most  lower case english letters.
 *
 * Sample Input
 *
 * madam
 *
 * Sample Output
 *
 * Yes
 */

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String A = sc.next();
        sc.close();

        String reversed = new StringBuilder(A).reverse().toString();
        System.out.println(A.equals(reversed) ? "Yes" : "No");
    }
}
