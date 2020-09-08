package day12;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;

public class XMLResponseTestMovieDB {


        // http://www.omdbapi.com/?t=Guardians of the Galaxy Vol. 2&r=xml&apikey=de46cef4

       @Test
    public void testMovieXML(){
           given()
                   .log().all()
                   .baseUri("http://www.omdbapi.com/")
                   .queryParam("apikey", "de46cef4")
                   .queryParam("t", "Guardians of the Galaxy Vol. 2")
                   .queryParam("r", "xml").
           when()
                   .get().
           then()
                   .log().all()
                   .statusCode(200)
                   .contentType(ContentType.XML)
                   .body("root.movie.@title", is("Guardians of the Galaxy Vol. 2") )
                   .body("root.movie.@year", is("2017"))
                   .body("root.movie.@rated", is("PG-13"))
                   .body("root.movie.@released", is("05 May 2017"))
                   .body("root.movie.@runtime", is("136 min"));

       }



}
