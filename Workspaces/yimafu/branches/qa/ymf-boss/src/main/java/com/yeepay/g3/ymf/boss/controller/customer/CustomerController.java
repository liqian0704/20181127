package com.yeepay.g3.ymf.boss.controller.customer;

import com.yeepay.app.httpinvoke.online.dto.CustomerBasicInfoDTO;
import com.yeepay.app.httpinvoke.online.facade.G2ServiceInterfaceFacade;
import com.yeepay.g3.core.ymf.entity.business.Business;
import com.yeepay.g3.core.ymf.entity.customer.Customer;
import com.yeepay.g3.core.ymf.entity.customer.CustomerArgs;
import com.yeepay.g3.core.ymf.entity.customer.CustomerFunction;
import com.yeepay.g3.core.ymf.entity.dictionary.Dictionary;
import com.yeepay.g3.core.ymf.entity.qrcode.QRCode;
import com.yeepay.g3.core.ymf.service.BusinessService;
import com.yeepay.g3.core.ymf.service.CustomerFunctionService;
import com.yeepay.g3.core.ymf.service.CustomerService;
import com.yeepay.g3.core.ymf.service.DictionaryService;
import com.yeepay.g3.core.ymf.service.QrCodeService;
import com.yeepay.g3.core.ymf.support.OperateEntity;
import com.yeepay.g3.facade.ymf.dto.common.ResponseMessage;
import com.yeepay.g3.facade.ymf.enumtype.MaterialStatus;
import com.yeepay.g3.facade.ymf.enumtype.OperateType;
import com.yeepay.g3.facade.ymf.enumtype.Status;
import com.yeepay.g3.utils.common.BeanUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import com.yeepay.g3.utils.rmi.RemotingProtocol;
import com.yeepay.g3.ymf.boss.controller.ValidateController;
import com.yeepay.g3.ymf.boss.support.annotations.CommonArgs;
import com.yeepay.utils.ftp.FtpFileStoreHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商户管理控制器
 * Created by fei.lu on 16/8/11.
 */
