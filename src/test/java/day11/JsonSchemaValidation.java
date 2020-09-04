package day11;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utility.ConfigurationReader;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

import static io.restassured.RestAssured.*;

public class JsonSchemaValidation {

    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI = ConfigurationReader.getProperty("spartan1.base_url");
        RestAssured.basePath = "/api";
    }

    @Test
    public void testSchema(){
        given()
                .log().uri().
        when()
                .get("/spartans/{id}", 10)
                .prettyPeek().
        then()
                .body(matchesJsonSchemaInClasspath("singleSpartanSchema.json"));
    }


}
