package day01;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SampleTest {

    @Test
    public void calculatorTest(){

        System.out.println("Hello World");

        // Assert 4 + 5 = 9
        assertEquals(9, 4 + 5);

        // how do we add error message if the assertion is failed
       // assertEquals(10, 5 + 4, "Wrong result!!!");



    }

    // you can add the display name for your test instead of the method name
    // providing the custom name for the test
    @DisplayName("I am testing the name")
    @Test
    public void nameTest(){

        // WRITE A SIMPLE ASSERTION
        String firstName = "Aysa";
        String lastName = "Dzhalaeva";

        String fullName = firstName + " " + lastName;
        assertEquals("Aysa Dzhalaeva", fullName);
        System.out.println("Success");

    }

}
