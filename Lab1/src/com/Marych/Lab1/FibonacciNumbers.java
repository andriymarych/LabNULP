package com.Marych.Lab1;

import java.util.ArrayList;

/**
 * The FibonacciNumbers class contains various methods for manipulating Fibonacci numbers
 * @author Andriy Marych
 * @version 1.0 2022-08-30
 * @see Fibonacci
 */
public class FibonacciNumbers {
    /**
     * Default constructor of the FibonacciNumbers class
     */
    FibonacciNumbers(){

    }

    /**
     * Determines whether among the first N numbers ,
     * there are such numbers that can be specified in the form (w^3 - 1)
     * @param fibonacciValueList ArrayList of Fibonacci objects
     */
    public static void checkForm(ArrayList<Fibonacci> fibonacciValueList){
        double w;
        boolean isPresent = false;
        ArrayList<Long> formList = new ArrayList<>();
        for (Fibonacci fibNumber : fibonacciValueList) {
            w = Math.cbrt(fibNumber.getValue() + 1);
            if (w % 1 == 0) {
                isPresent = true;
                formList.add(fibNumber.getValue());
            }
        }
        if(isPresent) {
            System.out.println("Fibonacci numbers that can be specified in the form (w3 - 1):");
            System.out.println(formList);
        }else
            System.out.println("Among the first "+ fibonacciValueList.size()+ " Fibonacci numbers, there are no such " +
                    "numbers that can be specified in the form (w^3 - 1)");
    }

    /**
     * Returns a string representation of the ArrayList of Fibonacci numbers
     * @param arrayList the ArrayList whose string representation of Fibonacci numbers to return
     * @return a string representation of Fibonacci arrayList
     */
    public static String toString(ArrayList<Fibonacci> arrayList) {
        if(arrayList.size() != 0) {
            return arrayList.toString();
        }
        return "NULL";
    }
}
