package com.yeepay.g3.ymf.boss.controller.material;

import com.yeepay.g3.core.ymf.biz.material.TermBiz;
import com.yeepay.g3.core.ymf.utils.web.POIHelper;
import com.yeepay.g3.core.ymf.vo.material.BatchTermRequest;
import com.yeepay.g3.core.ymf.vo.material.BatchTermResponse;
import com.yeepay.g3.facade.ymf.dto.TermDTO;
import com.yeepay.g3.facade.ymf.dto.common.ResponseMessage;
import com.yeepay.g3.facade.ymf.facade.posboss.LKPosInfoService;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.ymf.boss.WebConstants;
import com.yeepay.g3.ymf.boss.controller.ValidateController;
import com.yeepay.g3.ymf.boss.utils.common.DownloadUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Title: 终端与终端关系管理
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 17/3/10.
 */
@RequestMapping("material")
@Controller
public class TermController extends ValidateController {

    private static final Logger log = LoggerFactory.getLogger(TermController.class);

    @Autowired
    private TermBiz termBiz;

    @Autowired
    private LKPosInfoService lkPosInfoService;

    @RequestMapping("query")
    public ModelAndView route() {
        return new ModelAndView("material/term/query");
    }

    @RequestMapping("toBindTerm")
    public ModelAndView toBindTerm(@RequestParam String snSerial) {
        ModelAndView mv = new ModelAndView("material/term/bind");
        mv.addObject("snSerial", snSerial);
        return mv;
    }

    @RequestMapping("bindTerm")
    public @ResponseBody ResponseMessage bindTerm(HttpSession session, @RequestParam String snSerial,
                                                  @RequestParam String customerNumber) {
        try {
            if (StringUtils.isBlank(snSerial)) {
                return ResponseMessage.error("SN号不能为空");
            }
            if (StringUtils.isBlank(customerNumber)) {
                return ResponseMessage.error("商户编号不能为空");
            }
            termBiz.bindTerm(snSerial, customerNumber, getUser(session));
            return ResponseMessage.ok();
        } catch (Exception e) {
            log.error("绑定终端失败 SN号" + snSerial + ",商户编号" + customerNumber + "," + e.getMessage());
            return ResponseMessage.error("绑定终端失败:" + e.getMessage());
        }
    }

    @RequestMapping("toBatchBindTerm")
    public ModelAndView toBachBindTerm() {
        return new ModelAndView("material/term/batchBind");
    }

    @RequestMapping("batchBindTerm")
    @ResponseBody
    public ResponseMessage batchBindTerm(@RequestParam MultipartFile batchBindFile,
                                         @RequestParam String opCode,
                                         HttpSession session) {
        try {
            if (StringUtils.isBlank(opCode)) {
                return ResponseMessage.error("OP单号不能为空!");
            }
            if (null == batchBindFile) {
                return ResponseMessage.error("上传文件不能为空!");
            }
            List<BatchTermRequest> requestList = POIHelper.read(batchBindFile.getInputStream(), "data",
                    BatchTermRequest.class);
            if (null != requestList && requestList.size() == 0) {
                return ResponseMessage.error("上传文件中没有有效数据");
            }
            if (null != requestList && requestList.size() > 200) {
                return ResponseMessage.error("一次批量绑定最大200条记录");
            }
            List<BatchTermResponse> responseList = termBiz.batchBindTerm(requestList, opCode, getUser(session));
            session.setAttribute(WebConstants.SESSION_KEY_TERM_BATCH_RESPONSE, responseList);
            return ResponseMessage.data(responseList).setFlag(responseList.size() > 0);
        } catch (Exception e) {
            log.error("批量绑定终端失败", e);
            return ResponseMessage.error("批量绑定终端失败", e);
        }
    }

    @SuppressWarnings("unchecked")
    @RequestMapping("downloadBatchTermResponse")
    public void downloadBatchTermResponse(HttpSession session, HttpServletResponse response) {
        Object flag = session.getAttribute(WebConstants.SESSION_KEY_TERM_BATCH_RESPONSE);
        if (null != flag) {
            List<BatchTermResponse> responseList = (List<BatchTermResponse>) flag;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            POIHelper.write(responseList, baos);
            DownloadUtils.download(response, baos, "downloadBatchTermResponse.xlsx", MediaType.APPLICATION_OCTET_STREAM);
        } else {
            response.setContentType("text/plain");
            try {
                response.getWriter().write("没有文件需要下载");
            } catch (IOException e1) {

            }
        }

    }

    /**
     * 终端解绑
     * @param snSerial SN号
     * @param customerNumber 商户编号
     * @return
     */
    @RequestMapping("unbindTerm")
    public @ResponseBody ResponseMessage unbindTerm(HttpSession session, @RequestParam String snSerial,
                                                    @RequestParam String customerNumber) {
        try {
            if (StringUtils.isBlank(snSerial)) {
                return ResponseMessage.error("SN号不能为空");
            }
            if (StringUtils.isBlank(customerNumber)) {
                return ResponseMessage.error("商户编号不能为空");
            }
            termBiz.undBindTerm(snSerial, customerNumber, getUser(session));
            return ResponseMessage.ok();
        } catch (Exception e) {
            log.error("解绑终端失败 SN号" + snSerial + ",商户编号" + customerNumber + "," + e.getMessage());
            return ResponseMessage.error("解绑终端失败:" + e.getMessage());
        }
    }

    @RequestMapping("toBatchSyncTerm")
    public String toBatchSyncTerm() {
        return "material/term/batchSync";
    }

    /**
     * 根据出入库批次号同步posboss机具信息
     * @param inAndOutNum 出入库批次号
     * @return
     */
    @RequestMapping("batchSyncTerm")
    public @ResponseBody ResponseMessage batchSyncTerm(@RequestParam String inAndOutNum) {
        try {
            if (StringUtils.isBlank(inAndOutNum)) {
                return ResponseMessage.error("出入库单号不能为空");
            }
            List<TermDTO> dtoList = lkPosInfoService.getLKPosInfoByInAndOutNumber(inAndOutNum);
            if (null == dtoList || dtoList.size() == 0) {
                return ResponseMessage.error("根据批次号" + inAndOutNum + "没有查询到信息");
            }
            HashMap<String, TermDTO> hashMap = new HashMap<String, TermDTO>();
            for (TermDTO termDTO : dtoList) {
                hashMap.put(termDTO.getSerial(), termDTO);
            }
            termBiz.batchSyncTerm(hashMap);
            return ResponseMessage.ok();
        } catch (Exception e) {
            log.error("同步POBOSS机具信息失败", e);
            return ResponseMessage.error("同步POBOSS机具信息失败" + e.getMessage());
        }
    }
}
