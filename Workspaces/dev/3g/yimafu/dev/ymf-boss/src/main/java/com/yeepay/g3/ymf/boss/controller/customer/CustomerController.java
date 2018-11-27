package com.yeepay.g3.ymf.boss.controller.customer;

import com.yeepay.app.httpinvoke.online.dto.CustomerBasicInfoDTO;
import com.yeepay.app.httpinvoke.online.facade.G2ServiceInterfaceFacade;
import com.yeepay.g3.core.ymf.constants.Constants;
import com.yeepay.g3.core.ymf.entity.business.Business;
import com.yeepay.g3.core.ymf.entity.customer.Customer;
import com.yeepay.g3.core.ymf.entity.customer.CustomerArgs;
import com.yeepay.g3.core.ymf.entity.customer.CustomerFunction;
import com.yeepay.g3.core.ymf.entity.dictionary.Dictionary;
import com.yeepay.g3.core.ymf.entity.gratuity.Gratuity;
import com.yeepay.g3.core.ymf.entity.qrcode.QRCode;
import com.yeepay.g3.core.ymf.service.BusinessService;
import com.yeepay.g3.core.ymf.service.CustomerFunctionService;
import com.yeepay.g3.core.ymf.service.CustomerService;
import com.yeepay.g3.core.ymf.service.DictionaryService;
import com.yeepay.g3.core.ymf.service.customer.CustomerUtil;
import com.yeepay.g3.core.ymf.service.gratuity.GratuityService;
import com.yeepay.g3.core.ymf.service.qrcode.QrCodeService;
import com.yeepay.g3.core.ymf.support.OperateEntity;
import com.yeepay.g3.facade.ymf.dto.common.ResponseMessage;
import com.yeepay.g3.facade.ymf.enumtype.CustomerFlag;
import com.yeepay.g3.facade.ymf.enumtype.MaterialStatus;
import com.yeepay.g3.facade.ymf.enumtype.OperateType;
import com.yeepay.g3.facade.ymf.enumtype.Status;
import com.yeepay.g3.facade.ymf.enumtype.trade.TradeSystemEnum;
import com.yeepay.g3.utils.common.BeanUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import com.yeepay.g3.utils.rmi.RemotingProtocol;
import com.yeepay.g3.ymf.boss.controller.ValidateController;
import com.yeepay.g3.ymf.boss.support.annotations.CommonArgs;
import com.yeepay.utils.ftp.FtpFileStoreHelper;
import org.apache.commons.lang.ArrayUtils;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.yeepay.g3.core.ymf.constants.Constants.DEFAULT_GRATUITY;

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
    @Autowired
    private GratuityService gratuityService;
    @Autowired
    private CustomerUtil customerUtil;
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
            //   此处定义打赏模板
            String temp = customerArgs.getGratuityTmp();
            Gratuity gratuity = new Gratuity();
            //-1 是页面未选择打赏营销
            if(!"-1".equals(temp)){
                if(null==temp||StringUtils.isBlank(temp)){
                    List<Dictionary> dictionaryiList =  dictionaryService.getDictionariesByType(DEFAULT_GRATUITY);
                    Dictionary dictionary = dictionaryiList.get(0);
                    gratuity.setGratuityTemplate(dictionary.getValue());
                    gratuity.setCustomerNumber(customerArgs.getCustomerNumber());
                    gratuity.setStatus(Status.ACTIVE);
                }else{
                    gratuity.setGratuityTemplate(customerArgs.getGratuityTmp());
                    gratuity.setCustomerNumber(customerArgs.getCustomerNumber());
                    gratuity.setStatus(Status.ACTIVE);
                }
                gratuityService.save(gratuity);
            }
            //    打赏模板end
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
        String[] appTypes = customer.getAppType().split(",");

        Map<String,String> appTypeMap = new HashMap<String,String>();
        for(String appType:appTypes){
            appTypeMap.put(appType,appType);
        }
        ModelAndView mv = new ModelAndView();
        mv.addObject("customer", customer);
        mv.addObject("business", business);
        mv.addObject("appType",appTypeMap);
        mv.setViewName("customer/customerDetail");
        return mv;
    }

    //跳转商户修改页面
    @RequestMapping("/toModify")
    public ModelAndView toCustomerModify(@RequestParam(value = "id") Long id) {
        Customer customer = customerService.findById(id);
        List<Business> businessList = businessService.findAllBusiness();
        List<Dictionary> payTypeList = dictionaryService.getDictionariesByType(Constants.PAY_TYPE);
        List<Dictionary> collectTypeList = dictionaryService.getDictionariesByType(Constants.COLLECT_TYPE);
        List<Dictionary> industryTypeList = dictionaryService.getDictionariesByType("IndustryType");
        StringBuffer collectCodeBuffer = new StringBuffer();
        for(Dictionary dic:collectTypeList){
           CustomerFunction function = customerFunctionService.getFunctionByCustomerNumberAndType(customer.getCustomerNumber(),dic.getCode());
            if(null != function){
                if(Status.ACTIVE.equals(function.getStatus())){
                    collectCodeBuffer.append(function.getTypeName()+",");
                }
            }
        }
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
        List<Gratuity> list = gratuityService.getByCustomerNumber(customer.getCustomerNumber());
        ModelAndView mv = new ModelAndView();
        if(list.size()>0){
            Gratuity gratuity = list.get(0);
            mv.addObject("gratuity", gratuity);
        }
        customer.setPayTypeInfo(codeBuffer.toString());
        if(collectCodeBuffer.toString().length()>1){
            customer.setCollectTypeInfo(collectCodeBuffer.toString().substring(0,collectCodeBuffer.toString().length()-1));
        }
        mv.addObject("customer", customer);
        mv.addObject("businessList", businessList);
        mv.addObject("payTypeList", payTypeList);
        mv.addObject("collectTypeList",collectTypeList);
        mv.addObject("industryTypeList", industryTypeList);
        mv.setViewName("customer/customerModify");
        return mv;
    }


    @RequestMapping(value ="/payModify")
    @ResponseBody
    public ResponseMessage toPayModify(@RequestParam(value = "id") String id,@RequestParam(value = "customerIds") String customerIds){
        log.info("change customer tradeSystem,customerNumber ID="+id);
        try {
            if (StringUtils.isNotEmpty(customerIds)) {
                if (customerIds.endsWith(",")) {
                    customerIds = customerIds.substring(0, customerIds.length() - 1);
                }
                String[] customerId = customerIds.split(",");
                if (null != customerId && customerId.length > 0) {
                    for (String customerid : customerId) {
                        customerService.changeTradeSystemForId(customerid);
                    }
                }
                return ResponseMessage.ok();
            } else if (StringUtils.isNotEmpty(id)) {
                customerService.changeTradeSystemForId(id);
                return ResponseMessage.ok();
            } else {
                return ResponseMessage.error("未选择商户,请选择商户!");
            }
        }catch(Exception e){
            log.error("切换通道出错!",e);
            return ResponseMessage.error("系统错误!");
        }
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

            log.info("checkCustomer.getAppType():"+checkCustomer.getAppType());
            log.info("customer.getAppType():"+customer.getAppType());
            if(!checkCustomer.getAppType().equals(customer.getAppType())){
                //不在重新生成二维码
                customer.setAppType(customer.getAppType());
//              setQrCodeInactive(customer.getCustomerNumber());
            }

            // 如果修改了生效时间
            if (null == customer.getValidityPeriod()) {
                customer.setValidityPeriod(checkCustomer.getValidityPeriod());
            }

            if(StringUtils.isNotBlank(customerArgs.getGratuityTmp())){
                Gratuity gratuity = gratuityService.getByCustomerNumber(customer.getCustomerNumber()).get(0);
                gratuity.setGratuityTemplate(customerArgs.getGratuityTmp());
                gratuityService.update(gratuity);
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
        String[] collectTypeArray = customer.getCollectTypeInfo().split(",");
        String[] dics = (String[])ArrayUtils.addAll(payTypeInfoArray,collectTypeArray);
        log.info("###dics :" + Arrays.asList(dics).toString());
        StringBuilder payTypeInfoName = new StringBuilder();
        StringBuilder collectTypeInfoName = new StringBuilder();
        for (String dicType:Arrays.asList(dics)) { //封装商户表的支付方式显示
            Dictionary dictionary = dictionaryService.getDictionaryByCode(dicType);
            if (dictionary != null) {
                if(Constants.PAY_TYPE.equals(dictionary.getType())){
                    payTypeInfoName.append(dictionary.getName() + ",");
                }else if(Constants.COLLECT_TYPE.equals(dictionary.getType())){
                    collectTypeInfoName.append(dictionary.getName()+",");
                }
            }
        }
        String payTypeinfo = payTypeInfoName.toString().substring(0,payTypeInfoName.toString().length()-1);
        String collectTypeInfo = collectTypeInfoName.toString().substring(0,collectTypeInfoName.toString().length()-1);
        log.info("payTypeinfo:"+payTypeinfo+"  collectTypeInfo : "+collectTypeInfo);
        customer.setPayTypeInfo(payTypeinfo);
        customer.setCollectTypeInfo(collectTypeInfo);
        // add 2017-03-03后的商户 区分存量商户,交易通道大算,统一收银台.
        customer.setTradeSystem(TradeSystemEnum.OPR);
        customer.setCustomerFlag(CustomerFlag.OPR_AFTER);
        // add
        if(null != customer.getValidityPeriod()){
            customer.setValidityPeriod(customer.getValidityPeriod() * 24);//订单有效期,页面单位为天,数据库存小时
        }

        if("ADD".equals(operateType)) {//如果是新增操作保存商户支付方式配置
            log.info("package customer,operatetype=ADD,customerNumber="+customer.getCustomerNumber());
            customerUtil.updateCustomerFunction(dics,customer);
        }
        if("UPDATE".equals(operateType)){
            log.info("package customer,operatetype=UPDATE,customerNumber="+customer.getCustomerNumber());
            String customerNumber = customer.getCustomerNumber();
            List<CustomerFunction> list = customerFunctionService.getCustomerFunctionByCustomerNumber(customerNumber);
            if (null == list) { // 商户没有开通过功能
                for (String payType:dics) {//保存支付配置 支付方式与收款类型
                    CustomerFunction function = new CustomerFunction();
                    function.setCustomerNumber(customer.getCustomerNumber());
                    function.setTypeName(payType);
                    customerFunctionService.saveCustomerFunction(function);//保存与支付方式的关系商户关系
                }
            } else {
                Map<String, CustomerFunction> map = listToMap(list);
                // 需要开通的
                for (String payType : dics) {
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
