/*
 * Title: Java Method Overriding 2 (Super Keyword)
 * Difficulty: Easy
 * Link: https://www.hackerrank.com/challenges/java-method-overriding-2-super-keyword/problem
 *
 * When a method in a subclass overrides a method in superclass, it is still possible to call the overridden method using super keyword. If you write super.func() to call the function func(), it will call the method that was defined in the superclass.
 *
 * You are given a partially completed code in the editor. Modify the code so that the code prints the following text:
 *
 * Hello I am a motorcycle, I am a cycle with an engine.
 * My ancestor is a cycle who is a vehicle with pedals.
 */

public class Solution {
    public static void main(String[] args) {
        MotorCycle M = new MotorCycle();
    }
}

class BiCycle {
    String define_me() {
        return "a vehicle with pedals.";
    }
}

class MotorCycle extends BiCycle {
    String define_me() {
        return "a cycle with an engine.";
    }

    MotorCycle() {
        System.out.println("Hello I am a motorcycle, I am " + define_me());

        // ---- YOUR PART ---- super.define_me() calls the BiCycle version
        String temp = super.define_me();
        // ---- END ----

        System.out.println("My ancestor is a cycle who is " + temp);
    }
}
