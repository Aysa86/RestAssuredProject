package day11;

import POJO.Spartan2;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import utility.ConfigurationReader;
import utility.DB_Utility;

import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;

public class GettingDataFromOtherRequests {

    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI = ConfigurationReader.getProperty("spartan1.base_url");
        RestAssured.basePath = "/api";



    }

    /**
     * We want to test GET /Spartans/{id}
     * and the ID is dynamic , and also different in different environments
     * we want to always work with available data
     * without dealing with 404 issue because data does not exists
     *
     *  Action Items
     *  1. Send a GET /spartans endpoint
     *  2. store the result as List of pojo
     *  3. initially just the the first data and use it for GET /Spartans/{id} request
     *      and use the name , gender , phone for body validation
     *  4 , eventually randomize the way you get the ID from List of Pojo
     */
    @Test
    public void testTheDynamicID(){

        Response response = get("/spartans");

        List<Spartan2> spartan2List = response.jsonPath().getList("", Spartan2.class);
        System.out.println("spartan2List = " + spartan2List);

        // get the 1st spartan id so we can send below request
      int firstSpartanIDFromList =  spartan2List.get(0).getId();
        System.out.println("firstSpartanIDFromList = " + firstSpartanIDFromList);

        String firstSpartanNameFromList = spartan2List.get(0).getName();

        // GET /spartans/{id}
        given()
                .pathParam("id", firstSpartanIDFromList).
        when()
                .get("/spartans/{id}").
        then()
                .statusCode(200)
                .body("name", is(firstSpartanNameFromList));

    }
    // can I repeat certain test n numbers of times in JUnit 5
    // use @RepeatedTest
    @RepeatedTest(5)

    public void gettingRandomID_and_NameForEachTest(){
        Response response = get("/spartans");
        List<Spartan2> spartan2List = response.jsonPath().getList("", Spartan2.class);

        // get random spartan object from the list each time
        // our range for the index will be 0--->spartan2List.size()

        Random random = new Random();
        int randomIndex = random.nextInt(spartan2List.size());
        System.out.println("randomIndex = " + randomIndex);
        
        Spartan2 randomSpartanObject = spartan2List.get(randomIndex);
        System.out.println("randomSpartanObject = " + randomSpartanObject);

        given()
                .pathParam("id", randomSpartanObject.getId()).
        when()
                .get("/spartans/{id}").
        then()
                .log().body()
                .statusCode(200)
                .body("name", is(randomSpartanObject.getName()))
                ;
        


    }

}
