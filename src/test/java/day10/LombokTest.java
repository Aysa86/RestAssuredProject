package day10;

import POJO.Countries;
import org.junit.jupiter.api.Test;

public class LombokTest {

    @Test
    public void test(){

        Countries country = new Countries("AR", "ARGENTINA", 10);
        System.out.println("country = " + country);

    }

}
