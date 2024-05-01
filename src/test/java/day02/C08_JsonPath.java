package day02;

import base_urls.RestFullBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.AssertJUnit.assertEquals;

public class C08_JsonPath extends RestFullBaseUrl {
        /*
    Given
        https://restful-booker.herokuapp.com/booking/598
    When
        User send a GET request to the URL
    Then
        HTTP Status Code should be 200
    And
        PetStoreResponse content type is “application/json”
    And
        PetStoreResponse body should be like;
        {
            "firstname": "Josh",
            "lastname": "Allen",
            "totalprice": 111,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2018-01-01",
                "checkout": "2019-01-01"
            },
            "additionalneeds": "super bowls"
        }
*/

    @Test
    public void jsonPathTest() {

        //Set the Url
        spec.pathParams("first","booking"
                    ,"second",30);
        //Set the expected data


        //Send the request and get the response
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();
        //Do assertion
        //1st way: then() method with hamcrest matchers
        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstname",equalTo("Josh"))
                .body("lastname",equalTo("Allen"))
                .body("totalprice",equalTo(111))
                .body("depositpaid",equalTo(true))
                .body("bookingdates.checkin",equalTo("2018-01-01"))
                .body("bookingdates.checkout",equalTo("2019-01-01"))
                .body("additionalneeds",equalTo("super bowls"));


        //2nd way: By extracting data outside the body with JSONPath
        //Convert PetStoreResponse to JsonPath object
        JsonPath json =response.jsonPath();

        //Retrieve the desired data by using JsonPath object
        String firstname = json.getString("firstname");
        String lastname = json.getString("lastname");
        int totalprice = json.getInt("totalprice");
        boolean depositpaid = json.getBoolean("depositpaid");
        String  checkin = json.getString("bookingdates.checkin");
        String  checkout = json.getString("bookingdates.checkout");
        String additionalneeds = json.getString("additionalneeds");


        assertEquals("Josh",firstname);//If this assertion fails, the subsequent lines won't execute. Because this is Hard Assertion.
        assertEquals("Allen",lastname);
        assertEquals(111,totalprice);
        assertEquals(true,depositpaid);
        assertEquals("2018-01-01",checkin);
        assertEquals("2019-01-01",checkout);
        assertEquals("super bowls",additionalneeds);

        //How to use Soft Assertion
        //1st step: Create SoftAssert object
        SoftAssert softAssert = new SoftAssert();
        //2nd step: Do assertion
        softAssert.assertEquals("Josh",firstname); //If this assertion fails, the subsequent lines will execute as well. Because this is Soft Assertion.
        softAssert.assertEquals("Allen",lastname);
        softAssert.assertEquals(111,totalprice);
        softAssert.assertEquals(true,depositpaid);

        //3rd step: Use assertAll() method.
        softAssert.assertAll();

        //Not: Hard assertion is used commonly in market. Because if any failure occurs we must fix it immediately.

    }
}
