package day11;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GettingRandomNumber {

    @Test
    public void testRandom(){

        // Random class comes from java.util package and can provide some random numbers in different types
        // first we need to create an object

        Random random = new Random();
        // this will give random number from 0-5
       int randomNumber = random.nextInt(5);

        List<String> names = Arrays.asList("Aysa", "Eugene", "Katya", "Vladimir", "Mummy");
        System.out.println("Lucky name: " + names.get(randomNumber));
        // get a random name from the list each time

        System.out.println("randomNumber = " + randomNumber);

    }



}
