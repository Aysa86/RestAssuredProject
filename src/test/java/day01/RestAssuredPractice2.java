package day01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.matcher.RestAssuredMatchers.* ;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class RestAssuredPractice2 {
    @Test
    public void test1(){
        // assert 5+4 = 9
        int num1 = 5;
        int num2 = 4;

        // hamcrest library use the assertThat for all assertions
        // hamcrest use building matchers to do assertions
        // couple common ones :
        // is (value)
        // equalTo(value)
        // or optionally is(equalTo(some value))
        assertThat(num1 + num2, is(9));

        assertThat(num1 + num2, equalTo(9));

        assertThat(num1 + num2, is(equalTo(9)) );

        // not(value)
        // is( not( some value) )
        // not(equalTo(value))
        assertThat(num1 + num2, not(11));
        assertThat(num1 + num2,is(not(10)) );
        assertThat(num1 + num2, not(equalTo(12)) );

        String firstName = "AYSA";
        String lastName = "DZHALAEVA";

        assertThat(firstName + " " + lastName, is("AYSA DZHALAEVA"));
        assertThat(firstName + " " + lastName, equalTo("AYSA DZHALAEVA"));
        assertThat(firstName + " " + lastName, is(equalTo("AYSA DZHALAEVA")));

        // how to check in caseInsensitive manner
        assertThat(firstName, equalToIgnoringCase("aysa"));

        // how to ignore all white spaces
        assertThat(firstName, equalToCompressingWhiteSpace("AYSA"));

        // String matchers
        // equalToIgnoringCase
        // equalToCompressingWhiteSpace
        //containsString, endsWith, startsWith - test string matching

        assertThat(firstName, startsWith("A"));
        assertThat(lastName, endsWith("VA"));
        assertThat(lastName, containsString("DZ"));

    }
    @DisplayName("Support for collection")
    @Test
    public void test2(){
        List<Integer> numList = Arrays.asList(11,44,3,55,88,5) ;
        // checking the list size is 6
        assertThat(numList, hasSize(6) );
        assertThat(numList.size(), is(6));
        // checking the list contains 11
        assertThat(numList, hasItem(11));
        // checking the list contains all items in any order
        assertThat(numList, containsInAnyOrder(11,5,44,3,55,88));
        // checking the list contains all items in exact order
        // contains method works like equals
        assertThat(numList, contains(11,44,3,55,88,5));



    }






}
