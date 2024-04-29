package day05;

import base_urls.JsonPlaceHolderBaseUrl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static com.fasterxml.jackson.databind.type.LogicalType.Map;
import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class C22_ObjectMapperMap extends JsonPlaceHolderBaseUrl {

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
        Map<String,Object> payLoad = objectMapper.readValue(expectedStr,Map.class);

        // Send request and get response
        Response response = given(spec).body(payLoad).when().post("{first}");
        response.prettyPrint();

        // Do assertions
        Map<String,Object> actualData =objectMapper.readValue(response.asString(),Map.class);

        assertEquals(201,response.statusCode());
        assertEquals(payLoad.get("userId"),actualData.get("userId"));
        assertEquals(payLoad.get("title"),actualData.get("title"));
        assertEquals(payLoad.get("completed"),actualData.get("completed"));
    }

}
