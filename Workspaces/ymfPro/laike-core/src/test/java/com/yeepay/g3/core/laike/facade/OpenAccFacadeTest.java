package com.yeepay.g3.core.laike.facade;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.yeepay.g3.core.laike.BaseTest;
import com.yeepay.g3.facade.laike.dto.*;
import com.yeepay.g3.facade.laike.enumtype.CompanyTypeEnum;
import com.yeepay.g3.facade.laike.enumtype.InviteType;
import com.yeepay.g3.facade.laike.facade.LikerCallbackFacade;
import com.yeepay.g3.facade.laike.facade.OpenAccountFacade;
import com.yeepay.g3.facade.laike.facade.app.AllianceAccountFacade;
import com.yeepay.g3.facade.laike.facade.app.AppOpenAccountFacade;
import com.yeepay.g3.facade.laike.facade.app.LikerProductionFacade;
import junit.framework.Assert;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertTrue;

/**
 * Description: OpenAccFacadeTest
 * Author: jiawen.huang
 * Date: 16/11/17
 * Time: 16:56
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class OpenAccFacadeTest extends BaseTest {

	@Autowired
	private OpenAccountFacade openAccountFacade;

	@Autowired
    private AppOpenAccountFacade appOpenAccountFacade;

    @Autowired
    private LikerCallbackFacade likerCallbackFacade;

	@Autowired
	private AllianceAccountFacade allianceAccountFacade;

    @Autowired
    private LikerProductionFacade likerProductionFacade;

	@Rollback(false)
	@Test
	public void checkInviteType() {
		OpenAccountRequest request1 = new OpenAccountRequest();
		request1.setMemberNo("212468314398");
		request1.setInviteType(InviteType.INVITECODE);
		request1.setInviteCode("105333");
//		request1.setInviteCode("105369");
		OpenAccountResponse response = openAccountFacade.checkInviteType(request1);
		System.out.print(new Gson().toJson(response));
		Assert.assertTrue(response.getStatus().equals(ResponseStatus.SUCCESS));
	}

	//	@Rollback(false)
	@Test
	public void gatherBaseInfo() {
		OpenAccountRequest request1 = new OpenAccountRequest();
        request1.setMemberNo("212468316295");// 312468314686
        request1.setInviteType(InviteType.SIGNEDPAPER);
        request1.setInviteCode("107378");
        request1.setCompanyType(CompanyTypeEnum.MICRO);
        openAccountFacade.checkInviteType(request1);
        OpenAccountRequest request = new OpenAccountRequest();
        request.setMemberNo("212468316295");
        request.setMerchantName("台签测试");
        request.setBizNoEnd("2018-01-01");
		request.setOrgNoEnd("2018-01-01");
		request.setTaxNo("税务登记证");
		request.setAccountLicense("开户许可证");
		request.setOrgNo("组织机构代码证");
        request.setCompanyType(CompanyTypeEnum.MICRO);
//		request.setBizNo("110228015878409");
        request.setLegalName("赵高卓");
        request.setLegalIdCard("431381198908223477");
        request.setLegalIdEnd("2020-01-01");
        request.setMerLevel1No("007");
		request.setMerLevel2No("007001");
		request.setMerProvince("110000");
		request.setMerCity("110100");
		request.setMerDistrict("110101");
		request.setMerAddress("万通中心D座23层");
		OpenAccountResponse response = openAccountFacade.gatherBaseInfo(request);
		System.out.print(new Gson().toJson(response));
		Assert.assertTrue(response.getStatus().equals(ResponseStatus.SUCCESS));
	}

	@Rollback(false)
	@Test
	public void gatherSettleInfo() {
		OpenAccountRequest request = new OpenAccountRequest();
		request.setMemberNo("212468314393");
		request.setSettleBankCode("CMBCHINA");
		request.setSettleBankName("北京万通支行");
		request.setSettleCardNo("6214830106424202");
		request.setSettleCardName("测试");
		request.setBankProvinceCode("北京");
		request.setBankCityCode("北京");
		OpenAccountResponse response = openAccountFacade.gatherSettleInfo(request);
		System.out.print(new Gson().toJson(response));
		Assert.assertTrue(response.getStatus().equals(ResponseStatus.SUCCESS));
	}


	//	@Rollback(false)
	@Test
	public void gatherAttachments() {
//        商户入网类型=QYCORP_CODEIDCARD, 必传文件列表=[IDCARD_FRONT, IDCARD_BACK, CORP_CODE, TAX_CODE, ORG_CODE, OP_BANK_CODE]
		AttachmentsRequest request = new AttachmentsRequest();
		request.setMemberNo("212468314312");
		request.setAgreementImg("http://172.17.106.197:8081/ucm/201605/944b6163842b4d1bacc1d1a3f5096e33.IMG_0004.JPG");
        request.setBankcardImg("http://172.17.106.197:8081/ucm/201605/944b6163842b4d1bacc1d1a3f5096e33.IMG_0004.JPG");
        request.setIdcardImg1("http://172.17.106.197:8081/ucm/201605/944b6163842b4d1bacc1d1a3f5096e33.IMG_0004.JPG");
        request.setIdcardImg2("http://172.17.106.197:8081/ucm/201605/944b6163842b4d1bacc1d1a3f5096e33.IMG_0004.JPG");
        request.setBizImg("http://172.17.106.197:8081/ucm/201605/944b6163842b4d1bacc1d1a3f5096e33.IMG_0004.JPG");
        request.setPermitImg("http://172.17.106.197:8081/ucm/201605/944b6163842b4d1bacc1d1a3f5096e33.IMG_0004.JPG");
        request.setTaxImg("http://172.17.106.197:8081/ucm/201605/944b6163842b4d1bacc1d1a3f5096e33.IMG_0004.JPG");
        request.setOrgImg("http://172.17.106.197:8081/ucm/201605/944b6163842b4d1bacc1d1a3f5096e33.IMG_0004.JPG");
		OpenAccountResponse response = openAccountFacade.gatherAttachments(request);
		System.out.print(new Gson().toJson(response));
		Assert.assertTrue(response.getStatus().equals(ResponseStatus.SUCCESS));
	}

	@Test
	public void callbackResult() {
		Map<String, String> map = Maps.newHashMap();
//		map.put("requestNo", "ACA16122318952329");
//		map.put("merNetInStatus", "PROCESS_SUCCESS");
        map.put("requestNo", "ACA17082336718879");
        map.put("merNetInStatus", "PROCESS_REJECT");
        BaseResponse response = likerCallbackFacade.openAccResult(map);
		System.out.print(new Gson().toJson(response));
		Assert.assertTrue(response.getStatus().equals(ResponseStatus.SUCCESS));
	}

	@Test
	public void jsonTest() {
		Map<String, Object> map = Maps.newHashMap();
		List<Map<String, String>> list = Lists.newArrayList();
		Map<String, String> map1 = Maps.newHashMap();
		Map<String, String> map2 = Maps.newHashMap();
		Map<String, String> map3 = Maps.newHashMap();
		Map<String, String> map4 = Maps.newHashMap();
		Map<String, String> map5 = Maps.newHashMap();
		Map<String, String> map6 = Maps.newHashMap();
		Map<String, String> map7 = Maps.newHashMap();
		map1.put("app", "true");
		map1.put("taiqian", "true");

		map2.put("id", "WXZS");
		map3.put("id", "ALI");
		map4.put("id", "ALIZS");
		map5.put("id", "GZH");
		map6.put("id", "NC_PAY_DEBIT");
		map7.put("id", "NC_PAY_CREDIT");
		list.add(map2);
		list.add(map3);
		list.add(map4);
		list.add(map5);
		list.add(map6);
		list.add(map7);
		map.put("openProducts", list);
		map.put("payType", map1);
		System.out.print(new Gson().toJson(map));
    }

    @Test
    public void findopen() {
        BaseRequest baseRequest = new BaseRequest();
        baseRequest.setVersionId("APA17051767189564");
        baseRequest.setMemberNo("212468327025");
        System.out.print(new Gson().toJson(openAccountFacade.findOpenAccount(baseRequest)));
    }

    @Test
    public void test_2() {
        //OpenAccountFacade openAccountFacade = RemoteServiceFactory.getService("http://10.151.32.27:30035/laike-hessian/hessian/", RemotingProtocol.HESSIAN, (OpenAccountFacade.class));
//		OpenAccountRequest request1 = new OpenAccountRequest();
//		request1.setMemberNo("212468316159");
//		request1.setInviteType(InviteType.INVITECODE);
//		request1.setInviteCode("107376");
//		request1.setCompanyType(CompanyTypeEnum.ENTERPRISE);
//		request1.setCompanyType(CompanyTypeEnum.MICRO);
//		openAccountFacade.checkInviteType(request1);
        OpenAccountRequest request = new OpenAccountRequest();
        request.setMemberNo("212468316159");
        request.setLegalName("温倩");
        request.setAgentNo("10040011542");
        request.setLegalIdCard("110105198708217723");
        request.setLegalIdStart("1997-01-01");
        request.setLegalIdEnd("2020-01-01");
        request.setMerLevel1No("007");
        request.setMerLevel2No("007001");
        request.setMerProvince("110000");
        request.setMerCity("110100");
        request.setMerDistrict("110101");
        request.setMerAddress("万通中心D座23层");
        OpenAccountResponse response = openAccountFacade.gatherBaseInfo(request);
        System.out.print(new Gson().toJson(response));
        assertTrue(response.getStatus().equals(ResponseStatus.SUCCESS));
    }

    @Test
    public void test() {
        System.out.println(new Date(1491900534439L));
    }

    @Test
    public void upload() throws IOException {
        UploadImgRequest request = new UploadImgRequest();
        File file = new File("/Users/yp-tc-m-7116/Desktop/WechatIMG355.jpeg");
        BufferedImage bis = ImageIO.read(file);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bis, "jpeg", baos);
        byte[] bankCardByte = baos.toByteArray();
        String base64 = Base64.encodeBase64String(bankCardByte);
        request.setMemberNo("212468315706");
		System.err.println(new Gson().toJson(allianceAccountFacade.uploadImage(request)));
	}

    @Test
    public void openAllianceAccount() {
        OpenAccountRequest request = new OpenAccountRequest();
        request.setMemberNo("212468327431");
        request.setSettleCardNo("6225880175522243");
        request.setSettleCardName("陈琰");
        request.setSettleBankCode("CMBCHINA");
        request.setSettleBankName("招商银行");
//		request.setBankcardImg("http://172.17.106.197:8081/ucm/201709/cb04470c9f6445f89fc571b63869d522.jpeg");
//		request.setBankcardImg2("http://172.17.106.197:8081/ucm/201709/cb04470c9f6445f89fc571b63869d522.jpeg");
//		request.setOcrSettleCardNo("6225880175522243");
        request.setVersionId("APA17082389523724");
//		System.err.println(new Gson().toJson(appOpenAccountFacade.gatherNewSettleInfo(request)));
    }

    @Test
    public void gatherNewAttachments() {
        //AttachmentsRequest request = new AttachmentsRequest();
        OpenAccountRequest request = new OpenAccountRequest();
        request.setMemberNo("212468327702");
//		System.err.println(appOpenAccountFacade.gatherNewBaseInfo(request));
    }

    @Test
    public void open() {
        BaseRequest request = new BaseRequest();
        request.setMemberNo("212468327792");
        System.err.println(new Gson().toJson(appOpenAccountFacade.findOpenAccount(request)));
    }

    @Test
    @Rollback(false)
    public void autoRegAlliance() {
        BaseRequest request = new BaseRequest();
        request.setMemberNo("212468327483");
//        System.err.println(new Gson().toJson(allianceAccountFacade.autoRegAlliance(request)));
    }

    @Test
    public void findServiceList() {
        BaseRequest request = new BaseRequest();
        request.setMemberNo("212468327708");
        System.err.println(new Gson().toJson(likerProductionFacade.list(request)));
    }
}
