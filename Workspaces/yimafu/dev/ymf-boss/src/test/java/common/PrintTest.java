package common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;
import java.util.Collection;

/**
 * Title:
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/29.
 */
public class PrintTest {

    /**
     * deep print
     * @param obj
     */
    protected void deepPrint(Object obj) {
        if (obj instanceof Collection<?>) {
            Collection<?> collection = (Collection<?>) obj;
            for (Object o : collection) {
                jsonPrint(o);
            }
        } else {
            jsonPrint(obj);
        }
    }

    /**
     *  json print
     * @param obj
     */
    protected void jsonPrint(Object obj) {
        ObjectMapper om = new ObjectMapper();
        try {
            om.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.ssssss"));
            System.out.println(om.writerWithDefaultPrettyPrinter().writeValueAsString(obj));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
