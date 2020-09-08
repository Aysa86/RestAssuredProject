package day12;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utility.ConfigurationReader;

import java.io.File;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

import static io.restassured.RestAssured.*;

public class SchemaValidationTest {

    @BeforeAll
    public static void init() {

        RestAssured.baseURI = ConfigurationReader.getProperty("spartan1.base_url");
        RestAssured.basePath = "/api";
    }



    @DisplayName("Testing GET /spartans response against Schema")
    @Test
    public void testGetAllSpartansSchema(){

        when()
                .get("/spartans").
        then()
                .body(matchesJsonSchemaInClasspath("AllSpartansSchema.json"));
    }

    @DisplayName("Testing GET /spartans response against Schema in root path")
    @Test
    public void testGetAllSpartansSchemaInRootPath(){
        // we need to provide right path so to do that
        // we create a file Object that point to the schema
        // use matchesJsonSchema method that accept a file
        // and do our validation

        File mySchema = new File("AllSpartansSchema2.json");

        when()
                .get("/spartans").
        then()
                .body(matchesJsonSchema(mySchema));


    }

    @DisplayName("Testing GET /spartans/search response against Schema")
    @Test
    public void testSpartansSearch(){

        given()
                .queryParam("gender", "Female").
        when()
                .get("/spartans/search").
        then()
                .body(matchesJsonSchemaInClasspath("SearchSchema.json"));

    }




}
