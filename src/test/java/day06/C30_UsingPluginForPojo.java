package day06;

import base_urls.GorestBaseUrl;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class C30_UsingPluginForPojo extends GorestBaseUrl {
    /*
Given
    https://gorest.co.in/public/v1/users?id=6743944
When
    User sends GET request
Then
    HTTP Status Code should be 200
And
    Response body should be like:
    {
        "meta": {
            "pagination": {
                "total": 1,
                "pages": 1,
                "page": 1,
                "limit": 10,
                "links": {
                    "previous": null,
                    "current": "https://gorest.co.in/public/v1/users?page=1",
                    "next": null
                }
            }
        },
        "data": [
            {
                "id": 6743944,
                "name": "Aagneya Asan",
                "email": "aagneya_asan@ryan.test",
                "gender": "female",
                "status": "inactive"
            }
        ]
    }
 */


    @Test
    public void test(){
        spec.pathParam("first","users")
                .queryParam("id","6880236");

        String expected ="";

        given(spec).when().get("{first}").prettyPrint();
    }
}
