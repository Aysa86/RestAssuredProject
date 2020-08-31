package day08;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SpartanAPI_EC2_HappyPath {

    // we need 1 = Add, 2 = Read, 3 = Update, 4 = Delete
    private static int newID;

    @Order(1)
    @Test
    public void testAddData(){
        // create one data here using POJO as body, do your assertion
        // and grab the id so it can be used for all next 3 tests


    }
    @Order(2)
    @Test
    public void testReadData(){
        // use the id that have been generated from previous request
        // assert the response json according to expected data
        // expected data is the same data you used to create in previous request
        // you can make yuor post body from previous request at class level
        // or you can also query the DB to get your expected data
    }
    @Order(3)
    @Test
    public void testUpdateData(){

    }
    @Order(4)
    @Test
    public void testDeleteData(){

    }


}
