/*
 * Title: Java Date and Time
 * Difficulty: Easy
 * Link: https://www.hackerrank.com/challenges/java-date-and-time/problem
 *
 * The Calendar class is an abstract class that provides methods for converting between a specific instant in time and a set of calendar fields such as YEAR, MONTH, DAY_OF_MONTH, HOUR, and so on, and for manipulating the calendar fields, such as getting the date of the next week.
 *
 * You are given a date. You just need to write the method, , which returns the day on that date.  To simplify your task, we have provided a portion of the code in the editor. 
 *
 * Example 
 *
 * The method should return  as the day on that date. 
 *
 * Function Description   
 *
 * Complete the findDay function in the editor below.   
 *
 * findDay has the following parameters:   
 *
 * - int: month   
 *
 * - int: day   
 *
 * - int: year   
 *
 * Returns   
 *
 * - string: the day of the week in capital letters   
 *
 * Input Format
 *
 * A single line of input containing the space separated month, day and year, respectively, in    format.
 *
 * Constraints
 *
 * - 
 *
 * Sample Input
 *
 * 08 05 2015
 *
 * Sample Output
 *
 * WEDNESDAY
 *
 * Explanation
 *
 * The day on August th  was WEDNESDAY.
 */

import java.time.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int month = sc.nextInt();
        int day = sc.nextInt();
        int year = sc.nextInt();
        sc.close();
        System.out.println(Result.findDay(month, day, year));
    }
}

class Result {
    // ---- YOUR PART ----
    public static String findDay(int month, int day, int year) {
        return LocalDate.of(year, month, day).getDayOfWeek().toString(); // e.g. WEDNESDAY
    }
    // ---- END ----
}
