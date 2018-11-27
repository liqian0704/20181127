package com.yeepay.g3.core.ymf.facade.impl.laike;

import com.yeepay.app.httpinvoke.online.dto.CustomerBasicInfoDTO;
import com.yeepay.app.httpinvoke.online.facade.G2ServiceInterfaceFacade;
import com.yeepay.g3.core.ymf.entity.business.Business;
import com.yeepay.g3.core.ymf.entity.customer.Customer;
import com.yeepay.g3.core.ymf.entity.dictionary.Dictionary;
import com.yeepay.g3.core.ymf.service.BusinessService;
import com.yeepay.g3.core.ymf.service.CustomerFunctionService;
import com.yeepay.g3.core.ymf.service.CustomerService;
import com.yeepay.g3.core.ymf.service.DictionaryService;
import com.yeepay.g3.core.ymf.service.customer.CustomerUtil;
import com.yeepay.g3.facade.ymf.dto.laike.*;
import com.yeepay.g3.facade.ymf.enumtype.CustomerFlag;
import com.yeepay.g3.facade.ymf.enumtype.CustomerLevel;
import com.yeepay.g3.facade.ymf.enumtype.IndustryType;
import com.yeepay.g3.facade.ymf.enumtype.Status;
import com.yeepay.g3.facade.ymf.enumtype.common.AppType;
import com.yeepay.g3.facade.ymf.enumtype.trade.BalanceType;
import com.yeepay.g3.facade.ymf.enumtype.trade.TradeSystemEnum;
import com.yeepay.g3.facade.ymf.enumtype.trade.TrxCode;
import com.yeepay.g3.facade.ymf.exception.YmfTrxException;
import com.yeepay.g3.facade.ymf.facade.laike.CustomerBizFacade;
import com.yeepay.g3.utils.common.CheckUtils;
import com.yeepay.g3.utils.common.json.JSONUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import com.yeepay.g3.utils.rmi.RemotingProtocol;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;

/**
 * biz
 * Created by dongxulu on 16/12/16.
 */
@Service("customerBizFacade")
public class CustomerBizFacadeImpl implements CustomerBizFacade {
    private static Logger logger = LoggerFactory.getLogger(CustomerBizFacadeImpl.class);
    @Autowired
    private BusinessService businessService;
    @Autowired
    private CustomerFunctionService customerFunctionService;
    @Autowired
    private DictionaryService dictionaryService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerUtil customerUtil;

    @Override
    public CustomerRegistResponseDTO doRegist(CustomerRegistRequestDTO requestDTO) throws YmfTrxException {
        logger.info("调用 [商户注册接口] 请求参数:{}",JSONUtils.toJsonString(requestDTO));
        try {
            CustomerRegistResponseDTO customerRegistResponseDTO = registCustomer(requestDTO);
            logger.info("调用 [商户注册接口] 成功 请求参数:{}", JSONUtils.toJsonString(customerRegistResponseDTO));
            return customerRegistResponseDTO;
        } catch (Throwable e) {
            return respError(e);
        }
    }

    @Override
    public BalanceProductResponseDTO doCustomerBalanceProduct(BalanceProductRequestDTO balanceProductRequestDTO) {
        logger.info("doCustomerBalanceProduct request Param"+JSONUtils.toJsonString(balanceProductRequestDTO));
        BalanceProductResponseDTO balanceProductResponseDTO = new BalanceProductResponseDTO();
        String customerNumber = balanceProductRequestDTO.getCustomerNumber();
        BalanceType balanceProduct = balanceProductRequestDTO.getBalanceProduct();
        if(null == balanceProduct){
            balanceProductResponseDTO.setReturnCode(TrxCode.T1006.getCode());
            balanceProductResponseDTO.setReturnMsg(TrxCode.T1006.getMsg());
            return balanceProductResponseDTO;
        }
        Customer customer = customerService.findByCustomerNumber(customerNumber);
        if(null == customer){
            balanceProductResponseDTO.setReturnCode(TrxCode.T1023.getCode());
            balanceProductResponseDTO.setReturnMsg(TrxCode.T1023.getMsg());
            return balanceProductResponseDTO;
        }
        customer.setBalanceProduct(balanceProduct);
        customerService.updateCustomer(customer);
        balanceProductResponseDTO.setReturnCode(TrxCode.T00.getCode());
        return balanceProductResponseDTO;
    }

    @Override
    public ProductInfoResponseDTO getProductInfo(String customerNumber) {
        ProductInfoResponseDTO responseDTO = new ProductInfoResponseDTO();
        Customer customer = customerService.findByCustomerNumber(customerNumber);
        if(null == customer){
            responseDTO.setReturnCode(TrxCode.T1023.getCode());
            responseDTO.setReturnMsg(TrxCode.T1023.getMsg());
            return responseDTO;
        }
        Business business = businessService.getBusinessById(customer.getBusinessId());
        if(null != business){
            responseDTO.setBusiness(business.getBizCode());
        }
        responseDTO.setCustomerNumber(customer.getCustomerNumber());
        responseDTO.setPayTypeInfos(customer.getPayTypeInfo());
        responseDTO.setReturnCode(TrxCode.T00.getCode());
        return responseDTO;
    }

    /**
     * 处理错误结果
     */
    private CustomerRegistResponseDTO respError(Throwable e) {
        logger.error("调用 [商户注册接口] 异常:", e);
        CustomerRegistResponseDTO resp = new CustomerRegistResponseDTO();
        YmfTrxException exception = null ;
        if (e instanceof YmfTrxException) {
            exception = (YmfTrxException) e;
        } else if(e instanceof IllegalArgumentException) {
            exception = new YmfTrxException(TrxCode.T1006, e.getMessage());
        } else {
            exception = YmfTrxException.SYSTEM_ERROR;
        }
        resp.setReturnCode(exception.getCode());
        resp.setReturnMsg(exception.getMessage());
        return resp;
    }

