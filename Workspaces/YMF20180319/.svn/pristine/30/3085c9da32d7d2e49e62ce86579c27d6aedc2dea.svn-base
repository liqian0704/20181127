package com.yeepay.g3.ymf.pay.controller.ymfbill;

import com.yeepay.g3.core.ymf.entity.billDto.QueryResultParamDTO;
import com.yeepay.g3.core.ymf.entity.customer.Customer;
import com.yeepay.g3.core.ymf.ext.G2ServiceExt;
import com.yeepay.g3.core.ymf.service.CustomerService;
import com.yeepay.g3.core.ymf.service.PaymentService;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.ymf.pay.controller.BaseController;
import com.yeepay.g3.ymf.pay.controller.OrderPayController;
import com.yeepay.g3.ymf.pay.controller.ymfbill.billDto.BaseInfoParamDTO;
import com.yeepay.g3.ymf.pay.controller.ymfbill.billDto.QueryParamDTO;
import com.yeepay.g3.ymf.pay.controller.ymfbill.constants.Constants;
import com.yeepay.g3.ymf.pay.controller.ymfbill.util.ValidateUtil;
import com.yeepay.g3.ymf.pay.controller.ymfbill.util.XmlBulider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.validation.ConstraintViolation;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * 都邦对账信息查询
 */
