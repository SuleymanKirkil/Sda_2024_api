package day04;

import base_urls.RestFullBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import testdata.RestfulTestData;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.AssertJUnit.assertEquals;

public class C17_PostRequestNestedMapTestData extends RestFullBaseUrl {

    /*
    Given
        1) https://restful-booker.herokuapp.com/booking
        2) {
            "firstname": "John",
            "lastname": "Doe",
            "totalprice": 15,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2023-03-07",
                "checkout": "2024-09-25"
            },
            "additionalneeds": "Lunch"
           }
    When
        I send POST Request to the Url
    Then
        Status code is 200
        And response body should be like {
                                            "bookingid": 2243,
                                            "booking": {
                                                "firstname": "John",
                                                "lastname": "Doe",
                                                "totalprice": 471,
                                                "depositpaid": true,
                                                "bookingdates": {
                                                    "checkin": "2023-03-07",
                                                    "checkout": "2024-09-25"
                                                },
                                                "additionalneeds": "Lunch"
                                            }
                                        }
 */

    @Test
    public void postNestedMapTest(){
        // Set Url
        spec.pathParam("first","booking");

        // Set Expected Data:

        // While dealing with nested structures start from inner structure

        Map<String,Object> bookingdates= RestfulTestData.bookingDatesMapper("2023-03-07","2024-09-25");

        Map<String ,Object> payLoad = RestfulTestData.bookingMapper("John","Doe",15,true,bookingdates,"Lunch");

        // Send request and get response:
        Response response = given(spec).body(payLoad).when().post("{first}");
        response.prettyPrint();

        // Do assertions
        response
                .then()
                .body("booking.firstname",equalTo(payLoad.get("firstname")))
                .body("booking.bookingdates.checkin",equalTo(bookingdates.get("checkin")));


        Map<String,Object> actualData = response.as(Map.class);

        Object checkin =(((Map)((Map)(actualData.get("booking"))).get("bookingdates")).get("checkin"));

        assertEquals(200,response.statusCode());
        assertEquals(payLoad.get("firstname"), ((Map)(actualData.get("booking"))).get("firstname") );
        assertEquals(payLoad.get("lastname"), ((Map)(actualData.get("booking"))).get("lastname") );
        assertEquals(payLoad.get("totalprice"), ((Map)(actualData.get("booking"))).get("totalprice") );
        assertEquals(payLoad.get("depositpaid"), ((Map)(actualData.get("booking"))).get("depositpaid") );
        assertEquals(bookingdates.get("checkin"), checkin);
        assertEquals(bookingdates.get("checkin"), (((Map)((Map)(actualData.get("booking"))).get("bookingdates")).get("checkin")));
        assertEquals(bookingdates.get("checkout"), (((Map)((Map)(actualData.get("booking"))).get("bookingdates")).get("checkout")));
        assertEquals(payLoad.get("additionalneeds"), ((Map)(actualData.get("booking"))).get("additionalneeds") );

        JsonPath json = response.jsonPath();
        assertEquals(payLoad.get("lastname"), json.getString("booking.lastname") );

    }
}
