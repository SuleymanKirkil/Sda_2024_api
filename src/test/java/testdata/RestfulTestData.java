package testdata;

import java.util.HashMap;
import java.util.Map;

public class RestfulTestData {

    public static Map<String, Object> bookingDatesMapper(String checkin,String checkout){
        Map<String,Object> bookingdates=  new HashMap<>();
        bookingdates.put("checkin",checkin);
        bookingdates.put("checkout",checkout);
        return bookingdates;
    }

    public static Map<String, Object> bookingMapper(String firstname,String lastname,Integer totalprice,Boolean depositpaid, Map<String,Object> bookingdates,String additionalneeds){
        Map<String ,Object> payLoad = new HashMap<>();
        payLoad.put("firstname",firstname);
        payLoad.put("lastname",lastname);
        payLoad.put("totalprice",totalprice);
        payLoad.put("depositpaid",depositpaid);
        payLoad.put("bookingdates",bookingdates);
        payLoad.put("additionalneeds",additionalneeds);
        return payLoad;
    }
}
