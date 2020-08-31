package day07;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utility.ConfigurationReader;
import utility.DB_Utility;
import utility.LibraryUtil;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class LibraryApp_API_DB_Test {

    private  static String libraryToken;
    @BeforeAll
    public static void setUp(){
     String active_env = ConfigurationReader.getProperty("active_env");
     libraryToken = LibraryUtil.setUpRestAssuredAndDB_forEnvironment(active_env);

    }


    @Test
    public void test(){
        System.out.println("libraryToken = " + libraryToken);
        // We will make a call to /Dashboard_stats endpoint and validate the data against database data

        DB_Utility.runQuery("SELECT count(*) from books");
        // it return book count as a single row and column
        String bookCount = DB_Utility.getColumnDataAtRow(1,1);
        System.out.println("bookCount = " + bookCount); // 985

        DB_Utility.runQuery("SELECT count(*) from users");
        String usersCount = DB_Utility.getColumnDataAtRow(1,1);
        System.out.println("usersCount = " + usersCount); //5074
        
        DB_Utility.runQuery("SELECT count(*) from book_borrow where is_returned=false");
        String borrowedBooks = DB_Utility.getColumnDataAtRow(1,1);
        System.out.println("borrowedBooks = " + borrowedBooks); //601

        given()
                .log().all()
                .header("x-library-token", libraryToken).
        when()
                .get("/dashboard_stats").
              //  .prettyPeek();
        then()
                 .body("book_count", is(bookCount))
                 .body("borrowed_books", is(borrowedBooks))
                 .body("users", is(usersCount))
        ;



    }




    @AfterAll
    public static void tearDown(){

        DB_Utility.destroy();
        RestAssured.reset(); // this is for resetting all the values we set for RestAssured itself


    }




}
