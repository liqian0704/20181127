package com.yeepay.g3.ymf.boss.controller.order;

import com.yeepay.g3.core.ymf.biz.trade.SupplyBiz;
import com.yeepay.g3.core.ymf.biz.trade.TradeBiz;
import com.yeepay.g3.core.ymf.constants.Constants;
import com.yeepay.g3.core.ymf.entity.customer.Customer;
import com.yeepay.g3.core.ymf.entity.order.Order;
import com.yeepay.g3.core.ymf.entity.order.Payment;
import com.yeepay.g3.core.ymf.service.CustomerService;
import com.yeepay.g3.core.ymf.service.PaymentService;
import com.yeepay.g3.core.ymf.service.order.OrderService;
import com.yeepay.g3.core.ymf.support.NCApiSupport;
import com.yeepay.g3.facade.ymf.dto.common.ResponseMessage;
import com.yeepay.g3.facade.ymf.dto.refund.RefundRequestDTO;
import com.yeepay.g3.facade.ymf.exception.YmfException;
import com.yeepay.g3.utils.common.StringUtils;
import com.yeepay.g3.utils.common.encrypt.AES;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.ymf.boss.controller.common.DownloadController;
import com.yeepay.g3.ymf.boss.utils.FileManageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author jiwei.lv
 * @create 2016-08-16/8/17
 */
@Controller
@RequestMapping("/order")
public class OrderController extends DownloadController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private TradeBiz tradeBiz;
	@Autowired
	private SupplyBiz supplyBiz;

    @RequestMapping("/query")
    public String orderQuery() {
        return "order/orderQuery";
    }
    @RequestMapping("/refundQuery")
    public String refundOrderQuery() {
        return "order/refundOrderQuery";
    }
    
    @RequestMapping("/orderConfirm")
    public @ResponseBody ResponseMessage orderConfirm(@RequestParam(value = "id") Long id) {
        try {
			return supplyBiz.supplyOrderById(id) ;
		} catch (Exception e) {
			log.error("系统异常，请稍后重试！", e);
			return ResponseMessage.error("系统异常，请稍后重试！");
		}
    }

    @RequestMapping("/detail")
    public String orderDetail(@RequestParam(value = "id") Long id, Model model) throws YmfException {
        Payment payment = paymentService.findById(id);
        if (null == payment) {
            model.addAttribute("msg", "支付纪录不存在");
            return "error";
        }
        Order order = orderService.findById(payment.getOrderId());
        if (null == order) {
            model.addAttribute("msg", "订单不存在");
            return "error";
        }
        Customer customer=customerService.findByCustomerNumber(order.getCustomerNumber());
        if (null == customer) {
            model.addAttribute("msg", "商户不存在");
            return "error";
        }
        if (StringUtils.isNotBlank(payment.getCardNo())) {
            String card=null;
            try {
                card = AES.decryptFromBase64(payment.getCardNo(), Constants.CARD_NO_AES_KEY);
            }catch (Exception e){
                log.error("银行卡解密出错!",e);
                card=payment.getCardNo();
            }
            if(StringUtils.isNotBlank(card)){
                String prefix=card.substring(0,6);
                String fix=card.substring(card.length()-4,card.length());
                payment.setCardNo(prefix+"****"+fix);
            }
        }
        model.addAttribute("order", order);
        model.addAttribute("payment", payment);
        model.addAttribute("customer", customer);
        if(null!=payment.getRealAmt()){
            if(null!=payment.getRefundAmt()){
                model.addAttribute("main",payment.getRealAmt().subtract(payment.getRefundAmt()));
            }else{
                model.addAttribute("main",payment.getRealAmt());
            }
        }else{
            model.addAttribute("main","--");
        }

        model.addAttribute("refund", null != payment.getRefundAmt());
        return "order/orderDetail";
    }

    @SuppressWarnings("unchecked")
    @RequestMapping("/down")
    public ResponseEntity<byte[]> downDateResult(HttpServletRequest request,HttpSession session) throws IOException{
        return downdload(request, session);
    }

    @Deprecated
    @RequestMapping("/downfile")
    public void downFile(HttpServletResponse response, @RequestParam(value="filename") String filename){
        if (null != filename && !"".equals(filename)) {
            FileManageUtils.exportFile(response,filename);
        }
    }

    @RequestMapping("refund")
    @ResponseBody
    public ResponseMessage refund(@RequestParam Long id) {
        try {
            Payment payment = paymentService.findById(id);
            if (null == payment) {
                return ResponseMessage.error("根据ID查询不到支付记录, id:" + id);
            }
            RefundRequestDTO request = NCApiSupport.buildRefundRequest(payment);
            return tradeBiz.refundOrder(request);
        } catch (Exception e) {
            log.error("发起退款失败", e);
            return ResponseMessage.error("发起退款失败", e);
        }
    }
}
