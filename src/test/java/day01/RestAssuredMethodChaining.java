package day01;
import io.restassured.RestAssured;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.* ;
import static io.restassured.matcher.RestAssuredMatchers.* ;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;



public class RestAssuredMethodChaining {

    @DisplayName("First Attempt for chaining")
    @Test
    public void chainingMethodsTest1(){

        // given()
           // some more info that you want to provide
           // like header, query params, path variable, body

        // when()
           // you send the request GET POST PUT PATCH DELETE

        // then()
           // validate something here
       // http://100.25.162.89:8000/api/hello

        when()
                .get("http://100.25.162.89:8000/api/hello"). // sending request to this url
        then()           // assertion starts here
                .statusCode(200) // asserting status code is 200
                .header("Content-Length", "17"); // asserting header is 17

    }

    @DisplayName("Using Hamcrest matcher for assertion")
    @Test
    public void testingWithMatcher(){

        when().
                get("http://100.25.162.89:8000/api/hello"). // sending request to this url
                prettyPeek().
        then().           // assertion starts here
                statusCode(is(200)). // assert status code is 200
        header("Content-Length", equalTo("17")).       // assert header is 17
        header("Content-Type",is("text/plain;charset=UTF-8" )).
        body(is("Hello from Sparta"));
    }

    @DisplayName("Testing GET /api/spartans endpoint")
    @Test
    public void testAllSpartans(){

        // given part ==> Request Specification
             // you can add info like header, query params, path variable, body
             // if this request need authentication, it also goes to give section
        // when part ==> Send Request(GET POST PATCH PUT)
             //  --Get response
        // then part -- Validatable Response
             // this is where assertions start
             // you can chain multiple assertions
             // if any assertions fail , whole test fail.

     given(). // add all your request specific information like header, query param, path var, body
             header("Accept", "application/xml").
     when().
             get("http://100.25.162.89:8000/api/spartans").
             prettyPeek().
     then().
             statusCode(is(200));
    }





}
