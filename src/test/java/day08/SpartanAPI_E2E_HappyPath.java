package day08;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.xml.ws.Response;

import static io.restassured.RestAssured.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SpartanAPI_E2E_HappyPath {

    // we need 1 = Add, 2 = Read, 3 = Update, 4 = Delete
    static int newID;

    // We want exact order 1.Add , 2, Read, 3 Update , 4 Delete

    @Order(1)
    @Test
    public void testAddData(){
        // create one data here using POJO as body, do your assertion
        // and grab the id so it can be used for all next 3 tests
        
        System.out.println("New ID is generated from the post request and saved " );
        newID = 100 ;

    }
    @Order(2)
    @Test
    public void testReadData(){
        // use the id that have been generated from previous request
        // assert the response json according to expected data
        // expected data is the same data you used to create in previous request
        // you can make yuor post body from previous request at class level
        // or you can also query the DB to get your expected data
        System.out.println(" The ID from Add Data Test is "  + newID);
        System.out.println("Using this ID for get Request ");
    }
    @Order(3)
    @Test
    public void testUpdateData(){

        System.out.println(" The ID from Add Data Test is "  + newID);
        System.out.println("Using this ID for PUT Request ");

    }
    @Order(4)
    @Test
    public void testDeleteData(){

        System.out.println(" The ID from Add Data Test is "  + newID);
        System.out.println("Using this ID for DELETE Request ");

    }


}
