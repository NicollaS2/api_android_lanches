
package App.Class.RestAPI.Config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;


public class Json {
    
    public static String toJson(Object obj) throws JsonProcessingException{
        
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }
    
    public static <T extends Object> T toObject(String json, Class<T> objClass) throws JsonProcessingException, IOException{
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, objClass);
    }
    
}
