package day09;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;


import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

public class Zip_DataDriven {
    /*
    --- Data Drive your GET / api.zippopotam.us/us/:state/:city
    --- Create a csv file called state_city.csv
        add 3 column  state , city , numberOfZipcodes
                      VA ,  Fairfax , 9(send the request and prepare this number)
         assert the state , city
                    and number of zipcodes you got from the response
     */

    @ParameterizedTest
    @CsvFileSource(resources = "/state_city.csv", numLinesToSkip = 1)
    public void testStateCity(String expectedState, String expectedCity, int numberOfZipCodes){

      //  System.out.println("expectedState = " + expectedState);
       // System.out.println("expectedCity = " + expectedCity);
       // System.out.println("numberOfZipCodes = " + numberOfZipCodes);
        Response response =
        given()
                .pathParam("state", expectedState)
                .pathParam("city", expectedCity)
                .baseUri("http://api.zippopotam.us/us").
        when()
                .get("/{state}/{city}" )
                .prettyPeek();

        // assert the expectedState and city match in the response

        JsonPath jsonPath = response.jsonPath();
        System.out.println("state = " + jsonPath.getString("'state abbreviation'"));
        System.out.println("city = " + jsonPath.getString("'place name'"));

        assertThat( jsonPath.getString("'state abbreviation'"), is(expectedState) );
        assertThat( jsonPath.getString("'place name'"), is(expectedCity) );

        
        // now we want to count how many item in jsonArray from response
        // and validate that against our csv file expected numbers

        List<String > zipList = jsonPath.getList("places.'post code'");
        System.out.println("zipList = " + zipList);

        assertThat(zipList, hasSize(numberOfZipCodes));

        // if your jsonpath is pointing to an jsonArray you can count them
        // by calling groovy method called size()

        System.out.println("calling the size method directly in jsonPath = "
                + jsonPath.getInt("places.size()"));

    }



}
