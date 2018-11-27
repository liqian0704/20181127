package com.yeepay.g3.core.ymf.dao.remit;

import com.yeepay.g3.core.ymf.entity.remit.Remittance;
import com.yeepay.g3.core.ymf.entity.remit.RemittanceParam;
import com.yeepay.g3.facade.ymf.enumtype.trade.RemitStatus;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface RemittanceMapper {
    int countByFilter(RemittanceParam filter);

    int deleteByFilter(RemittanceParam filter);

    int deleteByPrimaryKey(Long id);

    int insert(Remittance record);

    int insertSelective(Remittance record);

    List<Remittance> selectByFilter(RemittanceParam filter);

    Remittance selectByPrimaryKey(Long id);

    int updateByFilterSelective(@Param("record") Remittance record, @Param("example") RemittanceParam filter);

    int updateByFilter(@Param("record") Remittance record, @Param("example") RemittanceParam filter);

    int updateByPrimaryKeySelective(Remittance record);

    int updateByPrimaryKey(Remittance record);

    int updateByPrimaryKeyAndVersion(Remittance record);

    Remittance findByCustomerNumberAndCustomerOrderId(@Param("customerNumber") String customerNumber,
                                                      @Param("customerOrderID") String customerOrderID);
    Remittance findByYeepayOrderId(@Param("yeepayOrderId") String yeepayOrderId);

    List<Remittance> findByRemitStatus(@Param("remitStatus") RemitStatus remitStatus,
                                       @Param("begin") Date begin, @Param("end") Date end,
                                       @Param("startRow") int startRow, @Param("endRow") int endRow);

    int findByRemitStatusCount(@Param("remitStatus") RemitStatus remitStatus,
                               @Param("begin") Date begin,@Param("end") Date end);



}