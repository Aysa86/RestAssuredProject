package day03;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.* ;
import static org.hamcrest.Matchers.*;

public class PostRequestExample {
    @BeforeAll
    public static void setUp() {

        RestAssured.baseURI = "http://100.25.162.89:8000";
        // RestAssured.port = 8000;
        RestAssured.basePath = "/api";
    }

    @DisplayName("Testing Post /api/spartans")
    @Test
    public void testAddSpartan(){

        String myBodyData = "{\n" +
                "  \"name\"  : \"Ada\",\n" +
                "  \"gender\": \"Female\",\n" +
                "  \"phone\": 6234567890\n" +
                "}" ;

        System.out.println("myBodyData = " + myBodyData);

        given()
                .contentType(ContentType.JSON)
                .body(myBodyData)
                .log().all().
        when()
                .post("/spartans").
        then()
                .log().all()
                .statusCode(is(201))
                .contentType(ContentType.JSON)
                .body("success", is("A Spartan is Born!"))
                .body("data.name", is("Ada"))



        ;
    }

    @DisplayName("Practice Extracting Data")
    @Test
    public void postRequestExtractingData(){

        String myBodyData = "{\n" +
                "  \"name\"  : \"Tony\",\n" +
                "  \"gender\": \"Male\",\n" +
                "  \"phone\": 7382910862\n" +
                "}" ;

        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .body(myBodyData)
                        .log().all().
                when()
                        .post("/spartans")
                        .prettyPeek()
                ;
        System.out.println("The id is " + response.path("data.id"));
        System.out.println("The name is " + response.path("data.name"));
        JsonPath jp = response.jsonPath();

        System.out.println("ID using jsonPath: " + jp.getInt("data.id"));
        System.out.println("Name using jsonPath: " + jp.getString("data.name"));

    }

}
