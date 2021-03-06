package day04;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import com.github.javafaker.Faker;

import static io.restassured.RestAssured.* ;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class PostRequestTest {

    @BeforeAll
    public static void setUp() {

        RestAssured.baseURI = "http://100.25.162.89:8000";
        RestAssured.basePath = "/api";
    }

    @DisplayName("Post request with String body")
    @Test
    public void testPostWithStringBody(){

        // lets try to get random names rather than same ADAM each time
        Faker faker = new Faker();
        String randomName = faker.name().firstName();
        System.out.println("randomName = " + randomName);

        String bodyString = "{\n" +
                "  \"name\"  : \"" + randomName+ "\",\n" +
                "  \"gender\": \"Female\",\n" +
                "  \"phone\": 6234567890\n" +
                "}";

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(bodyString).
        when()
                .post("/spartans").
        then()
                .log().all()
                .statusCode(201)
                .body("data.name", is(randomName));
    }
    @DisplayName("Posting with external json file")
    @Test
    public void testPostWithExternalFile(){

        // create a file object that point to spartan.json you just added
        // so we can use this as body in post request
        File file1 = new File("spartan.json");

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(file1).
        when()
                .post("/spartans").
        then()
                .log().all()
                .statusCode(201)
                .body("data.name", is("From file"));

    }

    @DisplayName("Posting with Map object as body")
    @Test
    public void testPostWithMapAsBody(){

        Map<String, Object> bodyMap = new HashMap<>(); // LinkedHashMap will keep an order of insertions
        bodyMap.put("name", "Edward");
        bodyMap.put("gender", "Male");
        bodyMap.put("phone", 1928374650);

        System.out.println(bodyMap);

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(bodyMap).// jackson-data-bind turn your java map into json here
        when()
                .post("/spartans").
        then()
                .log().all()
                .statusCode(201)
                .body("data.name", is("Edward"));

    }

}
