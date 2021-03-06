package day10;

import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utility.ConfigurationReader;
import utility.LibraryUtil;

import static io.restassured.RestAssured.*;

public class LibraryAppReusingTheSpecification {
/*
### Practice what we learned with LibraryApp
We will use these 3 endpoints :
* GET /dashboard_status
* GET /get_book_categories
* GET /get_all_users
We want to save the Request spec for
  * setting the X-LIBRARY-TOKEN HEADER
  * ContentType Header
  * logging everything
We want to save the Response spec for
  * Status code of `200`
  * ContentType Header is JSON
  * log if validation fail
 */
    static RequestSpecification requestSpec;
    static ResponseSpecification responseSpec;

    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI = ConfigurationReader.getProperty("library1.base_url");
        RestAssured.basePath = "/rest/v1";

        String token = LibraryUtil.loginAndGetToken("librarian69@library", "KNPXrm3S");
        // we have build a reusable request specification for setting contentType
        requestSpec = given()
                .log().all()    // we want to log everything
                .accept(ContentType.JSON)   // we want json back
                .header("x-library-token", token); // we want to set the token header
        // creating response specification without the builder
        responseSpec = expect()
                                .statusCode(200)   // expecting 200 status code
                                .contentType(ContentType.JSON) // contentType Json
                                .logDetail(LogDetail.ALL); // want to log everything

    }

    @DisplayName("Testing GET /get_book_categories Endpoint with spec")
    @Test
    public void testGetBookCategories(){

        given()
                .spec(requestSpec).
        when()
                .get("/get_book_categories").
        then()
                .spec(responseSpec);
    }


    @DisplayName("Testing GET /get_all_users Endpoint with spec")
    @Test
    public void testGetAllUsers(){
        given().spec(requestSpec).when().get("/get_all_users").then().spec(responseSpec);
    }

    @DisplayName("Testing GET /dashboard_stats Endpoint with spec")
    @Test
    public void testGetDashboardStats(){
        given().spec(requestSpec).when().get("/dashboard_stats").then().spec(responseSpec);
    }

}
