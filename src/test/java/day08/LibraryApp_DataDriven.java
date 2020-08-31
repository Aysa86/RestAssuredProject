package day08;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import utility.ConfigurationReader;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class LibraryApp_DataDriven {

    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI = ConfigurationReader.getProperty("library1.base_url");
        RestAssured.basePath = "/rest/v1";
    }




    @ParameterizedTest(name = "iteration {index} | username: {0}, password: {1}")
    @CsvFileSource(resources = "/credentials.csv")
    public void testLoginCredentials(String username, String password){
       // System.out.println("username = " + username); prints all username and password
       // System.out.println("password = " + password);

       // so now let's make a post request to /login
       // contentType is x-form-urlencoded
       // for data email, password
       // check if the status code 200 if the password is correct
        // check the token field from the response is not null

        given()
                .contentType(ContentType.URLENC)
                .formParam("email", username)
                .formParam("password", password).
        when()
                .post("/login").
        then()
                .statusCode(200)
                .body("token", notNullValue())
                ;


    }

    @AfterAll
    public static void destroy(){
        RestAssured.reset();
    }


}
