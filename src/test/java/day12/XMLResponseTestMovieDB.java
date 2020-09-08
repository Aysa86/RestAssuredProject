package day12;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.List;

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

       @DisplayName("Getting many movies and extracting attributes to the List")
       @Test
       public void testGettingAttributesAsList(){
       Response response = given()
                   .log().all()
                   .baseUri("http://www.omdbapi.com/")
                   .queryParam("apikey", "de46cef4")
                   .queryParam("s", "superman")
                   .queryParam("r", "xml").
           when()
                   .get();

           XmlPath xmlPath = response.xmlPath();
           List<String> superManMovieList = xmlPath.getList("root.resuly.@title");
           System.out.println("superManMovieList = " + superManMovieList);

       }



}
