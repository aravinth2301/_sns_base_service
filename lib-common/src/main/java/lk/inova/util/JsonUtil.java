package lk.inova.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

	private static ObjectMapper objMapper = new ObjectMapper(); 
	
	
	public static String parseToString(Object obj){
		
		
		try {
			return objMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "";	
		}
	}
}
