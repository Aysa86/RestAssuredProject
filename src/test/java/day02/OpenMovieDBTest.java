package day02;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.* ;
import static org.hamcrest.Matchers.*;
public class OpenMovieDBTest {
    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI = "http://www.omdbapi.com";

    }
    @DisplayName("Testing movie")
    @Test
    public void testMovie(){
        given()
                .log().all()
                .queryParam("apikey", "de46cef4")
                .queryParam("t", "Guardians of the Galaxy Vol. 2")
                .queryParam("plot", "full").
        when()
                .get(). // url is already specified that's why do nothing, just empty get()

        then()
                .log().all()
                .statusCode(is(200))
                // checking title contains Guardians ...
                // looking for exact match
                .body("Title", containsString("Guardians of the Galaxy Vol. 2"))
                .body("Year", is("2017"))
                .body("Ratings[0].Value", is("7.6/10"))
                .body("Ratings[-1].Value", is("67/100"))

                ;

    }
}
