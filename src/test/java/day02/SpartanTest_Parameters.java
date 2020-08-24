package day02;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.* ;
import static org.hamcrest.Matchers.*;
public class SpartanTest_Parameters {

    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI = "http://100.25.162.89:8000";
        RestAssured.basePath = "/api";
    }


    @DisplayName("Testing /spartans/{id}")
    @Test
    public void testSingleSpartan(){

        given()
                .log().all()
                .pathParam("id", 107 ).
        when()
                .get("/spartans/{id}").
        then()
                .statusCode(is(200));
    }


    @DisplayName("Testing /spartans/{id}")
    @Test
    public void testSingleSpartan2() {

        given()
                .log().all().
        when()
                .get("/spartans/{id}", 107).
        then()
                .statusCode(is(200));
    }

    @DisplayName("Testing /spartans/{id} Body")
    @Test
    public void testSingleSpartanBody(){

        given()
                .log().all()
                .pathParam("id", 107).
        when()
                .get("/spartans/{id}").
        then()
                .log().all()
                .statusCode(is(200))
        //        .body("JSON PATH", is("THE VALUE"))
                .body("id", is(107))
                .body("name", is("Aysa"))
                .body("gender", is("Female"))
                .body("phone", is(9999955555L));


    }

}
