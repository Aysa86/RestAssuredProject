package day12;


import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class HamcrestMatcherEveryItem {

    @Test
    public void testList(){

        List<Integer> nums = Arrays.asList(1,3,6,9,3,6);

        // greaterThan()
        assertThat(nums, everyItem(greaterThan(0)));

        // lessThan()
        assertThat(nums, everyItem(lessThan(10)));

        // check each and every item in your first number list is in between 0-50

        // crate a List of String and check each item has length of 4
        String str = "abc";
        assertThat(str, hasLength(3));

        List<String> names = Arrays.asList("Aysa", "John", "Kate");
        assertThat(names, everyItem(hasLength(4)));

        int num = 100;
        // num more than 50 or num less than 0
        assertThat(num, anyOf(greaterThan(50), lessThan(0)));

        int num2 = 45;
        // num more than 0 and less than 50
        assertThat(num2, allOf(greaterThan(0), lessThan(50)));





    }
}
