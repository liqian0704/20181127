package com.yeepay.g3.core.ymf.utils.serialize;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

public class JsonMarshaller {
	private ObjectMapper marshaller;
	private JsonMarshaller(){
			marshaller = new ObjectMapper();
			marshaller.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, true);
			marshaller.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
			marshaller.disable(SerializationConfig.Feature.WRITE_NULL_PROPERTIES);
	}
	private static Log log = LogFactory.getLog(JsonMarshaller.class);
	private  static JsonMarshaller jsonMarshaller;
	public static JsonMarshaller getMarshaller(){
		if(null == jsonMarshaller){
			jsonMarshaller = new JsonMarshaller();
		}
		
		return jsonMarshaller;
	}
	
	public String jsonMarshaller(Object obj){
		String result = "";
		try {
			 result =  marshaller.writeValueAsString(obj);
		} catch (Exception e) {
			log.error("### jsonMarshaller error : obj = "+obj.toString());
		}
		return result;
	}
	public <T> T jsonUnMarshaller(String jsonStr,Class<T> clazz){
		T result = null;
		try {
			result = marshaller.readValue(jsonStr, clazz);
		} catch (Exception e) {
			log.error("### jsonUnMarshaller error : json = "+jsonStr);
		}
		return result;
	}
	
}
