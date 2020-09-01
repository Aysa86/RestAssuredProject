package day09;

import POJO.Spartan;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class TestPostSpartanDataDriven {

    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI = "http://100.25.162.89";
        RestAssured.port = 8000;
        RestAssured.basePath = "/api";

    }


    @ParameterizedTest
    @CsvFileSource(resources = "/allSpartan.csv", numLinesToSkip = 1)
    public void testAddSpartan(String csvName, String csvGender, long csvPhone){

        // I need POST body
       // System.out.println("csvName = " + csvName);
       // System.out.println("csvGender = " + csvGender);
       // System.out.println("csvPhone = " + csvPhone);

        Spartan spartanBody = new Spartan(csvName, csvGender, csvPhone);

        given()
                .contentType(ContentType.JSON)
                .body(spartanBody).
        when()
                .post("/spartans").
        then()
                .statusCode(201)
                .body("success", is("A Spartan is Born!"))
                .body("data.name", is(csvName))
                .body("data.gender", is(csvGender))
                .body("data.phone", is(csvPhone))
                .body("data.id", is(not(0))); // check if the phone is not 0
    }


}
