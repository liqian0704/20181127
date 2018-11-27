package com.yeepay.g3.ymf.pay.controller.ymfbill.util;

import com.yeepay.g3.core.ymf.entity.billDto.QueryResultParamDTO;
import com.yeepay.g3.ymf.pay.controller.ymfbill.billDto.BackXmlDTO;
import com.yeepay.g3.ymf.pay.controller.ymfbill.billDto.BaseInfoParamDTO;
import com.yeepay.g3.ymf.pay.controller.ymfbill.constants.Constants;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.List;


public class XmlBulider {

    public static String buildXml(List<QueryResultParamDTO> list, BaseInfoParamDTO baseInfoParamDTO, String hamc) {
        StringBuffer stringBuffer = new StringBuffer();
        BackXmlDTO backXmlDTO = new BackXmlDTO();
        backXmlDTO.setORDERINFO(list);
        String orderInfos = convertToXml(backXmlDTO);
        String baseInfo = convertToXml(baseInfoParamDTO);
        stringBuffer.append(Constants.YMF_XML_INFO);
        stringBuffer.append(Constants.YMF_XML_HEAD_SUCCESS);
        stringBuffer.append(Constants.YMF_XML_BODY_HEAD);
        stringBuffer.append(orderInfos);
        stringBuffer.append(baseInfo);
        stringBuffer.append(Constants.YMF_XML_SIGNINFO_HEAD);
        stringBuffer.append(Constants.YMF_XML_SIGN_HEAD);
        stringBuffer.append(hamc);
        stringBuffer.append(Constants.YMF_XML_SIGN_TAIL);
        stringBuffer.append(Constants.YMF_XML_SIGNINFO_TAIL);
        stringBuffer.append(Constants.YMF_XML_BODY_TAIL);
        stringBuffer.append(Constants.YMF_XML_TAIL);

        return stringBuffer.toString();
    }

    /**
     * 将对象直接转换成String类型的 XML输出
     *
     * @param obj
     * @return
     */
    private static String convertToXml(Object obj) {
        // 创建输出流
        StringWriter sw = new StringWriter();
        try {
            // 利用jdk中自带的转换类实现
            JAXBContext context = JAXBContext.newInstance(obj.getClass());

            Marshaller marshaller = context.createMarshaller();
            // 格式化xml输出的格式
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
                    Boolean.TRUE);
            // 将对象转换成输出流形式的xml
            marshaller.marshal(obj, sw);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        String resContent = sw.toString();
        int startIndex = resContent.indexOf(Constants.DELETE_XML_INFO);
        resContent = resContent.substring(startIndex + 55);

        return resContent;
    }



    public static void main(String[] args) {
//        QueryResultParamDTO qrpd1 = new QueryResultParamDTO("251234565678", "19876543", "dubang1", "", "", "", "", "", "t1", "", "", "", "", "", "") ;
//        QueryResultParamDTO qrpd2 = new QueryResultParamDTO("561234565678", "29876543", "dubang2", "", "", "", "", "", "s0", "", "", "", "", "", "") ;
//        QueryResultParamDTO qrpd3 = new QueryResultParamDTO("431234565678", "29876543", "dubang2", "", "", "", "", "", "s0", "", "", "", "", "", "") ;
//
//        BaseInfoParamDTO base = new BaseInfoParamDTO(20000000, new BigDecimal(4000.12),"2017-07-24 14:00:00", "2017-07-24 14:00:00");
//
//
//        List<QueryResultParamDTO> list = new ArrayList<QueryResultParamDTO>();
//        list.add(qrpd1);
//        list.add(qrpd2);
//        list.add(qrpd3);
//
//        BackXmlDTO backXmlDTO = new BackXmlDTO();
//        backXmlDTO.setORDERINFO(list);
//
////        String res = convertToXml(backXmlDTO);
//        String res = buildXml(list, base, "WERTYU123123123");
//
//        System.out.println(res);
    }


}
