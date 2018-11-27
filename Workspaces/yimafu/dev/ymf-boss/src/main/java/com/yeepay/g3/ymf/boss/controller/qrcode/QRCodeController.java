package com.yeepay.g3.ymf.boss.controller.qrcode;

import com.yeepay.g3.core.ymf.entity.customer.Customer;
import com.yeepay.g3.core.ymf.entity.qrcode.QRCode;
import com.yeepay.g3.core.ymf.service.CustomerService;
import com.yeepay.g3.core.ymf.service.DictionaryService;
import com.yeepay.g3.core.ymf.service.QrCodeService;
import com.yeepay.g3.core.ymf.support.OperateEntity;
import com.yeepay.g3.facade.ymf.enumtype.MaterialStatus;
import com.yeepay.g3.facade.ymf.enumtype.OperateType;
import com.yeepay.g3.facade.ymf.enumtype.Status;
import com.yeepay.g3.ymf.boss.controller.ValidateController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * 二维码管理
 * Created by fei.lu on 16/8/13.
 */
@Controller
@RequestMapping("qrCode")
public class QRCodeController extends ValidateController{
    @Autowired
    private QrCodeService qrCodeService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private DictionaryService dictionaryService;

    //二维码查询
    @RequestMapping("query")
    public String queryQrcode(){
        return "qrcode/qrCodeQuery";
    }

    //二维码详情
    @RequestMapping("toDetail")
    public ModelAndView qrCodeDetail(@RequestParam(value="id") long id){
        QRCode qrCode = qrCodeService.getQrCodeById(id);
        ModelAndView mv = new ModelAndView();
        if(qrCode==null){
            mv.addObject("msg","二维码不存在");
            mv.setViewName("error");
        }else{
            String customerNumber= qrCode.getCustomerNumber();
            Customer customer = customerService.findByCustomerNumber(customerNumber);
            mv.addObject("customer",customer);
            mv.addObject("qrCode",qrCode);
            mv.setViewName("qrcode/qrCodeDetail");
        }
        return mv;
    }

    //二维码核销
    @RequestMapping("cancelQrcode")
    @ResponseBody
    public String cancelQrcode(@RequestParam(value="qrCodeId") long qrCodeId,HttpSession session){
        String user = this.getUser(session);
        QRCode qrCode = qrCodeService.getQrCodeById(qrCodeId);
        if(qrCode==null){
            return "NOTEXISTQRCODE";
        }else{
            String customerNumber = qrCode.getCustomerNumber();
            qrCode.setStatus(MaterialStatus.INACTIVE);
            qrCode.setCloseTime(new Date());
            OperateEntity<QRCode> en = new OperateEntity<QRCode>(user,qrCode,OperateType.UPDATE,customerNumber);
            int executeResult = qrCodeService.updateQrCode(en);
            if(executeResult>=0){
                return "success";
            }else{
                return "fail";
            }
        }
    }

    //二维码生成
    @RequestMapping("createQrcode")
    public ModelAndView createQrCode(@RequestParam(value="customerNumber") String customerNumber,HttpSession session){
        String user = this.getUser(session);
        Customer customer = customerService.findByCustomerNumber(customerNumber);
        ModelAndView mv = new ModelAndView();
        if(customer!=null) {
            Status status = customer.getStatus();
            QRCode checkQrCode = qrCodeService.selectByCustomerAndStatus(customerNumber);
            if(checkQrCode==null){
            if(status!=null) {
                if ("ACTIVE".equals(status.toString())) {
                    QRCode qrCode = new QRCode();
                    qrCode.setCustomerNumber(customerNumber);
                    OperateEntity<QRCode> en = new OperateEntity<QRCode>(user, qrCode, OperateType.ADD, customerNumber);
                    try {
                        qrCodeService.saveQrCode(en);
                    }catch(Exception e){
                        e.printStackTrace();
                        mv.addObject("msg", "商户编号为:" + customerNumber + "的商户生成二维码失败!");
                        mv.setViewName("error");
                        return mv;
                    }
                    mv.addObject("qrCode", qrCode);
                    mv.addObject("customer", customer);
                    mv.setViewName("qrcode/qrCodeDetail");
                    return mv;
                } else {
                    mv.addObject("msg", "商户编号为:" + customerNumber + "的商户的产品权限为关闭,请先开通产品!");
                    mv.setViewName("error");
                    return mv;
                }
            }else {
                mv.addObject("msg", "商户编号为:" + customerNumber + "的商户的产品权限为空,请先维护产品权限!");
                mv.setViewName("error");
                return mv;
            }
            }else{
                mv.addObject("msg", "商户编号为:" + customerNumber + "的商户已生成二维码!");
                mv.setViewName("error");
                return mv;
            }
        }else{
            mv.addObject("msg", "商户编号为:"+customerNumber+"的商户不存在!");
            mv.setViewName("error");
            return mv;
        }
    }

    //核销后的二维码重新生成
    @RequestMapping("modifyQrcode")
    public ModelAndView modifyQrcode(@RequestParam(value="id") long id,HttpSession session) {
        String user = this.getUser(session);
        ModelAndView mv = new ModelAndView();
        QRCode qrCode = qrCodeService.getQrCodeById(id);
        if (qrCode != null) {
            String customerNumber = qrCode.getCustomerNumber();
            Customer customer = customerService.findByCustomerNumber(customerNumber);
            if(customer!=null) {
                Status status = customer.getStatus();
                QRCode checkQrCode = qrCodeService.selectByCustomerAndStatus(customerNumber);
                if(checkQrCode==null) {
                    if (status != null) {
                        if ("ACTIVE".equals(status.toString())) {//商户状态可用
                            try {
                                qrCode.setStatus(MaterialStatus.DELETE);
                                OperateEntity<QRCode> en = new OperateEntity<QRCode>(user, qrCode, OperateType.UPDATE, customerNumber);
                                qrCodeService.updateQrCode(en);
                                QRCode newQrcode = new QRCode();
                                newQrcode.setCustomerNumber(customerNumber);
                                OperateEntity<QRCode> en1 = new OperateEntity<QRCode>(user, newQrcode, OperateType.ADD, customerNumber);
                                qrCodeService.saveQrCode(en1);
                                QRCode qrCodeEnd = qrCodeService.selectByCustomerAndStatus(customerNumber);
                                mv.addObject("customer", customer);
                                mv.addObject("qrCode", qrCodeEnd);
                                mv.setViewName("qrcode/qrCodeDetail");
                                return mv;
                            }catch(Exception e){
                                e.printStackTrace();
                                mv.addObject("msg", "商户编号为:" + customerNumber + "的商户生成二维码失败!");
                                mv.setViewName("error");
                                return mv;
                            }
                        } else {
                            mv.addObject("msg", "商户编号为:" + customerNumber + "的商户的产品权限为关闭,请先开通产品!");
                            mv.setViewName("error");
                            return mv;
                        }
                    } else {
                        mv.addObject("msg", "商户编号为:" + customerNumber + "的商户的产品权限为空,请先维护产品权限!");
                        mv.setViewName("error");
                        return mv;
                    }
                }else{
                    mv.addObject("msg", "商户编号为:" + customerNumber + "的商户的二维码已经生成!");
                    mv.setViewName("error");
                    return mv;
                }
            }else{
                mv.addObject("msg", "二维码id为" + id + "的对应的商户编号为:"+customerNumber+",该商户不存在!");
                mv.setViewName("error");
                return mv;
            }
        } else {
            mv.addObject("msg", "二维码id为" + id + "的二维码不存在");
            mv.setViewName("error");
            return mv;
        }
    }
}
