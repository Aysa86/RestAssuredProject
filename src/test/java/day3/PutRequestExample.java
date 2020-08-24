package day3;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.* ;
import static io.restassured.matcher.RestAssuredMatchers.* ;
import static org.hamcrest.Matchers.*;

public class PutRequestExample {

    @BeforeAll
    public static void setUp() {

        RestAssured.baseURI = "http://100.25.162.89:8000";
        // RestAssured.port = 8000;
        RestAssured.basePath = "/api";
    }

    @DisplayName("Updating existing Data")
    @Test
    public void updateSpartan(){

        String updatedBody = "{\n" +
                "  \"name\"  : \"Tony\",\n" +
                "  \"gender\": \"Male\",\n" +
                "  \"phone\": 7382910862\n" +
                "}" ;

        given()
                 .contentType(ContentType.JSON)
                .body(updatedBody)
                .log().all().
        when()
                .put("/spartans/{id}" , 155).
        then()
                .log().all()
                .statusCode(204);
    }

    @Test
    public void testDelete(){
        when()
                .delete("/spartans/{id}", 155).
        then()
                .statusCode(204);

    }


    }



