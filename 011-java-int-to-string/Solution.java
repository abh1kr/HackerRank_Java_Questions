/*
 * Title: Java Int to String
 * Difficulty: Easy
 * Link: https://www.hackerrank.com/challenges/java-int-to-string/problem
 *
 * You are given an integer , you have to convert it into a string.
 *
 * Please complete the partially completed code in the editor. If your code successfully converts  into a string  the code will print "Good job". Otherwise it will print "Wrong answer".
 *
 *  can range between  to  inclusive.
 *
 * Sample Input 0
 *
 * 100
 *
 * Sample Output 0
 *
 * Good job
 */

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.close();

        // ---- YOUR PART (HackerRank: "String s=???") ----
        String s = Integer.toString(n);
        // ---- END ----

        if (n == Integer.parseInt(s)) {
            System.out.println("Good job");
        } else {
            System.out.println("Wrong answer.");
        }
    }
}
