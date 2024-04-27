package day03;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import testdata.JsonPlaceHolderTestData;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class C14_PatchRequest extends JsonPlaceHolderBaseUrl {

        /*
        Given
            1) https://jsonplaceholder.typicode.com/todos/198
            2) {
                 "title": "Read Books"
               }
        When
            I send PATCH Request to the Url
        Then
           Status code is 200
           And response body is like  {
                                            "userId": 10,
                                            "id": 198,
                                            "title": "Read Books",
                                            "completed": true
                                        }
    */

    @Test
    public void patchRequestTest(){
        // Set Url
        spec.pathParams("first","todos","second",198);

        // Set Expected Data

        Map<String,Object> payload =JsonPlaceHolderTestData.jsonPlaceHolderMapper(null,"Read Books",null) ;
        System.out.println("payload = " + payload);

        Map<String,Object> expectedData =JsonPlaceHolderTestData.jsonPlaceHolderMapper(10,"Read Books",true) ;
        expectedData.put("id",198);
        System.out.println("expectedData = " + expectedData);

        // sent Request and get response
        Response response = given(spec).body(payload).when().patch("{first}/{second}");
        response.prettyPrint();


        Map<String, Object> actualData = response.as(Map.class);
        assertEquals(200,response.statusCode());
        assertEquals(expectedData.get("userId"),actualData.get("userId"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("completed"),actualData.get("completed"));
        assertEquals(expectedData.get("id"),actualData.get("id"));
    }
}
