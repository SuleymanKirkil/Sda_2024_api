package day04;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class C21_DeleteRequest extends JsonPlaceHolderBaseUrl {
    /*
    Given
        https://jsonplaceholder.typicode.com/todos/198
    When
        I send DELETE Request to the Url
    Then
        Status code is 200
        And Response body is { }
*/
    @Test
    public void test(){
        //Set Url
        spec.pathParams("first","todos","second",198);

        // Set Expected Data

        // Send request and get response
        Response response = given(spec).when().delete("{first}/{second}");

        // Do assertions
        String responseStr = response.asString();
        System.out.println("responseStr = " + responseStr);

        assertEquals(200,response.statusCode());
        assertTrue(responseStr.equals("{}"));
    }
}
