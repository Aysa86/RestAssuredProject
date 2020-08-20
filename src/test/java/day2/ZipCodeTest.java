package day2;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.* ;
import static io.restassured.matcher.RestAssuredMatchers.* ;
import static org.hamcrest.Matchers.*;

@DisplayName("Testing Zip code API")
public class ZipCodeTest {

    @BeforeAll
    public static void setUp(){
       RestAssured.baseURI = "http://api.zippopotam.us";
       RestAssured.basePath = "/us";
    }

    @DisplayName("zip to city")
    @Test
    public void testZipCode(){

       given()
               .log().all()
               .pathParam("zip", 12345).
       when()
               .get("/{zip}" ).
       then()
               .log().all()
               .statusCode(is(200))
               .contentType(ContentType.JSON)
               .body("country", is("United States"))
               // get the state and check it is New York
               .body("places[0].state", is("New York"))
               .body("places[0].'place name'", is("Schenectady"))
               .body("'post code'", is("12345"));
    }

    @DisplayName("City to Zip")
    @Test
    public void testCityToZip(){
        given()
                .pathParam("state", "NY")
                .pathParam("city", "Schenectady")
                .log().all().
        when()
        .get("{state}/{city}").
        then()
                .log().all()
                .statusCode(is(200))
                .body("'country abbreviation'", is("US"))
        // test the latitude 42.8333
                .body("places[0].latitude", is("42.8333"))
             // jsonPath hack for getting last item from jsonArray
            // -1 index indicate the last item, only works here not in postman
                .body("places[-1].latitude", is("42.8333"))

        ;

    }

}
