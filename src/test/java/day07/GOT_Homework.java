package day07;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.List;

import static io.restassured.RestAssured.*;

public class GOT_Homework {

    // https://api.got.show/api/book/characters
    // get all character name in the List if the character field house = house Stark


    @DisplayName("Getting all members of House Stark")
    @Test
    public void testingGOT(){

        Response response =
                          given()
                                   .baseUri("https://api.got.show")
                                   .basePath("/api/book").
                          when()
                                   .get("/characters")
                                   ;

        List<String> houseStarkList = response.jsonPath().getList("findAll{it.house=='House Stark'}.name");
        System.out.println("houseStarkList = " + houseStarkList);

        // List should have size 76
        assertThat(houseStarkList, hasSize(76));

        // check the list has item Eddard Stark
        assertThat(houseStarkList, hasItem("Eddard Stark"));

        // check the list has items Robb Stark, Lyanna Stark, Arya Stark

        assertThat(houseStarkList, hasItems("Robb Stark", "Lyanna Stark", "Arya Stark"));

        

    }



}
