package day2;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.* ;
import static io.restassured.matcher.RestAssuredMatchers.* ;
import static org.hamcrest.Matchers.*;
public class SpartansSearchTest_QueryParam {
    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI = "http://100.25.162.89:8000";
        RestAssured.basePath = "/api";
    }

    @DisplayName("Testing /spartans/search endpoint")
    @Test
    public void testSearch(){

        given()
                .log().all()
                .queryParam("gender", "male")
                .queryParam("nameContains", "l").
        when()
                .get("spartans/search").
        then()
                .log().all()
                .statusCode(200)
                .body("numberOfElements", is(19));









    }


}
