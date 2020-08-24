package day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.* ;
import static org.hamcrest.Matchers.*;
public class SpartanTest {

    @DisplayName("Get all Spartans")
    @Test
    public void testAllSpartans(){
       // String spartanURL = "http://100.25.162.89:8000/api/spartans";
        // how to set base url, port, base path so I can reuse them
        RestAssured.baseURI = "http://100.25.162.89:8000";
        RestAssured.basePath = "/api";


        // it will create the request URL as is
        // baseURI + basePath + what is after get("This Part")

        // I want explicitly specify I want JSON response from this result
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

        // send the same request specifying the accept header is application/json
        // use baseURI and basePath, check status code 200, contentType header is json

        given()
                .baseUri("http://100.25.162.89:8000") // alternative way to do it
                .basePath("/api")
                //.accept("application/json").
                // easy way to set accept don't have any typo
                .accept(ContentType.JSON).
        when()
                .get("/spartans").
        then()
                .statusCode(is(200))
                //.contentType("application/json; charset=UTF-8");
                //.contentType(is("application/json; charset=UTF-8"))
                // easiest way for Content-Type is using ContentType enum
                .contentType(ContentType.JSON);



    }



}
