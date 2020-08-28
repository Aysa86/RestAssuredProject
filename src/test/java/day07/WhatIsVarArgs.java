package day07;

import org.junit.jupiter.api.Test;

public class WhatIsVarArgs {

    // variable number of arguments
    // create a method that accepts N numbers and add all of them
    // and print the result

    public static void addAllNumbers(int[] nums){

        int sum = 0;
        for (int eachNum : nums) {
            sum+=eachNum;
        }
        System.out.println("sum = " + sum);
    }

    @Test
    public void testAdd(){
        addAllNumbers(new int[]{1,2,3,4,5}); // sum = 15
        addAllNumbersVarArgs(1,2,3,4,5,5); // sum = 20
    }


    // this method parameter int ... nums means
    // when you call the method, it can accept any number of arguments
    // this is the only place you can use ...
    // anywhere else other than method param it will not work

    public static void addAllNumbersVarArgs(int... nums){

        int sum = 0;
        for (int eachNum : nums) {
            sum+=eachNum;
        }
        System.out.println("sum = " + sum);
    }



}
