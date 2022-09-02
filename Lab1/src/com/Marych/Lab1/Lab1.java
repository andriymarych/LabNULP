package com.Marych.Lab1;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Lab1 class is used for executing the first laboratory work on applied programming
 * @author Andriy Marych
 * @version 1.0 2022-08-30
 */
public class Lab1 {
    /**
     * Default constructor of the Lab1 class
     */
    Lab1(){

    }
    /**
     * This is the main() method, which is the entry point for executing a Java program
     * @param args contains the supplied command-line arguments for the Java program
     */

    public static void main(String[] args) {
        ArrayList<Fibonacci> arrayList = new ArrayList<>();
        int N = 0;
        System.out.println("Choose an input option :\n 0.Console Input \n 1.Command Line");
        Scanner in = new Scanner(System.in);
        byte readMode = in.nextByte();
        if(readMode == 0){
            System.out.println("Enter the sequence number of the first Fibonacci numbers");
            N = in.nextInt();
        } else if (readMode == 1) {
            N = Integer.parseInt(args[0]);
        }else{
            System.out.println("You have selected an incorrect input option.\nEnd of program...");
            System.exit(0);
        }
        for(int i = 1; i <= N; i++){
            arrayList.add(new Fibonacci(i));
        }
        System.out.println("-".repeat(60));
        System.out.println("Entered N:" + N);
        System.out.println("The first " + N + " Fibonacci numbers:");
        System.out.println(FibonacciNumbers.toString(arrayList));
        FibonacciNumbers.checkForm(arrayList);
        System.out.println("-".repeat(60));
    }

}
