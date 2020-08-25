package day05;
import POJO.Spartan2;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.* ;
import static org.hamcrest.Matchers.*;
public class JsonToPojoPractice {

    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI = "http://54.160.106.84:8000";
        RestAssured.basePath = "/api";
    }

    @DisplayName("Json to Pojo")
    @Test
    public void testSpartanJsonToSpartanObject(){
        Response response =
        given()
                .auth().basic("admin", "admin")
                .log().all()
                .pathParam("id", 275).
        when()
                .get("/spartans/{id}")
                .prettyPeek();

        // as method from response accept a class Type to define 
        //what is the type of Object you are trying to store the response as
        // Spartan2 class we created has all the fields that match the json fields from the response
        // So it will map all the json to the java fields and return Spartan2 POJO Object
        // turn below json into Java object
        Spartan2 sp2 = response.as(Spartan2.class);
        // above line is almost as if you are doing below line
       // Spartan2 sp2 = new Spartan2(275, "Elfriede", "Male", 1366149924 );
        System.out.println("sp2 = " + sp2);
    }

}
