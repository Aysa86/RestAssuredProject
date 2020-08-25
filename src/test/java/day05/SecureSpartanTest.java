package day05;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.* ;
import static org.hamcrest.Matchers.*;
public class SecureSpartanTest {

    // add @BeforeAll and use the SpartanApp ID with Basic Auth
    // make a simple GET request /spartans/{id}

    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI = "http://54.160.106.84:8000";
        RestAssured.basePath = "/api";
    }


    @DisplayName("Test GET /spartan/{id} Endpoint with no credentials")
    @Test
    public void testGetSingleSpartanSecured(){
        given()
                .log().all()
                .pathParam("id",174).
        when()
                .get("/spartan/{id}").
        then()
                .log().all()
                .statusCode(401);
    }




}