@Controller
@RequestMapping("/customer")
public class CustomerController extends ValidateController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private BusinessService businessService;
    @Autowired
    private  DictionaryService dictionaryService;
    @Autowired
    private QrCodeService qrCodeService;
    @Autowired
    private  CustomerFunctionService customerFunctionService;
    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    //商户新增页面
    @RequestMapping("/toAdd")
    public String toAddCustomer() {
        return "customer/customerAdd";
    }

    //商户新增
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage addCustomer(@CommonArgs CustomerArgs customerArgs, HttpSession session) {
        log.info("add customer no customerLogo,customerNumber="+customerArgs.getCustomerNumber());
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerArgs,customer);
        transferPeriod(customer, customerArgs);
        ResponseMessage resp = validate(customerArgs);
        String user = getUser(session);
        if (resp.isOk()) {
            log.info("validate customer success ,customerNumber="+customer.getCustomerNumber());
            Customer checkCustomer = customerService.findByCustomerNumber(customer.getCustomerNumber());
            if (checkCustomer != null) {
                return ResponseMessage.error("商户编号已存在");
            }
            customer = packageCustomer(customer,"ADD");
            int executeResult = customerService.saveCustomer(new OperateEntity<Customer>(user, customer, OperateType.ADD, customer.getCustomerNumber()));
            if (executeResult >= 0) {
                return ResponseMessage.ok();
            } else {
               return ResponseMessage.error("增加失败");
            }
        }
        return resp;
    }
    @RequestMapping(value = "/addCustomerLogo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage addCustomer(@CommonArgs CustomerArgs customerArgs, @RequestParam(required = false) MultipartFile fileUpload, HttpSession session) {
        log.info("add customer has customerLogo,customerNumber="+customerArgs.getCustomerNumber());
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerArgs,customer);
        transferPeriod(customer, customerArgs);
        ResponseMessage resp = validate(customerArgs);
        String user = getUser(session);
        if (resp.isOk()) {
            Customer checkCustomer = customerService.findByCustomerNumber(customer.getCustomerNumber());
            if (checkCustomer != null) {
                return ResponseMessage.error("商户编号已存在");
            }
            customer = packageCustomer(customer,"ADD");
            String fileLink = null;
            try {
                FtpFileStoreHelper helper = FtpFileStoreHelper.getHelper("ucm");
                fileLink = helper.upload(fileUpload.getInputStream(),"");
                customer.setCustomerLogo(fileLink);
                customerService.saveCustomer(new OperateEntity<Customer>(user, customer, OperateType.ADD, customer.getCustomerNumber()));
                return ResponseMessage.ok();
            } catch (Exception e) {
                log.error("新增商户异常", e);
                return ResponseMessage.error("新增商户失败", e);
            }
        }
        return resp;
    }
    //获得2代商户信息
    @RequestMapping("/get2GCustomer")
    @ResponseBody
    public Customer findCustomerBy2G(@RequestParam(value = "customerNumber") String customerNumber) {
        G2ServiceInterfaceFacade facade =
                RemoteServiceFactory.getService(RemotingProtocol.HTTPINVOKER, G2ServiceInterfaceFacade.class);
        CustomerBasicInfoDTO basicInfoDTO = facade.getCustomerBasicInfo(customerNumber);
        if(basicInfoDTO!=null) {
            Customer customer = new Customer();
            customer.setCustomerNumber(basicInfoDTO.getCustomernumber());
            customer.setCustomerName(basicInfoDTO.getFullname());
            return customer;
        }else{
            return null;
        }
    }

    //商户查询
    @RequestMapping("/query")
    public String customerQuery() {
        return "customer/customerQuery";
    }

    //商户详情
    @RequestMapping("/detail")
    public ModelAndView customerDetail(@RequestParam(value = "id") Long id) {
        Customer customer = customerService.findById(id);
        Long businessId = customer.getBusinessId();
        Business business = new Business();
        if (businessId != null) {
            business = businessService.getBusinessById(businessId);
        }
        ModelAndView mv = new ModelAndView();
        mv.addObject("customer", customer);
        mv.addObject("business", business);
        mv.setViewName("customer/customerDetail");
        return mv;
    }

    //跳转商户修改页面
    @RequestMapping("/toModify")
    public ModelAndView toCustomerModify(@RequestParam(value = "id") Long id) {
        Customer customer = customerService.findById(id);
        List<Business> businessList = businessService.findAllBusiness();
        List<Dictionary> payTypeList = dictionaryService.getDictionariesByType("PayType");
        List<Dictionary> industryTypeList = dictionaryService.getDictionariesByType("IndustryType");
        String payTypeInfo = customer.getPayTypeInfo();
        String[] payTypeInfoArray = payTypeInfo.split(",");
        StringBuffer codeBuffer = new StringBuffer();
        for (int i = 0; i < payTypeInfoArray.length; i++) {
            Dictionary dictionary = dictionaryService.getDictionaryByName(payTypeInfoArray[i]);
            if (dictionary != null) {
                String code = dictionary.getCode();
                if (i != payTypeInfoArray.length - 1) {
                    codeBuffer.append(code + ",");
                } else {
                    codeBuffer.append(code);
                }
            }
        }
        Integer validityPeriod = customer.getValidityPeriod();
        if(null != validityPeriod){
            customer.setValidityPeriod(validityPeriod / 24);
        }
        customer.setPayTypeInfo(codeBuffer.toString());
        ModelAndView mv = new ModelAndView();
        mv.addObject("customer", customer);
        mv.addObject("businessList", businessList);
        mv.addObject("payTypeList", payTypeList);
        mv.addObject("industryTypeList", industryTypeList);
        mv.setViewName("customer/customerModify");
        return mv;
    }


    //商户修改
    @RequestMapping("/modify")
    @ResponseBody
    public ResponseMessage modifyCustomer(@CommonArgs CustomerArgs customerArgs, HttpSession session) {
        log.info("modify customer no customerLogo,customerNumber="+customerArgs.getCustomerNumber());
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerArgs,customer);
        transferPeriod(customer, customerArgs);
        Customer checkCustomer = customerService.findById(customer.getId());
        ResponseMessage resp = validate(customerArgs);
        String user = this.getUser(session);
        if(resp.isOk()) {
            if (checkCustomer == null) {
               return ResponseMessage.error("商户不存在!");
            }
            if(checkCustomer.getCustomerLogo()!=null&&!"".equals(checkCustomer.getCustomerLogo())){
                customer.setCustomerLogo(checkCustomer.getCustomerLogo());
            }
            if(!checkCustomer.getAppType().equals(customer.getAppType())){//如果修改了版本要将二维码注销重新生成
                setQrCodeInactive(customer.getCustomerNumber());
            }
            // 如果修改了生效时间
            if (null == customer.getValidityPeriod()) {
                customer.setValidityPeriod(checkCustomer.getValidityPeriod());
            }
            packageCustomer(customer,"UPDATE");
            OperateEntity<Customer> en = new OperateEntity<Customer>(user,customer,OperateType.UPDATE,customer.getCustomerNumber());
            customerService.updateCustomer(en);
            return ResponseMessage.ok();
        }else{
            return  resp;
        }
    }

    @RequestMapping("/modifyCustomerLogo")
    @ResponseBody
    public ResponseMessage modifyCustomer(@CommonArgs CustomerArgs customerArgs, @RequestParam(value="fileUpload",required=false) MultipartFile fileUpload,HttpSession session) {
        log.info("modify customer has customerLogo,customerNumber="+customerArgs.getCustomerNumber());
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerArgs,customer);
        transferPeriod(customer, customerArgs);
        Customer checkCustomer = customerService.findById(customer.getId());
        ResponseMessage resp = validate(customerArgs);
        String user = this.getUser(session);
        if(resp.isOk()) {
            if (checkCustomer == null) {
                return ResponseMessage.error("商户不存在!");
            }
            if(!checkCustomer.getAppType().equals(customer.getAppType())){//如果修改了版本要将二维码注销重新生成
                log.info("apptypeChange,customerNumber="+customer.getCustomerNumber());
                setQrCodeInactive(customer.getCustomerNumber());
            }
            // 如果修改了生效时间
            if (null == customer.getValidityPeriod()) {
                customer.setValidityPeriod(checkCustomer.getValidityPeriod());
            }
            packageCustomer(customer,"UPDATE");//保存商户支付方式并配置防护支付方式
            if(fileUpload!=null) {
                try {
                    FtpFileStoreHelper helper = FtpFileStoreHelper.getHelper("ucm");
                    String fileLink = helper.upload(fileUpload.getInputStream(), "");
                    customer.setCustomerLogo(fileLink);
                    OperateEntity<Customer> en = new OperateEntity<Customer>(user,customer,OperateType.UPDATE,customer.getCustomerNumber());
                    customerService.updateCustomer(en);
                    return ResponseMessage.ok();
                } catch (Exception e) {
                    log.error("修改商户失败", e);
                    return ResponseMessage.error("操作失败", e);
                }
            } else {
                return ResponseMessage.error("请上传商户LOGO");
            }
        }else{
            return  resp;
        }
    }

    //封装Customer实体
    private Customer packageCustomer(Customer customer,String operateType){
        log.info("package customer,customerNumber="+customer.getCustomerNumber());
        String[] payTypeInfoArray = customer.getPayTypeInfo().split(",");
        StringBuilder payTypeInfoName = new StringBuilder();
        int payTypeInfoArrayLength = payTypeInfoArray.length;
        for (int i = 0; i < payTypeInfoArrayLength; i++) { //封装商户表的支付方式显示
            Dictionary payTypeDictionary = dictionaryService.getDictionaryByCode(payTypeInfoArray[i]);
            if (payTypeDictionary != null) {
                if (i != payTypeInfoArrayLength - 1) {
                    payTypeInfoName.append(payTypeDictionary.getName() + ",");
                } else {
                    payTypeInfoName.append(payTypeDictionary.getName());
                }
            }
        }
        if(null != customer.getValidityPeriod()){
            customer.setValidityPeriod(customer.getValidityPeriod() * 24);//订单有效期,页面单位为天,数据库存小时
        }
        customer.setPayTypeInfo(payTypeInfoName.toString());
        if("ADD".equals(operateType)) {//如果是新增操作保存商户支付方式配置
            log.info("package customer,operatetype=ADD,customerNumber="+customer.getCustomerNumber());
            for (int i = 0; i < payTypeInfoArrayLength; i++) {//保存支付配置
                CustomerFunction function = new CustomerFunction();
                function.setCustomerNumber(customer.getCustomerNumber());
                function.setTypeName(payTypeInfoArray[i]);
                customerFunctionService.saveCustomerFunction(function);//保存与支付方式的关系商户关系
            }
        }
        if("UPDATE".equals(operateType)){
            log.info("package customer,operatetype=UPDATE,customerNumber="+customer.getCustomerNumber());
            String customerNumber = customer.getCustomerNumber();
            List<CustomerFunction> list = customerFunctionService.getCustomerFunctionByCustomerNumber(customerNumber);
            if (null == list) { // 商户没有开通过功能
                for (int i = 0; i < payTypeInfoArrayLength; i++) {//保存支付配置
                    CustomerFunction function = new CustomerFunction();
                    function.setCustomerNumber(customer.getCustomerNumber());
                    function.setTypeName(payTypeInfoArray[i]);
                    customerFunctionService.saveCustomerFunction(function);//保存与支付方式的关系商户关系
                }
            } else {
                Map<String, CustomerFunction> map = listToMap(list);
                // 需要开通的
                for (String payType : payTypeInfoArray) {
                    CustomerFunction function = map.remove(payType);
                    if (null == function) {
                        CustomerFunction customerFunction = new CustomerFunction();
                        customerFunction.setCustomerNumber(customerNumber);
                        customerFunction.setTypeName(payType);
                        customerFunctionService.saveCustomerFunction(customerFunction);
                    } else {
                        if (Status.INACTIVE.equals(function.getStatus())) {
                            function.setStatus(Status.ACTIVE);
                            customerFunctionService.updateCustomerFunction(function);
                        }
                    }
                }
                // 剩余的需要关掉
                for (CustomerFunction function : map.values()) {
                    if (Status.ACTIVE.equals(function.getStatus())) {
                        function.setStatus(Status.INACTIVE);
                        customerFunctionService.updateCustomerFunction(function);
                    }
                }
            }
        }
        return customer;
    }

    /**
     * 数列转集合
     * @param list
     * @return
     */
    private Map<String, CustomerFunction> listToMap(List<CustomerFunction> list) {
        Map<String, CustomerFunction> result = new HashMap<String, CustomerFunction>();
        for (CustomerFunction function : list) {
            result.put(function.getTypeName(), function);
        }
        return result;
    }

    //注销二维码
    private void setQrCodeInactive(String customerNumber){
        List<QRCode> qrCodeList = qrCodeService.getQrCodeByCustomerNumber(customerNumber);
        if(qrCodeList!=null){
            for(int i=0;i<qrCodeList.size();i++){
                QRCode code = qrCodeList.get(i);
                if("ACTIVE".equals(code.getStatus().toString())) {
                    code.setStatus(MaterialStatus.INACTIVE);
                    qrCodeService.updateQrCode(code);
                }
            }
        }
    }

    /**
     * 转换
     * @param args
     * @return
     */
    private void transferPeriod(Customer customer, CustomerArgs args) {
        String period = args.getValidityPeriod();
        if (StringUtils.isNotBlank(period)) {
            customer.setValidityPeriod(Integer.valueOf(period));
        }
    }


}
