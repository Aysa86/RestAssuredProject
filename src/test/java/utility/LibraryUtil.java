package utility;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class LibraryUtil {

    // we want to keep the method that getToken in here
    // so we don't have to copy paste anymore

    /**
     * A static utility method to get the token by providing username and password
     * as Post request to /Login endpoint and capture the token field from response json
     * @param username
     * @param password
     * @return the token as String from the response json
     */
    public static String loginAndGetToken(String username, String password) {
        Response response = given().

                contentType(ContentType.URLENC).
                formParam("email", username ).
                formParam("password", password).
                when().
                post("/login");


        JsonPath jsonPath = response.jsonPath();
        String token = jsonPath.getString("token");
        return token;
    }

}
