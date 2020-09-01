package day09;

import POJO.Spartan;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class SpartanRoleBaseAccessControlNegativeTestReuse {

    static RequestSpecification requestSpecification;
    static ResponseSpecification responseSpecification;


    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "http://54.160.106.84";
        RestAssured.port = 8000;
        RestAssured.basePath = "/api";

        // setting the value of requestSpecification
        requestSpecification = given()
                .log().all()
                .auth().basic("user", "user")
                .accept(ContentType.JSON);

        // setting the value of responseSpecification using ResponseSpeBuilder object
        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        responseSpecification = responseSpecBuilder
                .expectStatusCode(403)
                .expectContentType(ContentType.JSON)
                .expectHeader("Date", notNullValue(String.class)  )
                .log(LogDetail.ALL)
                .build();


    }

    @DisplayName("User should not be able to delete data")
    @Test
    public void testUserCanNotDeleteData(){

       given()
               .spec(requestSpecification).
       when()
               .delete("/spartans/{id}", 10).
       then()
               .spec(responseSpecification);


    }
    @DisplayName("User should not be able to update data")
    @Test
    public void testUserCanNotUpdateData(){

        Spartan spartanObject = new Spartan("Aysa", "Female", 7829103628L);

        given()
                .spec(requestSpecification)
                .contentType(ContentType.JSON)
                .body(spartanObject).
        when()
                .put("/spartans/{id}", 10).
        then()
                .spec(responseSpecification);



    }
    @DisplayName("User should not be able to post data")
    @Test
    public void testUserCanNotPostData(){

        Spartan spartanObject = new Spartan("Aysa", "Female", 7829103628L);

        given()
                .spec(requestSpecification)
                .contentType(ContentType.JSON)
                .body(spartanObject).
        when()
                .post("/spartans/{id}", 10).
        then()
                .spec(responseSpecification);




    }









}
