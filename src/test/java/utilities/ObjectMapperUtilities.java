package utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import pojos.BookingPojo;

public class ObjectMapperUtilities {
    // <T> T ---> means any data type
    public static <T> T convertJsonToJava(String json,Class<T> cls){
        try {
            return new ObjectMapper().readValue(json,cls);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);

        }
    }
}
