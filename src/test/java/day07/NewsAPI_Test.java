package day07;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class NewsAPI_Test {

    /*
     interview questions
          Go to newsAPI.org and get an API key
          // each request put the key you got in Authorization header
          header name Authorization , value Bearer long_token_here

          send request to the endpoint /top-headlines?country=us
          get all the author name if the source id is not null
          print the list of author name

     */

    @DisplayName("News API get all News Author if the Source id is not null")
    @Test
    public void testNews(){

        String apiKey = "a58f9839c84c428991e6bc30c6d27d7c";

        Response response =
                given()
                       .baseUri("http://newsapi.org")
                       .basePath("/v2")
                       .header("Authorization" , "Bearer " + apiKey)
                       .queryParam("country", "us")
                       .log().all().
                when()
                       .get("//top-headlines");


        JsonPath jp = response.jsonPath();
        List<String> allAuthors = jp.getList("articles.author");
        allAuthors.forEach(eachAuthor -> System.out.println("eachAuthor = " + eachAuthor));
        System.out.println("allAuthors.size() = " + allAuthors.size());

        System.out.println("==========================================");

        List<String> allAuthorsFiltered = jp.getList("articles.findAll{it.source.id != null }.author");
        for (String eachAuthor : allAuthorsFiltered){
            System.out.println("eachAuthor = " + eachAuthor);
        }

        System.out.println("allAuthorsFiltered.size() = " + allAuthorsFiltered.size());

    }


}
