package day05;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static utilities.ObjectMapperUtilities.convertJsonToJava;

public class C26_RevisionExample extends JsonPlaceHolderBaseUrl {
        /*
   Given
       https://jsonplaceholder.typicode.com/todos
   When
  I send a GET request to the Url
And
    Accept type is “application/json”
Then
    HTTP Status Code should be 200
And
       There must be a todo like:
           {
               "userId": 1,
               "id": 4,
               "title": "et porro tempora",
               "completed": true
           }
    */
    @Test
    public void test(){
        // Set Url
        spec.pathParam("first","todos");

        // Set Expected Data
        String expectedStr = """
                {
                               "userId": 1,
                               "id": 4,
                               "title": "et porro tempora",
                               "completed": true
                           }""";
        Map<String,Object> expectedTodo = convertJsonToJava(expectedStr, Map.class);

        // Send Request and Get Response
        Response response = given(spec).when().get("{first}");
        //response.prettyPrint();

        // Do assertions
        JsonPath json = response.jsonPath();

        Object userId = json.getList("findAll{it.id==4}.userId").get(0);
        Object title = json.getList("findAll{it.id==4}.title").get(0);
        Object id = json.getList("findAll{it.id==4}.id").get(0);
        Object completed = json.getList("findAll{it.id==4}.completed").get(0);

        assertEquals(expectedTodo.get("userId"),userId);
        assertEquals(expectedTodo.get("title"),title);
        assertEquals(expectedTodo.get("id"),id);
        assertEquals(expectedTodo.get("completed"),completed);

        // 2nd way
        Object actualTodo = json.getList("findAll{it.id==4}").get(0);
        System.out.println("actualTodo = " + actualTodo);

        Map<String,Object> actualTodoMap = (Map)actualTodo;

        assertEquals(expectedTodo.get("userId"),actualTodoMap.get("userId"));
        assertEquals(expectedTodo.get("title"),actualTodoMap.get("title"));
        assertEquals(expectedTodo.get("id"),actualTodoMap.get("id"));
        assertEquals(expectedTodo.get("completed"),actualTodoMap.get("completed"));

    }
}
