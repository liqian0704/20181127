package com.yeepay.g3.ymf.pay.controller.link;

import com.yeepay.g3.core.ymf.biz.OrderCheckBiz;
import com.yeepay.g3.core.ymf.entity.order.Order;
import com.yeepay.g3.core.ymf.entity.order.Payment;
import com.yeepay.g3.core.ymf.utils.Base62;
import com.yeepay.g3.facade.opr.enumtype.UserTypeEnum;
import com.yeepay.g3.facade.ymf.enumtype.trade.BalanceType;
import com.yeepay.g3.facade.ymf.enumtype.trade.PaymentStatus;
import com.yeepay.g3.facade.ymf.enumtype.trade.TrxCode;
import com.yeepay.g3.facade.ymf.exception.YmfTrxException;
import com.yeepay.g3.utils.common.StringUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.ymf.pay.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: 主扫链接跳转
 * @Author: xiaobin.liu
 * @Date: 17/3/21
 * @Time: 下午4:21
 */
@Controller
@RequestMapping("/link")
public class ShortLinkController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(ShortLinkController.class) ;
    @Autowired
    private OrderCheckBiz orderCheckBiz;

    /**
     * 主扫(标准收银台)短链接跳转
     * @param id       paymentId
     * @return
     */
    @RequestMapping("/{id}")
    public String payLink(@PathVariable("id") String id, HttpServletRequest request) {
        try {
            logger.info("收到短链请求,paymentId={}",id);
            String paymentId = Base62._62_10(id);
            Payment payment = paymentService.findById(Long.parseLong(paymentId));
            if (payment == null) {
                //无效链接
                throw YmfTrxException.PARAMS_INVALID_ERROR;
            }
            if (PaymentStatus.SUCCESS.equals(payment.getPayStatus())) {
                //订单已经支付
                throw YmfTrxException.ORDER_COMMPLETE_ERROR;
            }
            //生成支付链接并重定向
            String token = payment.getPayUrl();
            if (StringUtils.isBlank(token)) {
                //订单未下单成功
                throw YmfTrxException.SYSTEM_ERROR;
            }
            Order order = orderService.findById(payment.getOrderId());

            //校验S0订单笔数限制
            if (BalanceType.S0 == order.getBalanceType()) {
                boolean b = orderCheckBiz.checkCustomerS0Limit(order.getCustomerNumber());
                if (!b) {
                    throw new YmfTrxException(TrxCode.T1041);
                }
            }

            //跳转标准收银台
            String cashierUrl = oprUrlBiz.standardCashier(payment.getCustomerNumber(),token,
                    "", order.getExternalId(), UserTypeEnum.USER_ID.toString());
            logger.info("重定向地址：{}",cashierUrl);
            return "redirect:" + cashierUrl;
        } catch (Exception e) {
            return toCommonErrorPage(request, handleException(e).toPromptMsg()) ;
        }
    }
}
