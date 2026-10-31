/*
 * Title: Java Generics
 * Difficulty: Easy
 * Link: https://www.hackerrank.com/challenges/java-generics/problem
 *
 * Generic methods are a very efficient way to handle multiple datatypes using a single method. This problem will test your knowledge on Java Generic methods.
 *
 * Let's say you have an integer array and a string array. You have to write a single method printArray that can print all the elements of both arrays. The method should be able to accept both integer arrays or string arrays.
 *
 * You are given code in the editor. Complete the code so that it prints the following lines:
 *
 * 1
 * 2
 * 3
 * Hello
 * World
 *
 * Do not use method overloading because your answer will not be accepted.
 */

public class Solution {
    public static void main(String[] args) {
        Printer myPrinter = new Printer();
        Integer[] intArray = {1, 2, 3};
        String[] stringArray = {"Hello", "World"};
        myPrinter.printArray(intArray);
        myPrinter.printArray(stringArray);
    }
}

class Printer {
    // ---- YOUR PART ---- one generic method (no overloading)
    public <T> void printArray(T[] array) {
        for (T element : array) {
            System.out.println(element);
        }
    }
    // ---- END ----
}
