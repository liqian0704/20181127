package com.yeepay.g3.ymf.boss.controller.qrcode; /**
 * 类名称: QRCodeWithShopController <br>
 * 类描述: <br>
 *
 * @author: dongxu.lu
 * @since: 16/10/28 上午11:04
 * @version: 1.0.0
 */

import com.yeepay.g3.core.ymf.entity.customer.Customer;
import com.yeepay.g3.core.ymf.entity.qrcode.QRCode;
import com.yeepay.g3.core.ymf.service.CustomerService;
import com.yeepay.g3.core.ymf.service.QrCodeService;
import com.yeepay.g3.facade.ymf.dto.qrCode.CreateQRCodeDTO;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.ymf.boss.controller.ValidateController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("newQrCode")
public class QRCodeNewController extends ValidateController {
    Logger logger = LoggerFactory.getLogger(QRCodeNewController.class);
    @Autowired
    private CustomerService customerService;
    @Autowired
    private QrCodeService qrCodeService;
    //二维码详情
    @RequestMapping("toCreate")
    public String toCreateQrCode(){
        return "qrcode/createQrCode";
    }
    @RequestMapping("toQuery")
    public String toQuery(){
        return "qrcode/qrCodeNewQuery";
    }
    @RequestMapping("create")
    public ModelAndView createQrCode(CreateQRCodeDTO qrCodeDTO) {
        ModelAndView mv = new ModelAndView();
        //@TODO 一期只有商户编号
        String customerNumber = qrCodeDTO.getCustomerNumber();
        List<QRCode> qrCodeList = new ArrayList<QRCode>();
        Customer customer = null;
        if(qrCodeDTO == null){
            mv.addObject("msg","参数都不填的啊,我可没你那么聪明!");
            mv.setViewName("error");
            return mv;
        }
        if(qrCodeDTO.getQrCount()>50){
            mv.addObject("msg","一次最多允许生成50个,不要贪心啊亲!");
            mv.setViewName("error");
            return mv;
        }
        if(!StringUtils.isBlank(customerNumber)){
             customer = customerService.findByCustomerNumber(customerNumber);
        }
        if (null != customer) {
            mv.addObject("customer", customer);
        }
        for (int i = 0; i < qrCodeDTO.getQrCount(); i++) {
            QRCode code = new QRCode();
            if (null != customer) {
                code.setCustomerNumber(customer.getCustomerNumber());
            }
            qrCodeList.add(code);
        }
        try {
            qrCodeService.batchCreateQrCode(qrCodeList);
        } catch (Exception e) {
            logger.error("###batchCreateQrCode Exception:",e);
            mv.addObject("msg","系统异常!");
            mv.setViewName("error");
            return mv;
        }
        mv.setViewName("qrcode/qrCodeNewQuery");
        return mv;
    }
    @RequestMapping("toModify")
    public ModelAndView toModify(@RequestParam(value = "id" )Long id){
        ModelAndView mv = new ModelAndView();
        QRCode qrCode = qrCodeService.getQrCodeById(id);
        if(null == qrCode){
            mv.addObject("msg","二维码 id:"+id+" 不存在");
            mv.setViewName("error");
            return mv;
        }
        mv.addObject("qrCode",qrCode);
        String customerNumber = qrCode.getCustomerNumber();
        Customer customer = customerService.findByCustomerNumber(customerNumber);
        if(null != customer){
            mv.addObject("customer",customer);
        }
        mv.setViewName("qrcode/qrCodeShopManage");
        return mv;
    }
    @ResponseBody
    @RequestMapping("getCustomerInfo")
    public Customer getCustomerInfo(@RequestParam("customerNumber") String customerNumber){

        Customer customer = customerService.findByCustomerNumber(customerNumber);
        if(null==customer){
            return null;
        }
        return customer;
    }

    @RequestMapping("updateQrCode")
    public ModelAndView updateQrCode(@RequestParam("qrcodeId") Long qrcodeId,
                                     @RequestParam("customerNumber") String customerNumber,
                                     @RequestParam("shopname") String shopname,
                                     QRCode qrCode){
        ModelAndView mv = new ModelAndView();
        Customer customer = customerService.findByCustomerNumber(customerNumber);
        if(null==customer){
            mv.addObject("msg","商户:"+customerNumber+" 不存在");
            mv.setViewName("error");
            return mv;
        }
        qrCode.setId(qrcodeId);
        qrCode.setShopname(shopname);
        qrCode.setCustomerNumber(customerNumber);
        try {
            qrCodeService.updateQrCode(qrCode);
        } catch (Exception e) {
            logger.error("###updateQrCode Exception qrCodeId:"+qrcodeId,e);
            mv.addObject("msg","系统异常!");
            mv.setViewName("error");
            return mv;
        }
        QRCode qrCodes = qrCodeService.getQrCodeById(qrCode.getId());
        mv.addObject("qrCode",qrCodes);
        mv.addObject("customer",customer);
        mv.setViewName("qrcode/qrCodeShopManage");
        return mv;
    }

}
