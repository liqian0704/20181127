package com.yeepay.g3.core.ymf;

import com.caucho.hessian.client.HessianProxyFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yeepay.g3.facade.alliance.common.ErrorCode;
import com.yeepay.g3.facade.alliance.dto.common.ResponseMessage;
import com.yeepay.g3.facade.alliance.dto.member.InviteCodeReq;
import com.yeepay.g3.facade.alliance.dto.member.RegisterReq;
import com.yeepay.g3.facade.alliance.enums.member.MerType;
import com.yeepay.g3.facade.alliance.enums.member.RegisterSource;
import com.yeepay.g3.facade.alliance.facade.member.RegisterFacade;
import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Title:
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 2017/6/19.
 */
public class AllianceTest {

    private RegisterFacade registerFacade;

    @Before
    public void setUp() {
        HessianProxyFactory factory = new HessianProxyFactory();
        try {
            registerFacade = (RegisterFacade) factory.create(RegisterFacade.class, "http://localhost:8080/alliance-hessian/hessian/RegisterFacade");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void validateInviteCode() throws Exception {
        InviteCodeReq req = InviteCodeReq.InviteCodeReqBuilder.builder()
                .inviteCode("sdfds")
                .build();
        ResponseMessage resp = registerFacade.validateInviteCode(req);
        deepPrint(resp);
        assertEquals(ErrorCode.ALC1001, resp.getCode());
    }

    @Test
    public void validateInviteCodeExist() throws Exception {
        InviteCodeReq req = InviteCodeReq.InviteCodeReqBuilder.builder()
                .inviteCode("Y573464")
                .build();
        ResponseMessage resp = registerFacade.validateInviteCode(req);
        deepPrint(resp);
        assertEquals(ErrorCode.ALC0000, resp.getCode());
    }

    @Test(expected = RuntimeException.class)
    public void validateInviteCodeNullParam() throws Exception {
        InviteCodeReq req = InviteCodeReq.InviteCodeReqBuilder.builder()
                .inviteCode(null)
                .build();
        registerFacade.validateInviteCode(req);
    }

    @Test
    public void registerMember() throws Exception {
        RegisterReq registerReq = RegisterReq.RegisterReqBuilder.builder()
                .customerName("测试盟主1")
                .customerNumber("10040011542")
                .registerTime(new Date())
                .merType(MerType.LORD)
                .source(RegisterSource.TEST)
                .build();
        ResponseMessage resp = registerFacade.registerMember(registerReq);
        deepPrint(resp);
        assertEquals(ErrorCode.ALC0000, resp.getCode());
    }

    @Test
    public void registerMemberAlly() throws Exception {
        RegisterReq registerReq = RegisterReq.RegisterReqBuilder.builder()
                .customerName("测试盟友1")
                .customerNumber("10040011543")
                .registerTime(new Date())
                .merType(MerType.ALLY)
                .source(RegisterSource.TEST)
                .inviteCode("Y573464")
                .build();
        ResponseMessage resp = registerFacade.registerMember(registerReq);
        deepPrint(resp);
        assertEquals(ErrorCode.ALC0000, resp.getCode());
    }

    @Test
    public void registerMemberChild() throws Exception {
        RegisterReq registerReq = RegisterReq.RegisterReqBuilder.builder()
                .customerName("测试组商户1")
                .customerNumber("10040011544")
                .registerTime(new Date())
                .merType(MerType.CHILD)
                .source(RegisterSource.TEST)
                .inviteCode("Y573465")
                .build();
        ResponseMessage resp = registerFacade.registerMember(registerReq);
        deepPrint(resp);
        assertEquals(ErrorCode.ALC0000, resp.getCode());
    }

    /**
     * deep print
     * @param obj
     */
    protected void deepPrint(Object obj) {
        if (obj instanceof Collection<?>) {
            Collection<?> collection = (Collection<?>) obj;
            for (Object o : collection) {
                deepPrint(o);
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
