package day06;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.* ;
import static org.hamcrest.Matchers.*;

public class HR_ORDS_GroovyMagic {


    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://54.174.216.245" ;
        RestAssured.port = 1000 ;
        RestAssured.basePath = "ords/hr";
    }
    @DisplayName("Testing the /Employees endpoint")
    @Test
    public void testEmployees(){

        Response response = get("/employees"); //.prettyPeek();

        JsonPath jp = response.jsonPath();

        // print all id by getting a List
        System.out.println("All IDs " + jp.getList("items.employee_id"));

        // print 1st ID and last ID
        System.out.println("First ID " + jp.getInt("items[0].employee_id"));
        System.out.println("First ID " + jp.getInt("items[-1].employee_id"));

        // get all IDs from first one till fifth one
        System.out.println("From 1st till 5th " + jp.getList("items[0..4].employee_id"));

        // get all last names from index 10-15
        System.out.println("Last name index from 10-15 " + jp.getList("items[10..15].last_name"));

        // get the employee first_name with employee id of 105
        // Groovy has find and find all methods where you can specify the criteria to restrict the result
        // find method will return single value that fall into the criteria compared to findAll returns a List
       // find{ it.employee_id == 105 }
        // <it> means each item in your json array
        // just like in your for each loop you have to specify a name ,
        // the name <it> represent each item in the json array

       String result = jp.getString("items.find{ it.employee_id == 105 }.first_name");
        System.out.println("First name of employee with employee id 105 " + result);
        // First name of employee with employee id 105 David


        // find the salary of employee with email value LDEHAAN
        int salary = jp.getInt("items.find{it.email == 'LDEHAAN'}.salary");
        System.out.println("Salary " + salary);
        String firstName = jp.getString("items.find{it.email == 'LDEHAAN'}.first_name");
        System.out.println("firstName = " + firstName);

        System.out.println("first name of the guy is " + firstName + " who's salary is " + salary );

        // findAll will get the result that match the criteria and return it as a List
        // save all the first name of the employees with salary maro than 15000

        List<String> richPeople = jp.getList("items.findAll{it.salary > 15000}.first_name");
        System.out.println("richPeople = " + richPeople);

        // find out phone number  in department_id 90
        List<String> phoneNumber = jp.getList("items.findAll{it.department_id == 90}.phone_number");
        System.out.println("phoneNumber = " + phoneNumber);

    }


}
