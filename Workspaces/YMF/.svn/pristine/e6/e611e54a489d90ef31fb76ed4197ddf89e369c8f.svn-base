package com.yeepay.g3.core.ymf.service.impl.order;

import com.yeepay.g3.core.ymf.dao.order.OrderDao;
import com.yeepay.g3.core.ymf.dao.order.OrderMapper;
import com.yeepay.g3.core.ymf.entity.customer.Customer;
import com.yeepay.g3.core.ymf.entity.dictionary.Dictionary;
import com.yeepay.g3.core.ymf.entity.order.Order;
import com.yeepay.g3.core.ymf.entity.order.OrderParam;
import com.yeepay.g3.core.ymf.entity.order.Payment;
import com.yeepay.g3.core.ymf.service.CustomerService;
import com.yeepay.g3.core.ymf.service.DictionaryService;
import com.yeepay.g3.core.ymf.service.PaymentService;
import com.yeepay.g3.core.ymf.service.order.OrderService;
import com.yeepay.g3.core.ymf.utils.common.ElectronicVoucherUtil;
import com.yeepay.g3.core.ymf.utils.dateutils.DateUtil;
import com.yeepay.g3.core.ymf.utils.sequence.SequenceUtils;
import com.yeepay.g3.facade.ymf.dto.common.CountResponse;
import com.yeepay.g3.facade.ymf.dto.common.ElectImageDto;
import com.yeepay.g3.facade.ymf.dto.order.*;
import com.yeepay.g3.facade.ymf.enumtype.trade.BusinessType;
import com.yeepay.g3.facade.ymf.enumtype.trade.OrderStatus;
import com.yeepay.g3.utils.common.StringUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Title:
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/16.
 */
