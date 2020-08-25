package day05;
import POJO.Spartan;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
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


    @DisplayName("Test GET /spartan/{id} Endpoint with credentials")
    @Test
    public void testGettingSingleSpartanWithCredentials(){

        int newID = createRandomSpartan();

        given()
                .log().ifValidationFails() // only log if anything goes wrong
                .auth().basic("admin", "admin")
                .pathParam("id", newID).
        when()
                .get("/spartans/{id}").
        then()
                .log().all()
                .statusCode(200);
    }

    public static int createRandomSpartan(){

        Faker faker = new Faker();
        String name = faker.name().firstName();
        String gender = faker.demographic().sex();
        long phone = faker.number().numberBetween(1000000000L, 9999999999L);

        Spartan spartan1 = new Spartan(name, gender, phone);

        Response response =
                given()
                       .log().all()
                       .auth().basic("admin", "admin")
                       .contentType(ContentType.JSON)
                       .body(spartan1).
                when()
                       .post("/spartans");

        return response.jsonPath().getInt("data.id");





    }

}
