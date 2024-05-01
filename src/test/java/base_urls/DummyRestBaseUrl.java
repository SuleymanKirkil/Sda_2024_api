package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;

public class DummyRestBaseUrl {

    protected RequestSpecification spec;
    @BeforeMethod
    public void setUp(){
        spec = new RequestSpecBuilder()
                    .setBaseUri("https://dummy.restapiexample.com/api/v1")
                    .setContentType(ContentType.JSON)
                    .build();
    }
}
