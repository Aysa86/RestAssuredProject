package day05;
import POJO.Spartan2;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

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

        int newID = SecureSpartanTest.createRandomSpartan();

        Response response =
        given()
                .auth().basic("admin", "admin")
                .log().all()
                .pathParam("id", newID).
        when()
                .get("/spartans/{id}")
                .prettyPeek();

        // as method from response accept a class Type to define 
        //what is the type of Object you are trying to store the response as
        // Spartan2 class we created has all the fields that match the json fields from the response
        // So it will map all the json to the java fields and return Spartan2 POJO Object
        // turn below json into Java object to work with Java Object
        Spartan2 sp2 = response.as(Spartan2.class);
        // above line is almost as if you are doing below line
       // Spartan2 sp2 = new Spartan2(275, "Elfriede", "Male", 1366149924 );
        System.out.println("sp2 = " + sp2);
    }

    @DisplayName("Search Request and save 1st Result as Spartan2 Pojo")
    @Test
    public void gettingNestedJsonAsPojo(){
    Response response = given()
                                  .log().all()
                                  .auth().basic("admin", "admin")
                                  .queryParam("gender","Male").
                        when()
                                  .get("/spartans/search")
                                  .prettyPeek();

        System.out.println("response.statusCode() = " + response.statusCode());

        // print out first id and name from response
        JsonPath jp = response.jsonPath();
        System.out.println("First ID " + jp.getInt("content[0].id"));
        System.out.println("First name " + jp.getString("content[0].name"));

        // let's try to save the entire first json object in the array into Spartan2 POJO
       Spartan2 firstMaleSpartan = jp.getObject("content[0]", Spartan2.class);
                                    //      what we want to save     where we want to store (type)
        System.out.println("firstMaleSpartan = " + firstMaleSpartan);
        // Spartan2{id=317, name='Robert', gender='Male', phone=3252645223}

        System.out.println("The Spartan id from POJO is " + firstMaleSpartan.getId());
        System.out.println("The Spartan name from POJO is " + firstMaleSpartan.getName());
        System.out.println("The Spartan gender from POJO is " + firstMaleSpartan.getGender());
        System.out.println("The Spartan phone from POJO is " + firstMaleSpartan.getPhone());
    }


    // how can I store the entire json array into the List<Spartan2>
    @DisplayName("Save the Json array as List<Spartan>")
    @Test
    public void testJsonArrayToListOfPojo(){

        Response response =
                given()
                          .auth().basic("admin", "admin")
                          .queryParam("gender", "Female").
                when()
                          .get("/spartans/search");

        // store all IDs as List of Integers
        JsonPath jp = response.jsonPath();
        List<Integer> ids = jp.getList("content.id");
        System.out.println(ids);

        List<String> names = jp.getList("content.name");
        System.out.println(names);

        // store the entire jsonArray as List of Spartan2
        List<Spartan2> spartan2List = jp.getList("content", Spartan2.class);
        System.out.println(spartan2List);

      /*  for (Spartan2 each : spartan2List) {
            System.out.println(each);
        }*/

        spartan2List.forEach(each -> System.out.println(each));
    }


}
