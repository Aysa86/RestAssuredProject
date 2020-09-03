package day10;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utility.ConfigurationReader;
import utility.DB_Utility;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GettingTestDataFromDatabase {

    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI = ConfigurationReader.getProperty("spartan1.base_url");

        RestAssured.basePath = "/api";

        DB_Utility.createConnection("spartan1");

    }




    @DisplayName("Testing GET /spartans/{id} by getting the id from DB")
    @Test
    public void testDataFromDB(){

        // I want to write a query to get the newest data created from DB
        // SELECT * FROM SPARTANS
        // ORDER BY SPARTAN_ID DESC
        // and just get the first row using our utility method

        String myQuery = "SELECT * FROM SPARTANS ORDER BY SPARTAN_ID DESC";
        DB_Utility.runQuery(myQuery);
        Map<String, String> firstRowMap = DB_Utility.getRowMap(1);
        System.out.println("firstRowMap = " + firstRowMap);

        // get the id, name, gender, phone out of this map
        System.out.println("SPARTAN_ID key's value = " + firstRowMap.get("SPARTAN_ID"));//"spartan_id" => lower case


        // we want to save id, name, gender, phone
        // so we can use the ID to make GET request
        // and verify the response body match the data from Database

        int idFromDB = Integer.parseInt(firstRowMap.get("SPARTAN_ID"));
        String nameFromDB = firstRowMap.get("NAME");
        long phoneFromDB = Long.parseLong(firstRowMap.get("PHONE"));
        String genderFromDB = firstRowMap.get("GENDER");

        when()
                .get("/spartans/{id}", idFromDB).

        then()
                .statusCode(200)
                .log().all()
                .body("id", is(idFromDB))
                .body("name", is(nameFromDB))
                .body("gender", is(genderFromDB))
                .body("phone", is(phoneFromDB));
        //.body("phone.toLong", is(phoneFromDB)) ==> the groovy method that convert
        // int to long. Body method gets the phone number as an int, so, test can be failed

    }

    @DisplayName("Testing GET /spartans/{id} by getting the id from DB")
    @Test
    public void testDataFromDB_Randomly() {

        String myQuery = "SELECT * FROM SPARTANS ORDER BY SPARTAN_ID DESC";
        DB_Utility.runQuery(myQuery);
        // we want to get the rowNum for below method randomly from 1 till the last row count
        // so I can always get different data for my test
        // so first I need to get the row count so I can set the max of my random number
        int rowCount = DB_Utility.getRowCount();
        // get a random number from 1 to rowCount value
        int randomRowNum = new Faker().number().numberBetween(1, rowCount);

        Map<String, String> randomRowMap = DB_Utility.getRowMap(randomRowNum);

        System.out.println("CURRENT ROW IS "+ randomRowNum);
        System.out.println("CURRENT ROW DATA IS "+ randomRowMap);
    }

}