@Controller
@RequestMapping("/queryAccountInfo")
public class YmfAccountInfoController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(OrderPayController.class);
    private static XmlBulider xmlBulider = new XmlBulider();//xml拼装工具类
    private static ValidateUtil validate = new ValidateUtil();//参数验证工具类

    @Autowired
    private G2ServiceExt g2ServiceExt;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private CustomerService customerService;

    /**
     * 查询信息
     */
    @RequestMapping(value = "/query", produces = "text/html;charset=UTF-8;")
    @ResponseBody
    public String query(QueryParamDTO queryParamDTO) throws Exception {

        String backXml = Constants.YMF_XML_PARAM_ERROR;
        /**
         * 1、解析访问xml，获取入参，参数交验
         */
        //参数非空校验
        Set<ConstraintViolation<Object>> violations = validate.validateDTOproperties(queryParamDTO);
        if (violations.size() != 0) {
            logger.warn("-----------访问报文参数交验失败--------------");
            backXml = buildErrorXml(backXml);
            return backXml;
        }
        String companyCode = queryParamDTO.getCustomerNumber();//商编
        String paytimeStart = queryParamDTO.getPaytimeStart();//支付开始时间
        String paytimeEnd = queryParamDTO.getPaytimeEnd();//支付结束时间
        String sign = queryParamDTO.getSign();//md5密文

        Customer customer = customerService.findByCustomerNumber(companyCode);
        if(customer == null) {
            logger.warn("-----------访问报文参数交验失败--------------");
            backXml = buildErrorXml(Constants.YMF_XML_CUSNUM_ERROR);
        } else {

            //时间间隔校验
            HashMap<String, Object> sendParamsMap = generMap(companyCode, paytimeStart, paytimeEnd, sign);
            double daysSpan = getTimeSpan(paytimeStart, paytimeEnd);
            if (daysSpan > Constants.TIME_SPAN_LIMIT) {
                logger.info("-----------访问报文时间间隔大于3天--------------");
                return buildErrorXml(Constants.YMF_XML_TIME_ERROR);
            } else {
                //md5密文验签
                String md5token = md5ReqValidate(sendParamsMap);
                boolean isMd5Hamc = (md5token.equals(sign)) ? true : false;
                if (!isMd5Hamc) {
                    logger.info("-----------访问报文验签失败--------------");
                    backXml = buildErrorXml(Constants.YMF_XML_HMAC_ERROR);
                } else {
                    /**
                     * 2、根据入参查询信息，查询数据库获取info数据实体；获取总记录数，总交易金额
                     *   QueryResultParamDTO实体列表
                     */
                    List<QueryResultParamDTO> backList = paymentService.billInfoQuery(sendParamsMap);
                    backList = orderFilter(backList);

                    logger.info("-------查询信息实体列表-----" + backList.size());
                    if (null == backList || backList.size() == 0) {
                        logger.info("-----------没有查到交易记录--------------");
                        backXml = buildErrorXml(Constants.YMF_XML_NULL_ERROR);
                    } else {
                        BigDecimal totalAmount = paymentService.totalAmountQuery(sendParamsMap);
                        logger.info("-----总金额：-----" + totalAmount);
                        int totalCount = paymentService.totalCountQuery(sendParamsMap);
                        logger.info("-----总交易笔数：-----" + totalCount);
                        //获取返回报文md5密文，拼装返回待验签的字段实体
                        HashMap<String, Object> validateParamsMap = generMd5Map(companyCode, totalAmount,
                                totalCount, paytimeStart, paytimeEnd);
                        String backMd5token = md5RespValidate(validateParamsMap);
                        BaseInfoParamDTO baseInfoParamDTO = bulidBaseInfoParamDTO(companyCode, totalAmount,
                                totalCount, paytimeStart, paytimeEnd);
                        /**
                         * 3、组装xml报文
                         */
                        backXml = xmlBulider.buildXml(backList, baseInfoParamDTO, backMd5token);
                    }

                }
            }
        }
        return backXml;


    }


    /**
     * 过滤问题单，做数据库容错性改造
     * @param
     * @return
     */
    private static List<QueryResultParamDTO> orderFilter(List<QueryResultParamDTO> list) {
        List<QueryResultParamDTO> resList = list;
        for(int i = 0; i < resList.size(); i++ ) {
            System.out.println(resList.get(i));
            QueryResultParamDTO resultParam = resList.get(i);
            boolean flag = validate(resultParam);
            if(flag == false) {
                resList.remove(i);
            }
        }

        return resList;
    }

    private static boolean validate(QueryResultParamDTO resultParam) {
        boolean flag = true;
        Set<ConstraintViolation<Object>> violations = validate.validateDTOproperties(resultParam);
        if (violations.size() != 0) {
            flag = false;
            logger.info("----------返回结果参数交验失败----------{}", flag);
        }
        return flag;
    }


    private static String buildErrorXml(String errorXml) {
        String backXml = Constants.YMF_XML_INFO + errorXml + Constants.YMF_XML_TAIL;
        return backXml;
    }

    private static BaseInfoParamDTO bulidBaseInfoParamDTO(String companyCode, BigDecimal totalAmount,
                                            int totalCount, String paytimeStart, String paytimeEnd) {
        BaseInfoParamDTO baseInfoParamDTO = new BaseInfoParamDTO();
        baseInfoParamDTO.setCustomerNumber(companyCode);
        baseInfoParamDTO.setTotalAmount(totalAmount);
        baseInfoParamDTO.setTotalCount(totalCount);
        baseInfoParamDTO.setPaytimeStart(paytimeStart);
        baseInfoParamDTO.setPaytimeEnd(paytimeEnd);
        return baseInfoParamDTO;
    }

    private static Double getTimeSpan(String paytimeStart, String paytimeEnd) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = sdf.parse(paytimeStart);
        Date date2 = sdf.parse(paytimeEnd);

        long timDif = date2.getTime() - date1.getTime();
        double days = timDif / (1000 * 60 * 60 * 24.0);

        return days;
    }

    /**
     * request 构建请求map
     * @param companyCode
     * @param paytimeStart
     * @param paytimeEnd
     * @param sign
     * @return
     */
    private static HashMap<String, Object> generMap(String companyCode, String paytimeStart,
                                                    String paytimeEnd, String sign) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("COMPANYCODE", companyCode);
        map.put("PAYTIMESTART", paytimeStart);
        map.put("PAYTIMEEND", paytimeEnd);
        map.put("SIGN", sign);

        return map;
    }

    /**
     * request：构建验签串= 访问参数信息 + 秘钥
     *
     * @param map
     * @return
     */
    private String generMd5Text(HashMap<String, Object> map) {
        String companyCode = (String) map.get("COMPANYCODE");
        String paytimeStart = (String) map.get("PAYTIMESTART");
        String paytimeEnd = (String) map.get("PAYTIMEEND");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("COMPANYCODE=");
        stringBuffer.append(companyCode);
        stringBuffer.append("&PAYTIMESTART=");
        stringBuffer.append(paytimeStart);
        stringBuffer.append("&PAYTIMEEND=");
        stringBuffer.append(paytimeEnd);
        stringBuffer.append("&KEY=");
        stringBuffer.append(g2ServiceExt.queryCustomerHmacKey(companyCode));
//        stringBuffer.append("y4ruw9chv636u9uq4vjkvvcct815pr9uu94mh42nma1x9cmxhb2cncpte6w8");

        return stringBuffer.toString();
    }

    /**
     * 构建验签串= 返回参数信息 + 秘钥
     * @param companyCode
     * @param totalAmount
     * @param totalCount
     * @param paytimeStart
     * @param paytimeEnd
     * @return
     */
    private static HashMap<String, Object> generMd5Map(String companyCode, BigDecimal totalAmount,
                                      int totalCount, String paytimeStart, String paytimeEnd) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("COMPANYCODE", companyCode);
        map.put("TOTALAMOUNT", totalAmount);
        map.put("TOTALCOUNT", totalCount);
        map.put("PAYTIMESTART", paytimeStart);
        map.put("PAYTIMEEND", paytimeEnd);

        return map;
    }

    /**
     * request：构建验签串= 访问参数信息 + 秘钥
     *
     * @param map
     * @return
     */
    private String generMd5MapText(HashMap<String, Object> map) {
        String companyCode = (String) map.get("COMPANYCODE");
        BigDecimal totalAmount = (BigDecimal) map.get("TOTALAMOUNT");
        int totalCount = (Integer) map.get("TOTALCOUNT");
        String paytimeStart = (String) map.get("PAYTIMESTART");
        String paytimeEnd = (String) map.get("PAYTIMEEND");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("COMPANYCODE=");
        stringBuffer.append(companyCode);
        stringBuffer.append("&TOTALCOUNT=");
        stringBuffer.append(totalCount);
        stringBuffer.append("&TOTALAMOUNT=");
        stringBuffer.append(totalAmount);
        stringBuffer.append("&PAYTIMESTART=");
        stringBuffer.append(paytimeStart);
        stringBuffer.append("&PAYTIMEEND=");
        stringBuffer.append(paytimeEnd);
        stringBuffer.append("&KEY=");
        stringBuffer.append(g2ServiceExt.queryCustomerHmacKey(companyCode));
//        stringBuffer.append("y4ruw9chv636u9uq4vjkvvcct815pr9uu94mh42nma1x9cmxhb2cncpte6w8");

        return stringBuffer.toString();
    }

    /**
     * md5验签算法
     *
     * @param
     * @return
     */
    private String md5Validate(String md5String) throws Exception {

//        md5String = generMd5Text(map);
//        String sign = (String)map.get("SIGN");
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(md5String.getBytes("UTF-8"));
        byte b[] = md.digest();
        int i;
        StringBuffer stringBuffer = new StringBuffer("");
        for (int offset = 0; offset < b.length; offset++) {
            i = b[offset];
            if (i < 0)
                i += 256;
            if (i < 16)
                stringBuffer.append("0");
            stringBuffer.append(Integer.toHexString(i));
        }

        String md5token = stringBuffer.toString();

        return md5token;

    }

    private String md5ReqValidate(HashMap<String, Object> map) throws Exception {
        String md5String = generMd5Text(map);
        String md5token = md5Validate(md5String);

        return md5token;
    }
    private String md5RespValidate(HashMap<String, Object> map) throws Exception {
        String md5String = generMd5MapText(map);
        String md5token = md5Validate(md5String);

        return md5token;
    }

}
