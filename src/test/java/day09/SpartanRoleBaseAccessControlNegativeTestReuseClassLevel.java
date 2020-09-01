package day09;

import POJO.Spartan;
import io.restassured.RestAssured;
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

public class SpartanRoleBaseAccessControlNegativeTestReuseClassLevel {



    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "http://54.160.106.84";
        RestAssured.port = 8000;
        RestAssured.basePath = "/api";


    }

    @DisplayName("User should not be able to delete data")
    @Test
    public void testUserCanNotDeleteData(){
        // building reusable request specification
        // using RequestSpesBuilder

        RequestSpecification  requestSpecification = given()
                                                    .log().all()
                                                    .auth().basic("user", "user")
                                                    .accept(ContentType.JSON);

        // Extracting RequestSpecification for all assertions so we can reuse
        // we will use a class called ResponseSpecBuilder

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();

        // getting the reusable responseSpecification object using the builder methods chaining

       ResponseSpecification responseSpecification = responseSpecBuilder.expectStatusCode(403)
                           .expectContentType(ContentType.JSON)
                           .expectHeader("Date", notNullValue(String.class))
                           .log(LogDetail.ALL) // we log all the response
                           .build(); // build method will return ResponseSpecification
                                           // expectHeader second argument expect a Matcher<String>
                                          // but notNullValue() return a Matcher<Object> so it did not compile
                                          // so we used the second version of notNullValue( The Matcher type inside <>)
                                          // to specify what kind of matcher we wanted

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
                .log().all()
                .auth().basic("user", "user")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(spartanObject).
        when()
                .put("/spartans/{id}", 10).
        then()
                .statusCode(403)
                .log().all()
                .contentType(ContentType.JSON)
                .header("Date", is(not(0)));



    }
    @DisplayName("User should not be able to post data")
    @Test
    public void testUserCanNotPostData(){

        Spartan spartanObject = new Spartan("Aysa", "Female", 7829103628L);

        given()
                .log().all()
                .auth().basic("user", "user")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(spartanObject).
        when()
                .post("/spartans/{id}", 10).
        then()
                .statusCode(403)
                .log().all()
                .contentType(ContentType.JSON)
                .header("Date", is(not(0)));




    }









}
