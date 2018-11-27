package com.yeepay.g3.core.ymf.dao.qrcode;

import com.yeepay.g3.core.ymf.entity.qrcode.QrRelation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dongxulu on 16/12/8.
 */
@Repository
public interface QrRelationDao {
    List<QrRelation> getByAgentNumberAndRequestId(@Param("agentNumber") String agentNumber,@Param("requestID") String requestID);
    QrRelation getByQrCodeID(@Param("qrCodeID")String qrCodeID);
    List<QrRelation> getByCustomerNumberAndRequestId(@Param("customerNumber") String customerNumber, @Param("requestID")String requestID);
    List<QrRelation> getBySalesNameAndRequestId(@Param("salesName") String salesName, @Param("requestID")String requestID);
}
