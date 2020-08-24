package day04;
import POJO.Spartan;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class PostRequestWithPOJO {

    @BeforeAll
    public static void setUp() {

        RestAssured.baseURI = "http://100.25.162.89:8000";
        RestAssured.basePath = "/api";
    }

    @Test
    public void testPostBodyWithPojo(){

        Spartan spartan1 = new Spartan("Aysa", "Female", 9089786756L);

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(spartan1).
        when()
                .post("/spartans").
        then()
                .log().all()
                .statusCode(201);

    }

}
