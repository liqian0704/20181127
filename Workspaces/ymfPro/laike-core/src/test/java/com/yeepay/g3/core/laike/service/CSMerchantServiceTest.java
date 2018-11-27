package com.yeepay.g3.core.laike.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.yeepay.g3.common.laike.utils.config.ConfigEnum;
import com.yeepay.g3.common.laike.utils.config.ConfigUtils;
import com.yeepay.g3.core.laike.BaseTest;
import com.yeepay.g3.core.laike.biz.AllianceAccountBiz;
import com.yeepay.g3.core.laike.entity.AccountOpenEntity;
import com.yeepay.g3.core.laike.entity.AttachmentEntity;
import com.yeepay.g3.core.laike.entity.UserEntity;
import com.yeepay.g3.core.laike.utils.AttachmentsAttribute;
import com.yeepay.g3.core.laike.utils.ValidateParamUtil;
import com.yeepay.g3.facade.laike.dto.OpenAccountRequest;
import com.yeepay.g3.facade.laike.dto.OpenAccountResponse;
import com.yeepay.g3.facade.laike.enumtype.AccountType;
import com.yeepay.g3.facade.laike.enumtype.LOLOpenStatus;
import com.yeepay.g3.facade.laike.exception.*;
import com.yeepay.g3.facade.mer.dto.MerRespBaseDto;
import com.yeepay.g3.facade.mer.dto.request.MerSignTypeNetInDto;
import com.yeepay.g3.facade.mer.enumtype.MerRuleEnum;
import com.yeepay.g3.facade.mer.enumtype.MerSourceEnum;
import com.yeepay.g3.utils.common.StringUtils;
import com.yeepay.g3.utils.common.json.JSONUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.yeepay.g3.core.laike.service.AbstractService.merLaiKeExtensionFacade;

