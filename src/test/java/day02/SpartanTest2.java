package day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class SpartanTest2 {
    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI = "http://100.25.162.89:8000";
        RestAssured.basePath = "/api";
    }

    @DisplayName("Get one Spartan test")
    @Test
    public void testSingleSpartan(){

        // I want to log the request I sent so I see what is the URL, methods and so on
        given()
                .log().all().
        when()
                .get("/spartans/107").
               // .prettyPeek().
        then()
                .log().all()  //to get everything from response
               // .log().body() // getting only the body of response
                //.log().status() // HTTP/1.1 200
                .statusCode(is(200));
    }





    @DisplayName("Get all Spartans")
    @Test
    public void testAllSpartans(){

        given()
                .header("Accept", "application/json").
        when()
                .get("/spartans").
       then()
                 .statusCode(is(200));
    }

    @DisplayName("Get all Spartans 2")
    @Test
    public void testAllSpartans2(){


        given()
               // .baseUri("http://100.25.162.89:8000")
                //.basePath("/api")
                .accept(ContentType.JSON).
        when()
                .get("/spartans").
        then()
                .statusCode(is(200))
                .contentType(ContentType.JSON);
    }
    @DisplayName("Hello Request test")
    @Test
    public void helloRequest(){
        given()
                .accept(ContentType.TEXT). // specify you want to get a text result back
        when()
                .get("/hello"). // sending request to baseURI + basePath + /hello
        then()
                .statusCode(is(200))// checking status code
                .contentType(ContentType.TEXT) // checking Content-Type is text
                .body(is("Hello from Sparta")); // checking the body

    }



}
