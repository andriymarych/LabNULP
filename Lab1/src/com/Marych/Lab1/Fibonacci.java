package com.Marych.Lab1;

/**
 * The Fibonacci class is used to store the N-th Fibonacci number
 * @author Andriy Marych CS-207
 * @version 1.0 2022-08-30
 */
public class Fibonacci {
    private final int N;
    private final long fibonacciValue;

    /**
     * Constructs a new Fibonacci object with the value of the Fibonacci number at N position
     *
     * @param N ordinal number of the Fibonacci number
     */
    public Fibonacci(int N){
        this.N = N;
        long firstNumber = 0, actualNumber , secondNumber = 1;
        while(N > 0){
            actualNumber = firstNumber + secondNumber;
            firstNumber = secondNumber;
            secondNumber = actualNumber;
            N--;
        }
        fibonacciValue = firstNumber;
    }

    /**
     * Returns ordinal number of the Fibonacci number(N)
     * @return ordinal number of the Fibonacci number(N)
     */
    public int getN() {
        return N;
    }

    /**
     * Returns the value of the Fibonacci number at position N
     * @return the value of the Fibonacci number at position N
     */
    public long getValue(){
        return fibonacciValue;
    }

    /**
     * Displays information about the Fibonacci number
     * (N (ordinal number), the value of the N Fibonacci number)
     */
    public void display(){
        if(N != 0) {
            System.out.println("The value of the "+ N + " Fibonacci number - " + fibonacciValue);
        }else
            System.out.println("NULL");
    }

    public String toString(){
        return String.valueOf(fibonacciValue);
    }
}
