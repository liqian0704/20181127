package com.yeepay.g3.ymf.pay.controller.ymfbill.constants;

public class Constants {

    /**
     * 对账信息返回报文头字段
     */
    public static final String YMF_XML_INFO = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<PACKET type=\"RESPONSE\" version=\"1.0\">\n";

    public static final String YMF_XML_HEAD_SUCCESS = "\t<HEAD>\n" +
            "\t\t<REQUESTTYPE>935</REQUESTTYPE>\n" +
            "\t\t<RESPONSECODE>1</RESPONSECODE>\n" +
            "\t\t<RESPONSEMSG>SUCCESS</RESPONSEMSG>\n" +
            "\t</HEAD>\n";

    public static final String YMF_XML_HEAD_FAILURE = "\t<HEAD>\n" +
            "\t\t<REQUESTTYPE>935</REQUESTTYPE>\n" +
            "\t\t<RESPONSECODE>0</RESPONSECODE>\n" +
            "\t\t<RESPONSEMSG>系统异常</RESPONSEMSG>\n" +
            "\t</HEAD>\n";

    public static final String YMF_XML_PARAM_ERROR = "\t<HEAD>\n" +
            "\t\t<REQUESTTYPE>935</REQUESTTYPE>\n" +
            "\t\t<RESPONSECODE>2</RESPONSECODE>\n" +
            "\t\t<RESPONSEMSG>访问报文参数信息异常</RESPONSEMSG>\n" +
            "\t</HEAD>\n";

    public static final String YMF_XML_HMAC_ERROR = "\t<HEAD>\n" +
            "\t\t<REQUESTTYPE>935</REQUESTTYPE>\n" +
            "\t\t<RESPONSECODE>2</RESPONSECODE>\n" +
            "\t\t<RESPONSEMSG>访问报文商户编号与秘钥不匹配</RESPONSEMSG>\n" +
            "\t</HEAD>\n";

    public static final String YMF_XML_TIME_ERROR = "\t<HEAD>\n" +
            "\t\t<REQUESTTYPE>935</REQUESTTYPE>\n" +
            "\t\t<RESPONSECODE>2</RESPONSECODE>\n" +
            "\t\t<RESPONSEMSG>异常：访问报文时间间隔大于3天</RESPONSEMSG>\n" +
            "\t</HEAD>\n";

    public static final String YMF_XML_NULL_ERROR = "\t<HEAD>\n" +
            "\t\t<REQUESTTYPE>935</REQUESTTYPE>\n" +
            "\t\t<RESPONSECODE>2</RESPONSECODE>\n" +
            "\t\t<RESPONSEMSG>该商编下的商户在该段时间内不存在交易记录</RESPONSEMSG>\n" +
            "\t</HEAD>\n";

    public static final String YMF_XML_CUSNUM_ERROR = "\t<HEAD>\n" +
            "\t\t<REQUESTTYPE>935</REQUESTTYPE>\n" +
            "\t\t<RESPONSECODE>2</RESPONSECODE>\n" +
            "\t\t<RESPONSEMSG>该商编不存在</RESPONSEMSG>\n" +
            "\t</HEAD>\n";

    /**
     * 对账信息返回报文尾字段
     */
    public static final String YMF_XML_TAIL = "</PACKET>";

    /**
     * 对账信息返回报文body字段
     */
    public static final String YMF_XML_BODY_HEAD = "<BODY>";

    public static final String YMF_XML_BODY_TAIL = "</BODY>\n";

    /**
     * 对账信息返回报文ORDERLIST字段
     */
    public static final String YMF_XML_ORDERLIST_HEAD = "<ORDERLIST>";

    public static final String YMF_XML_ORDERLIST_TAIL = "</ORDERLIST>";

    /**
     * 对账信息返回报文BASEINFO字段
     */
    public static final String YMF_XML_BASEINFO_HEAD = "<BASEINFO>";

    public static final String YMF_XML_BASEINFO_TAIL = "</BASEINFO>";


    /**
     * 对账信息返回报文SIGNINFO字段
     */
    public static final String YMF_XML_SIGNINFO_HEAD = "<SIGNINFO>\n";

    public static final String YMF_XML_SIGNINFO_TAIL = "</SIGNINFO>\n";

    public static final String YMF_XML_SIGN_HEAD = "\t<sign>";

    public static final String YMF_XML_SIGN_TAIL = "</sign>\n";

    /**
     * 删除的字段
     */
    public static final String DELETE_XML_INFO = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>";

    /**
     * 时间间隔限制
     */
    public static final double TIME_SPAN_LIMIT = 3;
}



















