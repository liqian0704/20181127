package com.yeepay.g3.ymf.boss.controller.likerapp;

import com.yeepay.g3.core.ymf.utils.serialize.JsonMarshaller;
import com.yeepay.g3.core.ymf.utils.web.POIHelper;
import com.yeepay.g3.facade.laike.dto.boss.RegisterMerRequest;
import com.yeepay.g3.facade.laike.dto.boss.RegisterMerResponse;
import com.yeepay.g3.facade.laike.enumtype.CompanyTypeEnum;
import com.yeepay.g3.facade.laike.facade.MerchantBossManageFacade;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import com.yeepay.g3.ymf.boss.controller.ValidateController;
import com.yeepay.g3.ymf.boss.utils.ExcelUtil;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * Description: app用户管理
 * Author: wei.li
 * Date: 17/3/7
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Controller
@RequestMapping("/user")
public class AppUserController extends ValidateController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppUserController.class);

    @RequestMapping("/toAdd")
    public String toAddCustomer() {
		return "likerapp/appUserManage/addUser";
	}

    @RequestMapping("/toDetail")
    public String toDetail() {
		return "likerapp/appUserManage/detail";
	}

    @RequestMapping("/addUser")
    public String add(String opNo, String companyType, String bossUer, @RequestParam("uploadFile") MultipartFile file, HttpSession session, Model model) {
        MerchantBossManageFacade merchantBossManageFacade = RemoteServiceFactory.getService(MerchantBossManageFacade.class);
        RegisterMerResponse responseDTO = new RegisterMerResponse();
        List<String> phoneNoList = new ArrayList<String>();
        List<String> merchantNoList = new ArrayList<String>();
        String merchantNo = "";
        String phoneNo = "";
        boolean bossIsNoExist = true;
        phoneNoList.add(bossUer);
        if (!file.isEmpty()) {
            LOGGER.info("通过Excel批量注册来客用户 : " + file.getOriginalFilename());
            ExcelUtil eu = new ExcelUtil();
            try {
                List<List<String>> dataList = eu.read(file);
                if (dataList != null) {
                    for (int i = 0; i < dataList.size(); i++) {
                        List<String> cellList = dataList.get(i);
                        phoneNo = cellList.get(0);
                        merchantNo = cellList.get(1);
                        if(StringUtils.isBlank(phoneNo) || StringUtils.isBlank(merchantNo)){
                            throw new Exception("noIsEmpty");
                        }
                        if(!phoneNo.equals(bossUer)){
                            phoneNoList.add(phoneNo);
                        }else{
                            bossIsNoExist = false;
                        }
                        merchantNoList.add(merchantNo);
                    }
                    if(phoneNoList.size() == 0 || merchantNoList.size() == 0){
                        throw new Exception("excelIsEmpty");
                    }
                    if(phoneNoList.size() > 100){
                        throw new Exception("listIsOverLimit");
                    }
                    if(bossIsNoExist){
                        throw new Exception("bossIsNoExist");
                    }
                    if(!hasSame(phoneNoList, phoneNoList.size())){
                        throw new Exception("phoneRepeat");
                    }
                    if(!hasSame(merchantNoList, 1)){
                        throw new Exception("merchantNoSame");
                    }
                    RegisterMerRequest dto = new RegisterMerRequest();
                    dto.setPhoneNoList(phoneNoList);
                    dto.setMerchantNo(merchantNo);
                    dto.setCompanyType(CompanyTypeEnum.valueOf(companyType));
                    dto.setOpNo(opNo);
                    dto.setBossUser(bossUer);
                    responseDTO = merchantBossManageFacade.registerMer(dto);
                }
            } catch (Exception e) {
                model.addAttribute("excelUpdateStatus", e.getMessage());
				return "likerapp/appUserManage/addUser";
			}
		}
		if(!StringUtils.isBlank(responseDTO.getErrMsg())){
            RegisterMerResponse registerMerResponse = JsonMarshaller.getMarshaller().jsonUnMarshaller(responseDTO.getErrMsg(),RegisterMerResponse.class);
            session.setAttribute("resultList", convert2result(registerMerResponse, opNo));
        }else{
            session.setAttribute("resultList", convert2result(responseDTO, opNo));
        }
        return "redirect:/user/toDetail";
    }

    @RequestMapping("/downFile")
    public void downFile(HttpSession session,  HttpServletResponse response){
//        String path = System.getProperty("java.io.tmpdir");
        String fileName = File.separator + getUser(session) + System.currentTimeMillis() + ".xlsx";
        List<List<Object>> result = new LinkedList<List<Object>>();
//        File excel = new File(fileName);
        List<List<Object>> content = (List<List<Object>>) session.getAttribute("resultList");
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            List<String> list = new ArrayList<String>();
            list.add("注册手机号");
            list.add("商户编号");
            list.add("校验结果");
            list.add("注册结果");
            list.add("OP单号");
            POIHelper.writeNormal(list, content, baos);
//            FileInputStream fis = new FileInputStream(excel);
            response.setContentType("application/octet-stream");
            response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
            IOUtils.write(baos.toByteArray(), response.getOutputStream());
            response.flushBuffer();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private List<List<String>> convert2result(RegisterMerResponse registerMerResponse, String opNo){
        List<List<String>> resultList = new LinkedList<List<String>>();
        Map<String, String> map = registerMerResponse.getVerifyResult();
        Set<String> keySet = map.keySet();
        for(String key : keySet){
            List<String> result = new LinkedList<String>();
            result.add(key);
            result.add(registerMerResponse.getMerchantNo());
            result.add(map.get(key));
            result.add(registerMerResponse.getStatus().getDisplayName());
            result.add(opNo);
            resultList.add(result);
        }
        return resultList;
    }

    private static boolean hasSame(List<? extends Object> list, int len)
    {
        if(null == list)
            return false;
        return len == new HashSet<Object>(list).size();
    }
}
