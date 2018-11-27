package com.yeepay.g3.core.ymf.junit.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.yeepay.g3.core.ymf.support.JacksonBigDecimalSerializer;
import com.yeepay.g3.core.ymf.support.JacksonNullSerializer;
import com.yeepay.g3.facade.ymf.dto.common.ResponseMessage;
import com.yeepay.g3.facade.ymf.validator.annotations.NotNull;
import org.junit.Test;

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
}