    /**
     * 注册商户
     */
    private CustomerRegistResponseDTO registCustomer(CustomerRegistRequestDTO requestDTO) throws YmfTrxException {
        CustomerRegistResponseDTO responseDTO = new CustomerRegistResponseDTO();
        if(null == requestDTO){
            throw YmfTrxException.PARAMS_INVALID_ERROR;
        }
        if (requestDTO.getIndustryType() == null) {
            requestDTO.setIndustryType(IndustryType.OTHER);
        }
        G2ServiceInterfaceFacade facade =
                RemoteServiceFactory.getService(RemotingProtocol.HTTPINVOKER, G2ServiceInterfaceFacade.class);
        CustomerBasicInfoDTO basicInfoDTO = facade.getCustomerBasicInfo(requestDTO.getCustomerNumber());
        if (basicInfoDTO == null) {
            logger.error("二代未查询到商户信息");
            throw YmfTrxException.SYSTEM_ERROR;
        }

        Customer checkCustomer = customerService.findByCustomerNumber(requestDTO.getCustomerNumber());
        if(null != checkCustomer){
            throw YmfTrxException.CUSTOMER_OPEN_LAIKE_ERROR ;
        }
//      业务方编码
        String businessCode = requestDTO.getBusinessCode();
        CheckUtils.notEmpty(businessCode,"业务方编码");
        CheckUtils.notEmpty(requestDTO.getBusinessName(),"业务方名称");
        Business business = businessService.getBusinessByCode(businessCode);
        long bussId = 0;
        if(null==business){
//          没有业务方则新增业务方
            Business newBusiness = new Business();
            newBusiness.setStatus(Status.ACTIVE);
            newBusiness.setBizCode(businessCode);
            newBusiness.setBizName(requestDTO.getBusinessName());
            newBusiness.setCreateTime(new Date());
            logger.info("创建新的业务方:{}",JSONUtils.toJsonString(newBusiness));
            bussId = businessService.saveBusiness(newBusiness);
        } else {
            bussId = business.getId();
        }
        Customer customer = initCustomer(basicInfoDTO,requestDTO,bussId);
        return pakageResponse(customer);
    }

    private Customer initCustomer(CustomerBasicInfoDTO get2GCustomerDTO,CustomerRegistRequestDTO requestDTO,
                                  long bussId) throws YmfTrxException{
        Customer customer = new Customer();
        customer.setBusinessId(bussId);
        customer.setCustomerNumber(get2GCustomerDTO.getCustomernumber());
        customer.setCustomerName(get2GCustomerDTO.getFullname());
        // add 2017-03-03后的商户 区分存量商户,交易通道大算,统一收银台.
        customer.setTradeSystem(TradeSystemEnum.OPR);
        customer.setCustomerFlag(CustomerFlag.OPR_AFTER);
        // add
        CheckUtils.notEmpty(requestDTO.getAppType(),"商户应用类型");
        String[] collectTypes = requestDTO.getCollectType().split(",");
        if(collectTypes.length<1){
            throw new YmfTrxException(TrxCode.T1025);
        }
        String[] payTypes = requestDTO.getPayType().split(",");
        if(payTypes.length<1){
            throw new YmfTrxException(TrxCode.T1024);
        }
        String[] dics = (String[])ArrayUtils.addAll(collectTypes,payTypes);
        StringBuffer payTypeBuffer = new StringBuffer();
        for(String dic:dics){
            Dictionary dictionary = dictionaryService.getDictByTypeAndCode("PayType",dic);
            if(dictionary != null){
                payTypeBuffer.append(dictionary.getName()).append(",");
            }
        }

        if(payTypeBuffer.length()<=0){
            logger.error("支付方式为空!",payTypeBuffer.toString());
            throw new YmfTrxException(TrxCode.T1024);
        }
        //将需要开通的功能存入商户功能表
        customerUtil.updateCustomerFunction(dics,customer);
        String payTypeInfo = payTypeBuffer.toString().substring(0,payTypeBuffer.length()-1);
        customer.setPayTypeInfo(payTypeInfo);
        customer.setIndustryType(requestDTO.getIndustryType().toString());
        customer.setStatus(Status.ACTIVE);
        customer.setAppType(requestDTO.getAppType());
        String[] apptypes = requestDTO.getAppType().split(",");
        if(Arrays.asList(apptypes).contains(AppType.INDUSTRY.toString())){
            customer.setCustomerLevel(CustomerLevel.A);
//          默认24小时
            customer.setValidityPeriod(24);
        }else{
            customer.setCustomerLevel(CustomerLevel.B);
        }
        return customer;
    }

    private CustomerRegistResponseDTO pakageResponse(Customer customer){
        customerService.saveCustomerAndShop(customer);
        CustomerRegistResponseDTO responseDTO = new CustomerRegistResponseDTO();
        responseDTO.setCustomerNumber(customer.getCustomerNumber());
        responseDTO.setStatus(customer.getStatus());
        responseDTO.setAppType(customer.getAppType());
        responseDTO.setCollectType(customer.getCollectTypeInfo());
        responseDTO.setCreateDate(customer.getCreateTime());
        responseDTO.setIndustryType(IndustryType.valueOf(customer.getIndustryType()));
        responseDTO.setPayType(customer.getPayTypeInfo());
        responseDTO.setReturnCode(TrxCode.T00.getCode());
        return responseDTO;
    }

}
