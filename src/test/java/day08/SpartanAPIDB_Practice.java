package day08;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utility.DB_Utility;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static io.restassured.RestAssured.*;

public class SpartanAPIDB_Practice {

    /**
     * The dev just implemented the search endpoint
     * and want it to be tested to make sure it's actually
     * returning the correct result from the database
     *
     *    GET /spartans/search?gender=Female&nameContains=a
     *  we want to test the count of result is correct
     *  for numberOfElements field.
     *
     *  The Database query to get the count is :
     *  // all the females that have a in their name , case insensitive
     *   -- This is how we get the data with case insensitive manner
     *      SELECT * FROM SPARTANS
     *       WHERE LOWER(gender) = 'female'
     *       and LOWER(name) LIKE '%a%' ;
     *
     */

    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI = "http://54.174.216.245" ;
        RestAssured.port = 8000 ;
        RestAssured.basePath = "/api";

        DB_Utility.createConnection("spartan1");
    }

    @DisplayName("Testing out my DB Connection")
    @Test
    public void testDB(){
       // DB_Utility.runQuery("Select * from Spartans");
       // DB_Utility.displayAllData();

        // run the query so we can use it for expected result
        String query = "SELECT * FROM SPARTANS     " +
                " WHERE LOWER(gender) = 'female'  " +
                " and LOWER(name) LIKE '%a%' ";
        DB_Utility.runQuery(query);
       // DB_Utility.displayAllData();

        // get the row count
        // and use it as expected result in next test
       int expectedResult = DB_Utility.getRowCount();
        System.out.println("expectedResult = " + expectedResult); // 36
    }

    @DisplayName("Testing /spartans/search Endpoint and Validate against DB")
    @Test
    public void testSearch(){
        // make a request to Get /spartans/search
        // using query parameter gender = Female nameContains a
        Response response =
        given()
                .queryParam("gender", "Female")
                .queryParam("nameContains", "a").
        when()
                .get("/spartans/search")
                .prettyPeek();
        
        int resultCount = response.path("numberOfElements");
        //int resultCount = response.jsonPath().getInt("numberOfElements");
        System.out.println("resultCount = " + resultCount); // 36

        String query = "SELECT * FROM SPARTANS     " +
                " WHERE LOWER(gender) = 'female'  " +
                " and LOWER(name) LIKE '%a%' ";
        DB_Utility.runQuery(query);

        int expectedResult = DB_Utility.getRowCount();
        // this is using junit assertion, you can use hamcrest if you want
        assertEquals(expectedResult, resultCount);

    }

    @DisplayName("Testing /spartans/search Endpoint and Validate against DB for all IDs")
    @Test
    public void testSearchVerifyAllIDs(){
        // make a request to Get /spartans/search
        // using query parameter gender = Female nameContains a
        Response response =
                given()
                        .queryParam("gender", "Female")
                        .queryParam("nameContains", "a").
                        when()
                        .get("/spartans/search")
                        .prettyPeek();

        // we want to store ID as a List<String> rather than List of Integer
        // so we can compare easily with the List<String> we got from DB Response
        // so we used the getList method that accept 2 parameters
        // the jsonpath to the List and the Data type of the List you want! ->> String.class
        List<String> IDsListFromResponse = response.jsonPath().getList("content.id", String.class);

        String query = "SELECT * FROM SPARTANS     " +
                " WHERE LOWER(gender) = 'female'  " +
                " and LOWER(name) LIKE '%a%' ";
        DB_Utility.runQuery(query);

        List<String> idListFromDB = DB_Utility.getColumnDataAsList(1);

        assertEquals(idListFromDB.size(), IDsListFromResponse.size());


    }







    @AfterAll
    public static void destroy(){
        RestAssured.reset();

        DB_Utility.destroy();
    }



}