@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
    @Autowired
    private PaymentService paymentService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private DictionaryService dictionaryService;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderMapper orderMapper;

    SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    DecimalFormat df = new DecimalFormat("#,##0.00");

    @Override
    public Order findById(Long id) {
        return orderMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<OrderDTO> queryOrders(OrderArgs params) {
        return orderDao.queryOrderDTOByArgs(params);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int save(Order order) {
    	int insert = orderMapper.insert(order) ;
    	createExternalId(order) ;
        return insert;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int update(Order order) {
        return orderMapper.updateByPrimaryKey(order);
    }

    @Override
    public List<Order> findSupplyOrder(Date from, Date to) {
        OrderParam param = new OrderParam();
        param.createCriteria().andOrderStatusEqualTo(OrderStatus.PROCESS)
            .andCreateTimeBetween(from, to);
        return orderMapper.selectByFilter(param);
    }

    @Override
    public List<Order> findExpireOrder(Date from, Date to) {
        OrderParam param = new OrderParam();
        param.createCriteria().andOrderStatusEqualTo(OrderStatus.INIT)
                .andCreateTimeBetween(from, to);
        return orderMapper.selectByFilter(param);
    }

    /**
     * 更新ExtendId
     */
    private void createExternalId(Order order) {
		//根据id更新ExtendId
		String extendId = SequenceUtils.createOrderSequence(order.getId());
		//payConfirm改为存下单方。
		//order.setPayConfirm(extendId.substring(extendId.length() - 6));//最后6位，订单确认码
		order.setExternalId(extendId);
		if (StringUtils.isEmpty(order.getCustomerOrderId())) {
			//标准版没有商户定单号，保存extendId
			order.setCustomerOrderId(extendId);
		}
		orderDao.updateExtendIdById(order) ;
    }


    @Override
    public Order findOrderPayOrder(String customerNumber, String customerOrderId) {
        OrderParam param = new OrderParam();
//      查询订单与公众号支付两种订单防止出现 重复下单的问题
        ArrayList<BusinessType> list  = new ArrayList<BusinessType>();
        list.add(BusinessType.ORDER_PAY);
        list.add(BusinessType.REQUIRE_PAY);
        param.createCriteria().andCustomerNumberEqualTo(customerNumber).andCustomerOrderIdEqualTo(customerOrderId)
                .andBusinessTypeIn(list);
        List<Order> orderList = orderMapper.selectByFilter(param);
        if (null != orderList && orderList.size() > 0) {
            return orderList.get(0);
        }
        return null;
    }
    @Override
    public OrderDetailDTO findByQueryArgs(OrderQueryArgs args) {
        return orderDao.findByQueryArgs(args);
    }

    @Override
    public List<OrderDTO> queryOrderDTOByArgs(OrderArgs args) {
        return orderDao.queryOrderDTOByArgs(args);
    }

    @Override
    public CountResponse countOrderByArgs(OrderArgs args) {
        return orderDao.countOrderByArgs(args);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void updateOrderStatusById(Order order) {
    	if (order == null || order.getOrderStatus() == null) {
    		throw new RuntimeException("Update Order is null") ;
    	}
    	orderDao.updateOrderStatusById(order); ;
    }

    @Override
    public String createProofPic(String orderId,String image) {


        String fileName=null;


        long id =Long.parseLong(orderId);
        try {
//            "/Users/yp-tc-m-2803/work/bgSign.jpg";
//
            InputStream  imageIO=this.getClass().getResourceAsStream("/images/bgSign.jpg");
            log.info(image);

            String path=System.getProperty("java.io.tmpdir");
            if(StringUtils.isEmpty(path)){
                log.info("获取临时目录为空!");
                return null;
            }
            Payment payment = paymentService.findById(id);
            if (null == payment) {
                return null;
            }
            Order order = this.findById(payment.getOrderId());
            if (null == order) {
                return null;
            }
            Customer customer=customerService.findByCustomerNumber(payment.getCustomerNumber());
            ElectImageDto dto=new ElectImageDto();

            dto.setTradeTime(format.format(payment.getPayTime()));
            dto.setStatus("成功");
            dto.setProductName(customer.getCustomerName()+"保险缴费");
            dto.setTrxAmount(df.format(payment.getTrxAmt()));
            dto.setCustomerNumber(payment.getCustomerNumber());
            dto.setCustomerOrderId(payment.getCustomerOrderId());
            dto.setExternalId(payment.getOutOrderId());
            dto.setCustomerName(customer.getCustomerName());
            dto.setTrxDate(format.format(payment.getPayTime()));
            dto.setUserName(order.getReceiverName());
            ElectronicVoucherUtil electronicVoucherUtil= ElectronicVoucherUtil.getInstance();
            electronicVoucherUtil.voucherGeneration(dto, imageIO, path);
            fileName=payment.getCustomerOrderId();
        }catch (Exception e){
            log.error("代码错误!",e);
        }
        return fileName+".jpg";
    }

    @Override
    public List<OrderProofDTO> findByCustomer(String customerNumber) {
        int predate=1;
        if(StringUtils.isEmpty(customerNumber)){
            log.info("商户编号为空!");
            return null;
        }
        Dictionary dict=dictionaryService.getDictByTypeAndCode("Proof","PROOFDATE");
        String proofDate =dict.getValue();
        if(StringUtils.isNotEmpty(proofDate)){
            predate= Integer.parseInt(proofDate);
        }
        String date=DateUtil.getPreDate(predate,"yyyy-MM-dd");
        if(StringUtils.isEmpty(date)){
            log.info("日期转化失败!");
            return  null;
        }
        List<String> list=new ArrayList<String>();
        Dictionary dictBusiness=dictionaryService.getDictByTypeAndCode("ORDERPARAM","BUSINESSTYPE");
        String[] businessType=dictBusiness.getValue().split(",");
        if(null!= businessType && businessType.length>0){
            for(String bustype:businessType){
                list.add(bustype);
            }
        }
        OrderProofQueryArgs orderProofQueryArgs=new OrderProofQueryArgs();
        orderProofQueryArgs.setCustomerNumber(customerNumber);
        orderProofQueryArgs.setStartdate(date+" 00:00:00.000");
        orderProofQueryArgs.setEnddate(date+" 23:59:59.999");
        orderProofQueryArgs.setBusinessType(list);
        List<OrderProofDTO> orderProoflist = orderDao.findProofByQueryArgs(orderProofQueryArgs);
        if(null != orderProoflist && orderProoflist.size()>0){
            return orderProoflist;
        }else{
            return  null;
        }
    }

    @Override
    public String createProofPicTime(List<OrderProofDTO> orderProofDTO) throws Exception{
        if(null != orderProofDTO){
            try {
//                "/Users/yp-tc-m-2803/work/test/"
                String basePath = null;
                int predate=1;
                String customerNumber = orderProofDTO.get(0).getCustomerNumber();
                List<Dictionary> dict=dictionaryService.getDictionariesByType("ProofPath");
                if(null != dict && dict.size()==1){
                    Dictionary  dictionary=dict.get(0);
                    if(null != dictionary){
                        basePath=dictionary.getCode();
                    }
                }
                Dictionary diction=dictionaryService.getDictByTypeAndCode("Proof","PROOFDATE");
                String proofDate =diction.getValue();
                if(StringUtils.isNotEmpty(proofDate)){
                    predate= Integer.parseInt(proofDate);
                }
                String datepath = DateUtil.getPreDate(predate, "yyyyMMdd");
                log.info(datepath);
                String path = basePath + customerNumber + "/" + datepath;
                File file = new File(path);
                if (!file.exists()) {
                    file.mkdirs();
                }
//                String image = "/Users/yp-tc-m-2803/work/test/" + "image/bgSign.jpg";
//                String image = this.getClass().getResource("/").getPath()+"images/bgSign.jpg";

                for (int i=0;i<orderProofDTO.size();i++){
                    InputStream imageIO=this.getClass().getResourceAsStream("/images/bgSign.jpg");
                    ElectImageDto dto = new ElectImageDto();
                    dto.setTradeTime(format.format(orderProofDTO.get(i).getPayTime()));
                    dto.setStatus("成功");
                    dto.setProductName(orderProofDTO.get(i).getCustomerName() + "保险缴费");
                    dto.setTrxAmount(df.format(orderProofDTO.get(i).getTrxAmt()));
                    dto.setCustomerNumber(orderProofDTO.get(i).getCustomerNumber());
                    dto.setCustomerOrderId(orderProofDTO.get(i).getCustomerOrderId());
                    dto.setExternalId(orderProofDTO.get(i).getOutOrderId());
                    dto.setCustomerName(orderProofDTO.get(i).getCustomerName());
                    dto.setTrxDate(format.format(orderProofDTO.get(i).getPayTime()));
                    dto.setUserName(orderProofDTO.get(i).getReceiverName());
                    ElectronicVoucherUtil electronicVoucherUtil = ElectronicVoucherUtil.getInstance();
                    electronicVoucherUtil.voucherGeneration(dto, imageIO, path);
                }

            }catch(Exception e){
                log.error("生成电子凭证失败",e);
                throw e;
            }
        }
        return "OK";
    }

    @Override
    public Order findByCustomerAndExternalId(String customerNumber, String externalId) {
        OrderParam param = new OrderParam();
        OrderParam.Criteria criteria = param.createCriteria().andExternalIdEqualTo(externalId);
        if (StringUtils.isNotBlank(customerNumber)) {
            criteria.andCustomerNumberEqualTo(customerNumber) ;
        }
        List<Order> orderList = orderMapper.selectByFilter(param);
        if (null != orderList && orderList.size() > 0) {
            return orderList.get(0);
        }
        return null;
    }

    /**
     * 批量更新queryCount。自动+1
     *
     * @param ids orderId
     */
    @Override
    public void updateQueryCountByIds(Set<Long> ids) {
        if (ids == null || ids.size() <= 0) {
            throw new RuntimeException("Params ids is null");
        }
        orderDao.updateQueryCountByIds(ids);
    }

}
