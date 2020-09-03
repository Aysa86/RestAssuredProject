package day10;

import POJO.Category;
import POJO.User;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utility.ConfigurationReader;
import utility.LibraryUtil;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class LibraryAppReusingTheSpecification_Shorter {


    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI = ConfigurationReader.getProperty("library1.base_url");
        RestAssured.basePath = "/rest/v1";


        String token = LibraryUtil.loginAndGetToken("librarian69@library", "KNPXrm3S");

        // just like we set baseURI and basePath
        // we are using static field of Restassured to set it at global level
        RestAssured.requestSpecification = given()
                                                   .log().all()
                                                   .accept(ContentType.JSON)
                                                   .header("x-library-token", token);

        RestAssured.responseSpecification = expect()
                                                   .statusCode(200)
                                                   .contentType(ContentType.JSON)
                                                   .logDetail(LogDetail.ALL);

    }

    @DisplayName("Testing GET /get_book_categories Endpoint with spec")
    @Test
    public void testGetBookCategories(){

        Response response = when().get("/get_book_categories");
        JsonPath jp = response.jsonPath();
        
        List<Category> listOfCategories = jp.getList("", Category.class);
        System.out.println("listOfCategories = " + listOfCategories);
    }


    @DisplayName("Testing GET /get_all_users Endpoint with spec")
    @Test
    public void testGetAllUsers(){
        Response response = when().get("/get_all_users");
        JsonPath jp = response.jsonPath();

        List<User> listOfUsers = jp.getList("", User.class);
        System.out.println("listOfUsers = " + listOfUsers);
        
     
     
     
    }

    @DisplayName("Testing GET /dashboard_stats Endpoint with spec")
    @Test
    public void testGetDashboardStats(){

     Response response = when().get("/dashboard_stats").prettyPeek();
     
     Map<String, Object> statMap = response.jsonPath().getMap("");
        System.out.println("statMap = " + statMap);
    }




}
