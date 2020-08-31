package day08;

import org.junit.jupiter.api.Test;
import utility.ConfigurationReader;

public class HowDidActiveEnvironmentWorkedInOurCode {
    
    @Test
    public void test1(){
        
        // print out all the library1 info if active_env = library1
        // make a method out of it
        
        String currentEnvironment = ConfigurationReader.getProperty("active_env");
        System.out.println("currentEnvironment = " + currentEnvironment);

        // print library1 database url
       // String dbURL = ConfigurationReader.getProperty("library1.database.url");
        String dbURL = ConfigurationReader.getProperty(currentEnvironment + ".database.url");
        System.out.println("dbURL = " + dbURL);



    }
    
    
}
