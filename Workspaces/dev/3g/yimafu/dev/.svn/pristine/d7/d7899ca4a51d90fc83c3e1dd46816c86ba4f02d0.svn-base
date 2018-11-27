package com.yeepay.g3.core.ymf.dao.qrcode;

import com.yeepay.g3.core.ymf.entity.qrcode.QRCode;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QRCodeDao {

    List<QRCode> getByAgentNumberAndRelationID(@Param("agentNumber")String agentNumber,
                                               @Param("relationID")String relationID);
    int insertSignedPaperQrcode(QRCode record);
}