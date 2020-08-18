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

        when().
                get("http://100.25.162.89:8000/api/hello"). // sending request to this url
        then().           // assertion starts here
                statusCode(200). // assert status code is 200
        header("Content-Length", "17"); // assert header is 17

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
}
