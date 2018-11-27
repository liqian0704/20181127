package com.yeepay.g3.ymf.pay.controller.ymfbill.billDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.yeepay.g3.core.ymf.entity.billDto.QueryResultParamDTO;

import java.util.List;


@XmlAccessorType(XmlAccessType.FIELD)
// XML文件中的根标识
@XmlRootElement(name = "ORDERLIST")
// 控制JAXB 绑定类中属性和字段的排序
@XmlType(propOrder = {
        "ORDERINFO"
})
public class BackXmlDTO {
    private List<QueryResultParamDTO> ORDERINFO;

    public List<QueryResultParamDTO> getORDERINFO() {
        return ORDERINFO;
    }

    public void setORDERINFO(List<QueryResultParamDTO> resultParamDTOS) {
        this.ORDERINFO = resultParamDTOS;
    }

    @Override
    public String toString() {
        return "ORDERLIST [" +
                "ORDERINFO=" + ORDERINFO +
                ']';
    }


}
