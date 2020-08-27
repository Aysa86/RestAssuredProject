package day06;

import POJO.Locations;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.* ;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasSize;

public class HR_ORDS_POJO_Test {
    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI = "http://54.174.216.245:1000";
        RestAssured.basePath = "ords/hr";
    }

    @DisplayName("Testing the /locations/{location id} endpoint")
    @Test
    public void testLocations(){
        Response response =
        given()
                .accept(ContentType.JSON)
                .pathParam("location_id", 1700)
                .log().all().
        when()
                .get("/locations/{location_id}")
                .prettyPeek();

        Locations location1 = response.as(Locations.class);
        System.out.println("location1 = " + location1);
    }

    @DisplayName("Testing the /location endpoint")
    @Test
    public void testLocation(){

        // save all Locations address to List<Location>

        Response response = get("/locations").prettyPeek();

        List<Locations> locationsList = 
                response.jsonPath().getList("items", Locations.class);
        for (Locations eachAddress : locationsList){
            System.out.println("each address = " + eachAddress);
        }

        // the other way to print the list of LOCATIONS USING LAMBDA EXPRESSION
        locationsList.forEach(each -> System.out.println("each = " + each));

        // how do we assert we have 23 items in the list
        // using hamcrest library assertion to check the list with certain size
        assertThat(locationsList, hasSize(23));



        // save all street address to List<String>

        List<String> addressList = response.jsonPath().getList("items.street_address");
        for (String each : addressList){
            System.out.println("each = " + each);
        }
        
        // we want to get a List of pojo but we only want to get those
        //data with country_id as US
        List<Locations> usLocations = 
        response.jsonPath().getList("items.findAll{ it.country_id == 'US' }", Locations.class);
      //  System.out.println("usLocations = " + usLocations);
        usLocations.forEach(eachLocation -> System.out.println(eachLocation));
        
    }


}
