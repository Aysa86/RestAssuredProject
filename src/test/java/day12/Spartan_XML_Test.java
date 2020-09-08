package day12;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utility.ConfigurationReader;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
public class Spartan_XML_Test {

    // boilerplate goes here
    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI = ConfigurationReader.getProperty("spartan1.base_url");
        RestAssured.basePath = "/api";
    }

    @DisplayName("Test XML Response from GET /Spartans")
    @Test
    public void testSpartanXML(){

        given()
                .accept(ContentType.XML).
        when()
                .get("/spartans").
        then()
                .contentType(ContentType.XML)
                .body("List.item[0].name", is("Meade"))
                .body("List.item[0].gender", is("Male"))
                .body("List.item[0].id", is("1")); 
    }
    
    @DisplayName("XMLPath object to exctract the data out")
    @Test
    public void testSpartanExtractData(){
        Response response = given()
                .accept(ContentType.XML).
                when()
                .get("/spartans");
        
        // getting XMLPath object just like we did for JsonPath object
        XmlPath xmlPath = response.xmlPath();
        
        // getting first spartan and store it into int
        int firstID = xmlPath.getInt("List.item[0].id");
        System.out.println("firstID = " + firstID);
        // getting the first spartan name
        String firstName = xmlPath.getString("List.item[0].name");
        System.out.println("firstName = " + firstName);
        // getting the first spartan phone
        long firstPhone = xmlPath.getLong("List.item[0].phone");
        System.out.println("firstPhone = " + firstPhone);
        
        // get all IDs and store it into the List
        List<Integer> allIDs = xmlPath.getList("List.item.id", Integer.class);
        System.out.println("allIDs = " + allIDs);

        // assert the list size is some number
        // assert above list has items 199, 201, 193
        assertThat(allIDs, hasSize(142));

        assertThat(allIDs, hasItems(199, 201, 193));

        // get the List of long phone number
        // the size is 142
        // check it has 3584128232, 2227140732
        // check every item is greater than 1000000000
        List<Long> phones = xmlPath.getList("List.item.phone", Long.class);
        System.out.println("phones = " + phones);
        assertThat(phones, hasSize(142));
        assertThat(phones, hasItems(3584128232L, 2227140732L) );

      //  how to check every item in collection match any criteria
       // assertThat(5, greaterThan(3));
        assertThat(phones, everyItem(greaterThan(100000000L)));

        // Get a List of String from the names
       // find out how many unique names you have
        List<String> namesList = xmlPath.getList("List.item.name");
        System.out.println("namesList = " + namesList);
        // Set interface define collection of unique elements
        // creating HashSet object by coping everything from List, duplicates are auto-removed
        Set<String> uniqueNames = new HashSet<>(namesList);
        System.out.println("uniqueNames = " + uniqueNames);

        System.out.println("namesList.size() " + namesList.size()); // 142
        System.out.println("uniqueNames.size() " + uniqueNames.size()); // 127
        
        
        
    }

}
