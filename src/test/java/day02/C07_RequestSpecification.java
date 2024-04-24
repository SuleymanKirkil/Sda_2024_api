package day02;

import base_urls.RestFullBaseUrl;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.AssertJUnit.assertTrue;

public class C07_RequestSpecification extends RestFullBaseUrl {

     /*
    Given
       https://restful-booker.herokuapp.com/booking
    When
       User sends a GET request to the URL
    Then
       Status code is 200
    And
       Among the data, there should be someone whose first name is "John" and last name is "Smith"
*/

    @Test
    public void queryParametersTest() {

//        1. Set the URL
        spec.pathParam("first","booking")
                .queryParams("firstname","John"
                            ,"lastname","Smith");


//        2. Set the expected data

//        3. Send the request and get the response
        Response response = given(spec).when().get("{first}");
        response.prettyPrint();
//        4. Do Assertion

        //1st way:

        response
                .then()
                .statusCode(200);

        String responseStr = response.asString();
        assertTrue(responseStr.contains("bookingid"));

        //2nd way:
        response
                .then()
                .statusCode(200)
                .body(containsString("bookingid"))
                .body("bookingid",hasSize(greaterThan(0)));
    }
}
