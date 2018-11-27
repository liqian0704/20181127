package com.yeepay.g3.ymf.boss.controller.terminalnumber;/**
 * Created by jiwei.lv on 17/5/8.
 */

import com.yeepay.g3.core.ymf.entity.customer.Customer;
import com.yeepay.g3.core.ymf.entity.upayterminalno.TerminalNumber;
import com.yeepay.g3.core.ymf.entity.upayterminalno.TerminalNumberArgs;
import com.yeepay.g3.core.ymf.service.CustomerService;
import com.yeepay.g3.core.ymf.service.terminalno.TerminalNumberService;
import com.yeepay.g3.facade.ymf.dto.common.ResponseMessage;
import com.yeepay.g3.facade.ymf.enumtype.common.CommonStatus;
import com.yeepay.g3.utils.common.BeanUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.ymf.boss.controller.ValidateController;
import com.yeepay.g3.ymf.boss.support.annotations.CommonArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @author jiwei.lv
 * @create 2017-05-17/5/8
 */
@Controller
@RequestMapping("/terminal")
public class UnionPayTerminalNoController extends ValidateController {
    private static final Logger log = LoggerFactory.getLogger(UnionPayTerminalNoController.class);
    @Autowired
    private TerminalNumberService terminalNumberService;
    @Autowired
    private CustomerService customerService;
    @RequestMapping("/query")
    public String query(){
        return  "terminalnumber/terminalNumberQuery";
    }
    @RequestMapping("/toAdd")
    public String toAdd(){
        return  "terminalnumber/add";
    }

    @RequestMapping("/add")
    @ResponseBody
    public ResponseMessage add(@CommonArgs TerminalNumberArgs terminalNumberArgs, HttpSession session){
        log.info(" add terminalNumber:"+terminalNumberArgs.toString());
        TerminalNumber terminalNumber = new TerminalNumber();
        BeanUtils.copyProperties(terminalNumberArgs,terminalNumber);
        ResponseMessage resp = validate(terminalNumberArgs);
        String user = getUser(session);
        try {
            //校验添加信息
            boolean tag=terminalNumberService.checkTerminalNumber(terminalNumber.getCustomerNumber(),terminalNumber.getTerminalNumber());
            if(tag){
                return ResponseMessage.error(terminalNumber.getCustomerNumber()+"已经存在"+terminalNumber.getTerminalNumber()+"银联终端号,请勿重复添加!!!");
            }
            Customer customer=customerService.findByCustomerNumber(terminalNumber.getCustomerNumber());
            if(customer==null){
                return ResponseMessage.error(terminalNumber.getCustomerNumber()+"找不到对应的商户!");
            }
            terminalNumberService.saveTerNO(terminalNumber.getCustomerNumber(),terminalNumber.getTerminalNumber(),user);
            return ResponseMessage.ok();
        }catch (Exception e){
            log.error("增加银联终端号发生异常",e);
            return ResponseMessage.error(e.toString());
        }

    }
    @RequestMapping("/modify")
    @ResponseBody
    public ResponseMessage modify(@RequestParam(value = "id") Long id,HttpSession session){
        try {
            String user = getUser(session);
            TerminalNumber terminalNumber = terminalNumberService.findByTerminalNO(id);
            if (terminalNumber == null) {
                return ResponseMessage.error("无相应的银联终端号!");
            }
            if(terminalNumber.getStatus()== CommonStatus.ACTIVE){
                terminalNumber.setStatus(CommonStatus.INACTIVE);
            }else{
                terminalNumber.setStatus(CommonStatus.ACTIVE);
            }
            terminalNumber.setOperatorName(user);
            terminalNumber.setLastmodifyTime(new Date());
            terminalNumberService.updateTerminalNO(terminalNumber);
            return ResponseMessage.ok();
        }catch (Exception e ){
            return ResponseMessage.error(e.toString());
        }
    }
}
