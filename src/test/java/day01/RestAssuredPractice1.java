package day01;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.* ;
import static io.restassured.matcher.RestAssuredMatchers.* ;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RestAssuredPractice1 {
   // spartan app that doesn't require password
    // my IP address
   // http://100.25.162.89:8000/api/hello

   @Test
    public void test1(){
      // RestAssured.get("URL here");
       //since we did static import
       // we can directly call the method
     Response response =  get("http://100.25.162.89:8000/api/hello");


       System.out.println("Status code of this response: " + response.statusCode() ); // printing the status code
       // Status code of this response: 200

       System.out.println( "Getting status line of this response " + response.getStatusLine() );

       // Getting status line of this response HTTP/1.1 200

       // in restAssured there are usually 2 methods that does same action
       // one directly with the name like response.statusCode();
       // another starting with getXXX like response.getStatusLine();

       // getting header of the response
       // we can use response.header("Date")
       // or we can use response.getHeader("Date");
       System.out.println("Getting the Date header " + response.header("Date") );


       // Content_type is so common in pretty much all requests so there is a build support for this header
       // by directly calling a method
       System.out.println(response.contentType());
       System.out.println(response.getContentType());
       System.out.println("Getting the value of Content-Type header " + response.getHeader("Content-Type"));

       System.out.println("Getting the value of Content-Length header " + response.header("Content-Length"));

   }
   @DisplayName("Testing/ Hello endpoint")
   @Test
    public void testHello(){
       Response response = get("http://100.25.162.89:8000/api/hello");

       // testing the status code returned correctly
       assertEquals(200, response.statusCode());

       // testing the Content-Type header value is  text/plain;charset=UTF-8
       assertEquals("text/plain;charset=UTF-8", response.contentType());
       assertEquals("text/plain;charset=UTF-8", response.header("Content-Type"));
       assertEquals("text/plain;charset=UTF-8", response.getHeader("Content-Type"));
       // testing the Content-length header value is 17
       assertEquals("17", response.header("Content-Length"));

   }

}
