/*
 * Title: Java Singleton Pattern
 * Difficulty: Easy
 * Link: https://www.hackerrank.com/challenges/java-singleton/problem
 *
 * "The singleton pattern is a design pattern that restricts the instantiation of a class to one object. This is useful when exactly one object is needed to coordinate actions across the system." 
 *
 *   - Wikipedia: Singleton Pattern
 *
 * Complete the Singleton class in your editor which contains the following components:  
 *
 * - A private Singleton non parameterized constructor.
 *
 * - A public String instance variable named . 
 *
 * - Write a static method named getSingleInstance that returns the single instance of the Singleton class.
 *
 * Once submitted, our hidden Solution class will check your code by taking a String as input and then using your Singleton class to print a line.
 *
 * Input Format
 *
 * You will not be handling any input in this challenge. 
 *
 * Output Format
 *
 * You will not be producing any output in this challenge.
 *
 * Sample Input
 *
 * hello world
 *
 * Sample Output
 *
 * Hello I am a singleton! Let me say hello world to you
 */

import java.io.*;

public class Solution {
    // HackerRank's hidden Solution class does this; added here so the file runs locally.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        Singleton.getSingleInstance().str = str;
        System.out.println("Hello I am a singleton! Let me say " + Singleton.getSingleInstance().str + " to you");
    }
}

// ---- YOUR PART ----
class Singleton {
    private static final Singleton instance = new Singleton();

    public String str;

    private Singleton() {
    }

    public static Singleton getSingleInstance() {
        return instance;
    }
}
// ---- END ----
