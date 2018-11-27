package com.yeepay.g3.core.ymf.service.order;

import java.util.Date;

import com.yeepay.g3.facade.ymf.enumtype.common.TradeStatus;
import com.yeepay.g3.facade.ymf.enumtype.trade.PaySource;
import com.yeepay.g3.facade.ymf.enumtype.trade.PaymentStatus;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yeepay.g3.core.ymf.entity.order.Payment;
import com.yeepay.g3.core.ymf.junit.ApplicationContextAwareTest;
import com.yeepay.g3.core.ymf.service.PaymentService;

/**
 * 
* @Title: PaymentServiceTest.java
* @Package com.yeepay.g3.core.ymf.service.order
* @Description: TODO
* @author dongxu.lu 
* @date 2016年8月25日 下午2:56:39
* @version
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classPath*：ymf-spring/ymf-application.xml"})
public class PaymentServiceTest  extends ApplicationContextAwareTest{
	private PaymentService paymentService;
	
    public void save() throws Exception {
    	paymentService=getBean(PaymentService.class);
    	Payment payment = new Payment();
    	payment.setOrderId(1L);
    	payment.setPaySource(PaySource.TEST);
    	payment.setPayStatus(PaymentStatus.INIT);
    	payment.setPayTime(new Date());
    	payment.setCustomerOrderId("lulutest123456");
    	payment.setCardNo("lulutest8888");
    	payment.setCreateTime(new Date());
    	try {
			System.out.println(paymentService.save(payment));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    @Test
    public void findByOrderIdAndPayStatus() throws Exception {
    	Payment payment = new Payment();
    	paymentService=getBean(PaymentService.class);
    	try {
			payment = paymentService.findByOrderIdAndPayStatus(1l, "INIT");
			System.out.println(payment);
			payment =paymentService.findById(2l);
			System.out.println("###find by ID:"+payment);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    


}