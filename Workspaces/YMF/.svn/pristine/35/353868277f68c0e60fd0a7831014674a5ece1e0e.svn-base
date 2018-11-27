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

   String getBusinessCodeByQrID(@Param("qrID") String qrID);

   List<QRCode> queryQrContentEmpty();

   List<QRCode> queryShopNumberEmpty();

    /**
     * 查询所有旧的二维码的地址,只查询1000条
     */
    List<QRCode> queryOldFtpUrl(@Param("prefix") String prefix);

}