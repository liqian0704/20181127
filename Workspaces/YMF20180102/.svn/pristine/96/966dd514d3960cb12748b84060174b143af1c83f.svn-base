package com.yeepay.g3.core.ymf.support;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * Title: BigDecimal输出plainString
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/9/8.
 */
@JacksonStdImpl
public class JacksonBigDecimalSerializer extends StdSerializer<BigDecimal> {

    public final static JacksonBigDecimalSerializer instance = new JacksonBigDecimalSerializer();

    private JacksonBigDecimalSerializer() {
        super(BigDecimal.class);
    }
    @Override
    public void serialize(BigDecimal value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
        jgen.writeString(value.toPlainString());
    }
}