/**
 * Description:
 * Author: wei.li
 * Date: 17/6/20
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class CSMerchantServiceTest extends BaseTest {

    @Autowired
    private CSMerchantService csMerchantService;

    @Autowired
    private AccountOpenService accountOpenService;

    @Autowired
    private AttachmentService attachmentService;

    @Autowired
    private UserService userService;

    @Autowired
    private AllianceAccountBiz allianceAccountBiz;

    private static String REGIST_MEMBER_MAIL_SUFFIX = "@liker.com";//会员注册后缀

    private static String CALL_BACK_MER_URL = "liker";//入网中心回调

    private static String CS_MERCHANT_PARAMS_VALID = "REG30002";//入网中心参数异常，业务性强，不用放全局类维护

    private static String CS_MERCHANT_PARAMS_ILLEGAL = "REG30003";//入网中心参数验证失败，业务性强，不用放全局类维护

    private static String ID_CARD_VALID_DATE = "长期";

    private static String REGIST_LOL_MAIL_SUFFIX = "@lol.com";//展业注册后缀

    protected ErrorCodeTranslator errorCodeTranslator = SystemErrorCodeTranslator.getInstance();

    @Test
    public void gatherAllianceInfo() {
//        AccountOpenEntity entity = accountOpenService.findByMemberNo("212468327975");//212468327005---212468327975
//        AttachmentEntity attachmentEntity = attachmentService.findByAccount("ACA18050436718599");//ACA17061652367764--ACA18060267189193
//        csMerchantService.gatherAllianceInfo(entity, attachmentEntity);
        HashMap<String, String> map = (HashMap<String, String>) ConfigUtils.getSysConfigParam(ConfigEnum.LIKER_CS_MERCHANT_ERROR_KEY);
//        String microMerL1 = (String) ConfigUtils.getSysConfigParam(ConfigEnum.MICRO_MER_LEVEL1);
//        String microMerL2 = (String) ConfigUtils.getSysConfigParam(ConfigEnum.MICRO_MER_LEVEL2);
        MerSignTypeNetInDto requestDTO = new MerSignTypeNetInDto();

        requestDTO.setRequestNo("ACA18061567189191");
        requestDTO.setMerSource(MerSourceEnum.LOL);
        requestDTO.setMerRule(MerRuleEnum.TGY);
        requestDTO.setMerContactEmail("13689841022@lol.com");
        requestDTO.setMerFullName("李倩");
        requestDTO.setMerShortName("李倩");
        requestDTO.setLegalName("李倩");
        requestDTO.setLegalPersonId("220183198807041860");
        requestDTO.setLegalPersonIdLong(true);
        requestDTO.setMerContactPhone("13689841022");

        requestDTO.setMerLevel1No("107");
        requestDTO.setMerLevel2No("107019");
        requestDTO.setMerProvince("110000");
        requestDTO.setMerCity("110100");
        requestDTO.setMerDistrict("110101");
        requestDTO.setMerAddress("呼家楼街道金桐东路");
        requestDTO.setBankcardNo("6225880151791671");
        requestDTO.setHeadBankName("CMBCHINA");
        requestDTO.setBankName("308100005150");
        requestDTO.setBankProvince("110000");
        requestDTO.setBankCity("110100");
        requestDTO.setFileInfo("[{\"quaType\":\"IDCARD_FRONT\",\"quaUrl\":\"http://172.17.106.197:8081/ucm/201705/31253fa597584271aee669302a8115f4.jpeg\"},{\"quaType\":\"IDCARD_BACK\",\"quaUrl\":\"http://172.17.106.197:8081/ucm/201705/77bc48da63e940ddbf1db72d00c16cab.jpeg\"},{\"quaType\":\"SETTLE_BANKCARD\",\"quaUrl\":\"http://172.17.106.197:8081/ucm/201705/81e6668019d74d8789022d4360f24563.jpeg\"},{\"quaType\":\"HAND_IDCARD\",\"quaUrl\":\"http://172.17.106.197:8081/ucm/201705/fd12a0b89e584014b5821783aa40fc95.jpeg\"},{\"quaType\":\"DZXY\",\"quaUrl\":\"http://172.17.106.197:8081/ucm/201705/a8d6921602ef493095a1b9ae5d36c980.jpeg\"}]");
        MerRespBaseDto responseDTO = merLaiKeExtensionFacade.personNetInWorkFlow(requestDTO);
        if (responseDTO.getReturnCode().equals(map.get(ExternalReturnCode.CS_MERCHANT_SUCCESS_CODE))) {
        } else {
            //检查特殊返回码
            filteSpecialCode(responseDTO.getReturnCode(), responseDTO.getReturnMsg());
            throw new LaikeSysException(errorCodeTranslator.translateCode(ErrorCodeSource.CS_MERCHANT,
                    responseDTO.getReturnCode(), responseDTO.getReturnMsg()));
        }
    }


    /**
     * 附件信息转客户中心要求的json格式
     *
     * @param attachmentEntity
     * @return
     */
    private String convert2Json(AccountOpenEntity accountOpenEntity, AttachmentEntity attachmentEntity) {
        List<Map<String, String>> list = Lists.newArrayList();
        Field[] fields = attachmentEntity.getClass().getDeclaredFields();
        for (Field field : fields) {
            AttachmentsAttribute attribute = field.getAnnotation(AttachmentsAttribute.class);
            if (null != attribute) {
                Map<String, String> map = Maps.newHashMap();
                try {
                    field.setAccessible(true);
                    String quaUrl = (String) field.get(attachmentEntity);
                    if (StringUtils.isBlank(quaUrl)) {
                        continue;
                    }
                    if (attribute.fieldName().equals("DZXY") && AccountType.LK.equals(accountOpenEntity.getAccountType())) {
                        continue;
                    }
                    map.put("quaType", attribute.fieldName());
                    map.put("quaUrl", quaUrl);
                    list.add(map);
                } catch (IllegalAccessException e) {
                    throw new LaikeSysException(ErrorCode.ATTACH_ATTRIBUTE_ILLEGAL, e);
                }
            }
        }
        return JSONUtils.toJsonString(list);
    }

    private void filteSpecialCode(String returnCode, String returnMsg) {
        HashMap<String, String> map = (HashMap<String, String>) ConfigUtils.getSysConfigParam(ConfigEnum.LIKER_CS_MERCHANT_ERROR_KEY);
        if (returnCode.equals(map.get(CS_MERCHANT_PARAMS_VALID))
                || returnCode.equals(map.get(CS_MERCHANT_PARAMS_ILLEGAL))) {
            throw new LaikeSysException(returnCode, returnMsg);
        }
    }


    @Test
    public void test() {
        AccountOpenEntity entity = accountOpenService.findByMemberNo("212468327015");//212468327005---212468327975
        AttachmentEntity attachmentEntity = attachmentService.findByAccount("ACA17062736718549");//ACA17061652367764--ACA18060267189193
        csMerchantService.gatherAllianceInfo(entity, attachmentEntity);
    }

    @Test
    public void test1() {
        AccountOpenEntity entity = accountOpenService.findByMemberNo("212468316583");//212468327015---212468327975
        AttachmentEntity attachmentEntity = attachmentService.findByAccount("ACA17032723671351");//ACA17061652367764--ACA18060267189193
        System.err.println(new Gson().toJson(convert2Json(entity, attachmentEntity)));
    }

    //    @Rollback(false)
    @Test
    public void test2() {
        UserEntity userEntity = userService.findRegisterByMember("212468327015");
        AccountOpenEntity accountOpenEntity = accountOpenService.findExistById(userEntity.getAccountId());
        AttachmentEntity attachmentEntity = attachmentService.findByAccount(accountOpenEntity.getId());
//        notifyAlliance(accountOpenEntity, userEntity);
//
        OpenAccountResponse response = new OpenAccountResponse();
        try {
            if (null != attachmentEntity) {
                BeanUtils.copyProperties(response, attachmentEntity);
            }
            if (null != accountOpenEntity) {
                BeanUtils.copyProperties(response, accountOpenEntity);
            }
            if (null != userEntity) {
                BeanUtils.copyProperties(response, userEntity);
                if (StringUtils.isBlank(userEntity.getInviteCode())) {
                    response.setInviteCode(null);
                }
//                if (StringUtils.isBlank(userEntity.getMerchantNo())) {
//                    response.setMerchantNo(accountOpenEntity.getMerchantNo());
//                }
                System.err.println(response.getAllianceInviteCode() + response.getMerchantNo());
            }
        } catch (Exception e) {
            throw new LaikeSysException(ErrorCode.ACCOUNT_CONVERT_DTO_EXCEP, e);
        }

    }


    private void notifyAlliance(AccountOpenEntity accountOpenEntity, UserEntity userEntity) {
        userService.updateAllianceCodeAndMerNo(userEntity, accountOpenEntity.getMerchantNo(), "test111", accountOpenEntity.getAgentNo());
        accountOpenEntity.setLolOpenStatus(LOLOpenStatus.SUCCESS);
        accountOpenService.createAndUpdate(accountOpenEntity);
    }

    @Test
    public void openAllianceAccount() {
        OpenAccountRequest request = new OpenAccountRequest();
        request.setMemberNo("212468327121");
        request.setLegalName("李倩");
        request.setLegalIdCard("220183198807041860");
        request.setSettleCardNo("6225880151791671");
        request.setSettleCardName("李倩");
        request.setSettleBankCode("CMBCHINA");
        request.setSettleBankName("招商银行");
        request.setBranchBankCode("308100005019");
        request.setBranchBankName("招商银行股份有限公司北京北三环支行");
        request.setMerProvince("110000");
        request.setMerProvinceName("北京");
        request.setMerCity("110100");
        request.setMerCityName("北京");
        request.setMerDistrictName("北京金台路");
        request.setMerDistrict("110101");
        request.setMerAddress("北京金台路");
        request.setSettleBankCode("CMBCHINA");
        request.setSettleBankName("招商银行");
        request.setBranchBankCode("308100005019");
        request.setBankProvinceCode("01");
        request.setBankProvinceName("北京");
        request.setBankCityCode("01");
        request.setBankCityName("北京");
        allianceAccountBiz.openAllianceAccount(request);
    }

    @Test
    public void testIp() {
        System.err.println(ValidateParamUtil.isIp(null));
        System.err.println(ValidateParamUtil.isIp(""));
    }

    @Test
    public void testIO() {
        OutputStream outputStream = new OutputStream() {
            @Override
            public void write(int b) throws IOException {

            }
        };
    }
}
