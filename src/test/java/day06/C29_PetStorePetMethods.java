package day06;

import base_urls.PetStoreBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.petstore.Category;
import pojos.petstore.PetStoreResponse;
import pojos.petstore.TagsItem;
import utilities.ObjectMapperUtilities;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static utilities.ObjectMapperUtilities.convertJsonToJava;

public class C29_PetStorePetMethods extends PetStoreBaseUrl {
    //Write an automation test that will create, read, update, delete a 'pet' using the "https://petstore.swagger.io/" document
//(All actions must be done on same pet)
          //  (Use Pojo)

    int id =136;
    PetStoreResponse payload;
    @Test
    public void createPetTest(){
        // Set Url
        spec.pathParam("first","pet");

        // Set Expected Data
         // 1st Way by Object Mapper

        String payloadStr = """
                {
                  "id": 136,
                  "category": {
                    "id": 3,
                    "name": "Doggie"
                  },
                  "name": "Aldo",
                  "photoUrls": [
                    "string","another"
                  ],
                  "tags": [
                    {
                      "id": 1,
                      "name": "Cute"
                    },
                    {
                      "id": 2,
                      "name": "Cheap"
                    }
                  ],
                  "status": "available"
                }""";
        payload =convertJsonToJava(payloadStr, PetStoreResponse.class);

        // 2nd way:

        TagsItem tag1= new TagsItem("Cute",1);
        TagsItem tag2= new TagsItem("Cheap",2);
        List<TagsItem> tags = new ArrayList<>();
        tags.add(tag1);
        tags.add(tag2);

        List<String> urls = new ArrayList<>();
        urls.add("string");
        urls.add("another");

        Category category = new Category("Doggie",3);
        PetStoreResponse payload2 =new PetStoreResponse(urls,"Aldo",id,category,tags,"available");


        // Send Request and Get Response
        Response response = given(spec).body(payload2).when().post("{first}");
        response.prettyPrint();

        response.then().statusCode(200);

    }

    @Test(dependsOnMethods = "createPetTest",priority = 1)
    public void getPetTest(){
        // Set Url
        spec.pathParams("first","pet"
        ,"second",id);

        // Sent Request Get Response
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();
        response.then().statusCode(200);
 }

    @Test(dependsOnMethods = "createPetTest",priority = 2)
    public void updatePetTest(){
        // Set Url
        spec.pathParam("first","pet");

        // Set Expected data
        payload.setName("Aslan");
        payload.getTags().get(0).setName("Sick");
        payload.setStatus("pending");

       // System.out.println("payload = " + payload);
        // Send Request and Get Response
        Response response = given(spec).body(payload).  put("{first}");
        response.prettyPrint();

    }
    @Test(dependsOnMethods = "createPetTest",priority = 3)
    public void deletePetTest(){
        spec.pathParams("first","pet","second",id);

        Response response = given(spec).when().delete("{first}/{second}");
        response.prettyPrint();
        response.then().statusCode(200);

    }
}
