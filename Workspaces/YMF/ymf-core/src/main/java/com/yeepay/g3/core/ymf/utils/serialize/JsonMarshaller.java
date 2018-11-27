package com.yeepay.g3.core.ymf.utils.serialize;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class JsonMarshaller {
    private ObjectMapper objectMapper;

    private JsonMarshaller() {
        objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true); // DATE按时间戳输出
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true); // 标准json的字符串是双引号,这里启用单引号兼容
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL); // 不序列化null字段
    }

    private static Log log = LogFactory.getLog(JsonMarshaller.class);
    private static JsonMarshaller jsonMarshaller;

    public static JsonMarshaller getMarshaller() {
        if (null == jsonMarshaller) {
            jsonMarshaller = new JsonMarshaller();
        }
        return jsonMarshaller;
    }

    public String jsonMarshaller(Object obj) {
        String result = "";
        try {
            result = objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            log.error("### jsonMarshaller error : obj = " + obj.toString());
        }
        return result;
    }

    public <T> T jsonUnMarshaller(String jsonStr, Class<T> clazz) {
        T result = null;
        try {
            result = objectMapper.readValue(jsonStr, clazz);
        } catch (Exception e) {
            log.error("### jsonUnMarshaller error : json = " + jsonStr);
        }
        return result;
    }

}
