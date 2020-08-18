package day01;


import io.restassured.response.Response;
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

   @DisplayName("Testing /hello endpoint body")
   @Test
    public void testingHelloResponseBody(){
       // get the body and assert the body equal to Hello from Sparta
       Response response =  get("http://100.25.162.89:8000/api/hello");

       // getting the body as String
       System.out.println(response.asString()); // Hello from Sparta

       // getting the body by calling body method
       System.out.println(response.body().asString()); // Hello from Sparta

       // assert the body is Hello from Sparta, length is 17
       String helloBody = response.asString();

       assertEquals("Hello from Sparta", helloBody); // Hello from Sparta
       assertEquals(17, helloBody.length()); // length is 17

   }
   @DisplayName("Printing the response body using method")
   @Test
    public void printingBody(){

       Response response =  get("http://100.25.162.89:8000/api/hello");

       // easy way to print the response body and return at the same time
       response.prettyPrint(); // Hello from Sparta

       System.out.println("=====================================");

       // another way to see body quick is prettyPeek()
       // it prints out the entire response
       // it will include all header, status code, body
       // important!!! It returns same response Object rather than String like prettyPrint()
       // it will enable you to call more methods of response Object after peeking
       response.prettyPeek(); // HTTP/1.1 200
                              // Content-Type: text/plain;charset=UTF-8
                              // Content-Length: 17
                              //Date: Tue, 18 Aug 2020 18:35:52 GMT
                              // Hello from Sparta

       System.out.println("======================================");

       // to see entire response + save the status code into a variable in same statement

       int statusCode =  response.prettyPeek().statusCode();
       System.out.println("PRINTING ONLY STATUS CODE " + statusCode);






   }

}
