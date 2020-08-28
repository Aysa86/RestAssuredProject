package day07;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utility.DB_Utility;

public class MySQL_Test {

    @BeforeAll
    public static void setUpDB(){
        DB_Utility.createConnection("library1");
    }

    @DisplayName("Quick DB check")
    @Test
    public void testThingsOut(){

        DB_Utility.runQuery("SELECT * FROM books");
        DB_Utility.displayAllData();
    }

    @AfterAll
    public static void destroy(){
        DB_Utility.destroy();
    }
}
