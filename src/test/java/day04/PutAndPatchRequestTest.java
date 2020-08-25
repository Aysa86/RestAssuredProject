package day04;
import POJO.Spartan;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class PutAndPatchRequestTest {

    @BeforeAll
    public static void setUp() {

        RestAssured.baseURI = "http://100.25.162.89:8000";
        RestAssured.basePath = "/api";
    }

    @DisplayName("Put Request body as a Map")
    @Test
    public void testPutRequestWithMap(){

        // put request to update spartan with id 159
        // name: put with map, gender: male, phone: 1231231231
        // status code 204
        // how do I know it's updated since it doesn't have body in request
        // we can make another get request right after this and assert the body

        // getting random name
        String randomName = new Faker().name().firstName();

        Map<String, Object> updatedBody = new LinkedHashMap<>();
        updatedBody.put("name",randomName);
        updatedBody.put("gender", "Male");
        updatedBody.put("phone", 1231231231);



        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(updatedBody).

        when()
                .put("/spartans/{id}", 159).
        then()
                .log().all()
                .statusCode(is(204));

        // Making another Get Request to make sure it worked!
        when()
                .get("/spartans/{id}", 159).
        then()
                .statusCode(is(200))
                .body("name", is(randomName));

    }

    @DisplayName("Put Request body as POJO")
    @Test
    public void testPutRequestWithPOJO(){



        // getting random name
        String randomName = new Faker().name().firstName();
        // this is how we can provide POJO
        Spartan spartan1 = new Spartan(randomName, "Female", 5432112345L);

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(spartan1).
        when()
                .put("/spartans/{id}", 159).
        then()
                .log().all()
                .statusCode(is(204));


    }

    @DisplayName("Patch Request")
    @Test
    public void testPatchPartialUpdate(){

        // only update the name with faker
        String randomName = new Faker().name().firstName();
        // String patchBody = "\"name\" : \" "+ randomName + "\" " ;
        Map<String, Object> patchBodyMap = new HashMap<>();
        patchBodyMap.put("name" , randomName) ;

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(patchBodyMap).
        when()
                .patch("/spartans/{id}", 107).
        then()
                .log().all()
                .statusCode(is(204));


    }



}
