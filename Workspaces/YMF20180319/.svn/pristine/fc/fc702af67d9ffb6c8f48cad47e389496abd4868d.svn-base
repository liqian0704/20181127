package com.yeepay.g3.core.ymf.dao.qrcode;

import com.yeepay.g3.core.ymf.entity.qrcode.QRCode;
import com.yeepay.g3.core.ymf.entity.qrcode.QRCodeParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QRCodeMapper {
    int countByFilter(QRCodeParam filter);

    int deleteByFilter(QRCodeParam filter);

    int deleteByPrimaryKey(Long id);

    int insert(QRCode record);

    int insertSelective(QRCode record);

    List<QRCode> selectByFilter(QRCodeParam filter);

    QRCode selectByPrimaryKey(Long id);

    int updateByFilterSelective(@Param("record") QRCode record, @Param("example") QRCodeParam filter);

    int updateByFilter(@Param("record") QRCode record, @Param("example") QRCodeParam filter);

    int updateByPrimaryKeySelective(QRCode record);

    int updateByPrimaryKey(QRCode record);

    int batchCreateQrCode(List<QRCode> orList);
    
    int updateStatusByQrId(@Param("status") String status,@Param("qrId") String qrId);
}