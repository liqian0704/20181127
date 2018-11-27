package com.yeepay.g3.core.ymf.dao.order;

import com.yeepay.g3.core.ymf.entity.order.Order;
import com.yeepay.g3.facade.ymf.dto.common.CountResponse;
import com.yeepay.g3.facade.ymf.dto.order.OrderArgs;
import com.yeepay.g3.facade.ymf.dto.order.OrderDTO;
import com.yeepay.g3.facade.ymf.dto.order.OrderDetailDTO;
import com.yeepay.g3.facade.ymf.dto.order.OrderProofDTO;
import com.yeepay.g3.facade.ymf.dto.order.OrderProofQueryArgs;
import com.yeepay.g3.facade.ymf.dto.order.OrderQueryArgs;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao {

    /**
     * 根据参数查询订单
     * @param args 参数
     * @return
     */
    List<OrderDTO> queryOrderDTOByArgs(OrderArgs args);

    /**
     * 根据参数汇总
     * @param args 参数
     * @return
     */
    CountResponse countOrderByArgs(OrderArgs args);

    /**
     * 根据extendId更新订单
     * @param params
     */
    void updateExtendIdById(Order params);

    /**
     *
     * @param params
     */
    void updateOrderStatusById(Order params);

    /**
     * 根据请求参数获取订单详情DTO
     * @param args
     * @return
     */
    OrderDetailDTO findByQueryArgs(OrderQueryArgs args);

    /**
     * 根据商户编号产生电子凭证
     * @param orderProofQueryArgs
     * @return
     */
    List<OrderProofDTO> findProofByQueryArgs(OrderProofQueryArgs orderProofQueryArgs);
}