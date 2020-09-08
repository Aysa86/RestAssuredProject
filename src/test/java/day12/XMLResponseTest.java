package day12;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utility.ConfigurationReader;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class XMLResponseTest {

    @BeforeAll
    public static void init() {
         // we are going to seng GET request to this endpoint
        // https://vpic.nhtsa.dot.gov/api/vehicles/GetMakeForManufacturer/988?format=xml

        RestAssured.baseURI = "https://vpic.nhtsa.dot.gov";
        RestAssured.basePath = "/api/vehicles";
    }

    @Test
    public void testXML(){

        given()
                .log().all()
                .queryParam("format", "xml").
        when()
                .get("/GetMakeForManufacturer/988").
        then()
                .log().all()
                .statusCode(200)
                .contentType(ContentType.XML)
                .body("Response.Count", is("2"))
                .body("Response.Message", is("Results returned successfully"))
                .body("Response.Results.MakesForMfg[0].Make_ID", is("474"))
                .body("Response.Results.MakesForMfg[1].Make_Name", is("ACURA"));
    }



}
