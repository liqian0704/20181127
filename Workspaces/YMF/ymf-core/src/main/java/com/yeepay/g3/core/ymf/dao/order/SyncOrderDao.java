package com.yeepay.g3.core.ymf.dao.order;

import com.yeepay.g3.facade.ymf.dto.agent.SyncOrderDTO;
import com.yeepay.g3.facade.ymf.dto.agent.SyncOrderTypeDTO;
import com.yeepay.g3.facade.ymf.dto.agent.SyncS0OrderDTO;
import com.yeepay.g3.facade.ymf.dto.common.CountResponse;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SyncOrderDao {

    /**
     * 根据商户编号和订单时间汇总来客订单
     * @param customerNumbers 商户编号集合
     * @param from 开始时间 包含
     * @param to 结束时间 不包含
     * @return 成功的交易订单
     */
    CountResponse countSyncOrder(@Param("from") Date from,
                                 @Param("to") Date to,
                                 @Param("list") List<String> customerNumbers);

    /**
     * 根据商户编号和订单时间查询来客订单
     * @param customerNumbers 商户编号集合
     * @param from 开始时间 包含
     * @param to 结束时间 不包含
     * @return 成功的交易订单
     */
    List<SyncOrderDTO> querySyncOrder(@Param("from") Date from,
                                      @Param("to") Date to,
                                      @Param("list") List<String> customerNumbers);

    /**
     * 秒到汇总
     * @param from
     * @param to
     * @param customerNumbers
     * @return
     */
    CountResponse countS0Remit(@Param("from")Date from, @Param("to")Date to, @Param("list")List<String> customerNumbers);

    /**
     * 秒到打款纪录
     * @param from
     * @param to
     * @param customerNumbers
     * @return
     */
    List<SyncS0OrderDTO> querySyncRemit(@Param("from")Date from, @Param("to")Date to, @Param("list")List<String> customerNumbers);
    /**
     *
     * @param customerNumbers  商户编号集合
     * @param from  开始时间 包含
     * @param to  结束时间 不包含
     * @param startNum  开始行号
     * @param endNum   结束行号
     * @return  成功的交易订单
     */
    List<SyncOrderTypeDTO> querySyncOrderForPage(@Param("from") Date from,
                                                 @Param("to") Date to,
                                                 @Param("list") List<String> customerNumbers,
                                                 @Param("startNum") int startNum,
                                                 @Param("endNum") int endNum);
}