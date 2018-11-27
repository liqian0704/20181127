package com.yeepay.g3.core.ymf.junit.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.yeepay.g3.core.ymf.support.JacksonBigDecimalSerializer;
import com.yeepay.g3.core.ymf.support.JacksonNullSerializer;
import com.yeepay.g3.facade.ymf.dto.common.ResponseMessage;
import com.yeepay.g3.facade.ymf.validator.annotations.NotNull;
import org.junit.Test;

import javax.script.Bindings;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleBindings;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;

/**
 * Title:
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/24.
 */
public class JacksonTest {

    @Test
    public void testNull() {
        ResponseMessage msg = ResponseMessage.error("sdjfkl");
        ObjectMapper om = new ObjectMapper();
        try {
            System.out.println(om.writeValueAsString(msg));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testClazzName() {
        System.out.println(NotNull.class.getSimpleName());
    }

    @Test
    public void testBigDecimal() {
        ObjectMapper om = new ObjectMapper();
        om.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        // null show null string
        om.getSerializerProvider().setNullValueSerializer(JacksonNullSerializer.instance);
        SimpleModule module = new SimpleModule();
        module.addSerializer(JacksonBigDecimalSerializer.instance);
        om.registerModule(module);
        BigDecimal bigDecimal = new BigDecimal("0.10");
        try {
            System.out.println(om.writeValueAsString(bigDecimal));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1() {
        ScriptEngineManager engineManager = new ScriptEngineManager();
        ScriptEngine engine = engineManager.getEngineByName("JavaScript");

        Bindings bindings = new SimpleBindings();
        bindings.put("x", 1500);
        bindings.put("y", 100);
        bindings.put("z", 1);

        bindings.put("msg", "just a test");
        String evals = ("msg += '!!!';var user = {name:'tom',age:23,hobbies:['football','basketball']}; var name = user.name");

        String func = " function share(x,y,z) {" +
                "   var num = 0; " +
                "       if (z) { " +
                "               if (x < 1000) { num = y * 0.8; " +
                "                }  else if (x >= 1000 && x < 2000) " +
                "                   {  num = y * 0.9 } " +
                "            }  return num   " +
                "   }   ";

        String c = "function add(){if(x>1)})";
        try {
            engine.eval(func);
            Invocable invocable = (Invocable) engine;
//            System.out.println(engine.eval(func, bindings));
            int count =0;
            long start = System.nanoTime();
            while (count < 100000) {
                invocable.invokeFunction("share", 1500, 100, 1);
                count++;
            }
            long end = System.nanoTime();
            long total = end - start;
            System.out.println("total:" + total + ": avg:" + total / 100000);
        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
