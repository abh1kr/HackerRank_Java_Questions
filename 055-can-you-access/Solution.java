/*
 * Title: Can You Access?
 * Difficulty: Medium
 * Link: https://www.hackerrank.com/challenges/can-you-access/problem
 *
 * You are given a class Solution and an inner class Inner.Private. The main method of class Solution takes an integer  as input. The powerof2 in class Inner.Private checks whether a number is a power of . You have to call the method powerof2 of the class Inner.Private from the main method of the class Solution.
 *
 * Constraints 
 *
 * Sample Input
 *
 * 8
 *
 * Sample Output
 *
 * 8 is power of 2
 * An instance of class: Solution.Inner.Private has been created
 */

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = Integer.parseInt(sc.nextLine().trim());
        sc.close();

        // ---- YOUR PART ---- Private is a private inner class of Inner, so it needs an Inner object first
        Object o = new Inner().new Private();
        // ---- END ----

        System.out.println(num + " is " + ((Inner.Private) o).powerof2(num));
        System.out.println("An instance of class: " + o.getClass().getCanonicalName() + " has been created");
    }

    static class Inner {
        private class Private {
            private String powerof2(int num) {
                return ((num & num - 1) == 0) ? "power of 2" : "not a power of 2";
            }
        }
    }
}
