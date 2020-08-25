package day05;
import POJO.Region;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.* ;
import static org.hamcrest.Matchers.*;

public class HR_ORDS_Test {
    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI = "http://54.174.216.245:1000";
        RestAssured.basePath = "ords/hr";
    }

    @DisplayName("Testing the /regions/{id} endpoint")
    @Test
    public void testRegion(){
        Response response =
       given()
               .log().all()
               .accept(ContentType.JSON)
               .pathParam("region_id",1).

       when()
               .get("/regions/{region_id}")
               .prettyPeek();
        Region region1 = response.as(Region.class);
        System.out.println(region1);
    }

    @DisplayName("Testing the /regions endpoints")
    @Test
    public void testRegionJsonArrayToPojoList(){

        // send a request to /regions endpoint to get all regions
        // save the regions json array into Pojo List
        Response response = get("/regions").prettyPeek();


        JsonPath jp = response.jsonPath();
        // get first region_name from the response using json path
        System.out.println( "First region name " + jp.getString("items[0].region_name"));
        // get last region_id from the response using json path
        System.out.println("Last region id " + jp.getInt("items[3].region_id"));
       // the other way to print out last region_id
        System.out.println("Last region id " + jp.getInt("items[-1].region_id"));
        // get the list of region_name from the response and save it to the List<String>
        List<String> allNames = jp.getList("items.region_name");
        System.out.println("allNames = " + allNames);
        // get the List<Region> from the response json
        List<Region> regionList = jp.getList("items", Region.class);
        System.out.println("regionList = " + regionList);
    }


}
