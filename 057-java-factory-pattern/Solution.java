/*
 * Title: Java Factory Pattern
 * Difficulty: Easy
 * Link: https://www.hackerrank.com/challenges/java-factory/problem
 *
 * According to Wikipedia, a factory is simply an object that returns another object from some other method call, which is assumed to be "new".
 *
 * In this problem, you are given an interface Food. There are two classes Pizza and Cake which implement the Food interface, and they both contain a method getType().
 *
 * The main function in the Main class creates an instance of the FoodFactory class. The FoodFactory class contains a method getFood(String) that returns a new instance of Pizza or Cake according to its parameter.
 *
 * You are given the partially completed code in the editor. Please complete the FoodFactory class.
 *
 * Sample Input 1
 *
 * cake
 *
 * Sample Output 1
 *
 * The factory returned class Cake
 * Someone ordered a Dessert!
 *
 * Sample Input 2
 *
 * pizza
 *
 * Sample Output 2
 *
 * The factory returned class Pizza
 * Someone ordered Fast Food!
 */

import java.io.*;

// NOTE: on HackerRank this driver class is called "Main"; here it is "Solution" so the file compiles.
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        FoodFactory foodFactory = new FoodFactory();
        Food food = foodFactory.getFood(s);

        System.out.println("The factory returned " + food.getClass());
        System.out.println(food.getType());
    }
}

interface Food {
    String getType();
}

class Pizza implements Food {
    public String getType() {
        return "Someone ordered Fast Food!";
    }
}

class Cake implements Food {
    public String getType() {
        return "Someone ordered a Dessert!";
    }
}

// ---- YOUR PART ----
class FoodFactory {
    public Food getFood(String order) {
        if (order.trim().equalsIgnoreCase("cake")) {
            return new Cake();
        } else if (order.trim().equalsIgnoreCase("pizza")) {
            return new Pizza();
        }
        return null;
    }
}
// ---- END ----
