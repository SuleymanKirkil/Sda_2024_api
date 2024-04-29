package day05;

import base_urls.JsonPlaceHolderBaseUrl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.JsonPlaceHolderPojo;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.oauth;
import static org.testng.AssertJUnit.assertEquals;

public class C23_ObjectMapperWithPojo extends JsonPlaceHolderBaseUrl {

    /*
         Given
           1) https://jsonplaceholder.typicode.com/todos
           2) {
                "userId": 55,
               "title": "Tidy your room",
               "completed": false
               }
        When
            I send POST Request to the Url
        Then
            Status code is 201
        And
            response body is like {
                                    "userId": 55,
                                    "title": "Tidy your room",
                                    "completed": false,
                                    "id": 201
                                    }

Note: Use map and POJO seperately
*/
    @Test
    public void test() throws JsonProcessingException {
        // Set Url
        spec.pathParam("first","todos");

        // Set Expected Data:

        //JsonPlaceHolderTestData.jsonPlaceHolderMapper(55,"Tidy your room",false);

        String expectedStr = """
                {
                   "userId": 55,
                   "title": "Tidy your room",
                   "completed": false
                   }""";

        ObjectMapper objectMapper = new ObjectMapper();
        JsonPlaceHolderPojo payLoad = objectMapper.readValue(expectedStr, JsonPlaceHolderPojo.class);

        // Send request and get response
        Response response = given(spec).body(payLoad).when().post("{first}");
        response.prettyPrint();

        // Do assertions
        JsonPlaceHolderPojo actualData =objectMapper.readValue(response.asString(),JsonPlaceHolderPojo.class);

        assertEquals(201,response.statusCode());
        assertEquals(payLoad.getUserId(),actualData.getUserId());
        assertEquals(payLoad.getTitle(),actualData.getTitle());
        assertEquals(payLoad.getCompleted(),actualData.getCompleted());


    }

}
