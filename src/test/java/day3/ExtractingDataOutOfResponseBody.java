package day3;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.* ;
import static io.restassured.matcher.RestAssuredMatchers.* ;
import static org.hamcrest.Matchers.*;
public class ExtractingDataOutOfResponseBody {

    @DisplayName("Getting Movie info")
    @Test
    public void test1(){

        // provide your baseURI in the test
        // add query parameters
           // apikey, title
           // save the response into response object
        Response response =
      given()
              .log().all()
              .baseUri("http://www.omdbapi.com")
              .queryParam("apikey", "de46cef4")
              .queryParam("t", "Guardians of the Galaxy Vol. 2").
      when()
             .get();

       // response.prettyPrint();
        System.out.println(response.statusCode());


        // if you want to get single data out for example just title , just year
        // use path method of Response object and provide the jsonPath

        String title = response.path("Title");
        System.out.println("title = " + title);

        // using same example print out year, Director, Actors, first rating Source

        String year = response.path("Year");
        System.out.println("year = " + year);

        String director = response.path("Director");
        System.out.println("director = " + director);

        String actors = response.path("Actors");
        System.out.println("Actors = "+ actors);

        String firstRatingSource = response.path("Ratings[0].Source");
        System.out.println("firstRating = " + firstRatingSource);


    }


}
