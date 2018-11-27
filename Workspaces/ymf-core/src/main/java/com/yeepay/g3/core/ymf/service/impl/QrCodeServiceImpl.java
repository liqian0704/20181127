package com.yeepay.g3.core.ymf.service.impl;

import com.yeepay.g3.core.ymf.dao.qrcode.QRCodeDao;
import com.yeepay.g3.core.ymf.dao.qrcode.QRCodeMapper;
import com.yeepay.g3.core.ymf.entity.customer.Customer;
import com.yeepay.g3.core.ymf.entity.qrcode.QRCode;
import com.yeepay.g3.core.ymf.entity.qrcode.QRCodeParam;
import com.yeepay.g3.core.ymf.service.CustomerService;
import com.yeepay.g3.core.ymf.service.impl.sequence.SequenceGeneratorImpl;
import com.yeepay.g3.core.ymf.service.qrcode.QrCodeService;
import com.yeepay.g3.core.ymf.support.OperateEntity;
import com.yeepay.g3.core.ymf.utils.qrCodeUtil.QRCodeUtil;
import com.yeepay.g3.core.ymf.utils.sequence.SequenceUtils;
import com.yeepay.g3.facade.ymf.enumtype.MaterialStatus;
import com.yeepay.g3.facade.ymf.enumtype.trade.BusinessType;
import com.yeepay.g3.facade.ymf.enumtype.trade.TrxCode;
import com.yeepay.g3.facade.ymf.exception.YmfTrxException;
import com.yeepay.g3.utils.common.StringUtils;
import com.yeepay.g3.utils.common.json.JSONUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.yeepay.g3.core.ymf.utils.qrCodeUtil.QRCodeUtil.createQrCode;

/**
 *
 * Created by fei.lu on 16/8/13.
 */
@Service
public class QrCodeServiceImpl implements QrCodeService{
	private static final Logger LOG = LoggerFactory.getLogger(QrCodeServiceImpl.class);
    @Autowired
    private QRCodeMapper  qRCodeMapper;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private SequenceGeneratorImpl  sequenceGeneratorImpl;
    @Autowired
    private QRCodeDao qRCodeDao;
    @Override
    public QRCode getQrCodeById(Long id) {
        return qRCodeMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int updateQrCode(QRCode qrCode) {
        qrCode.setUpdateTime(new Date());
        return qRCodeMapper.updateByPrimaryKeySelective(qrCode);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int saveQrCode(QRCode qrCode) {
        LOG.info("---Begin to save qrCode ");
        String customerNumber = qrCode.getCustomerNumber();
        Customer customer = customerService.findByCustomerNumber(customerNumber);
        String appType = customer.getAppType();
        if("NORMAL".equals(appType)){//生成二维码的类型
            qrCode.setBusinessType(BusinessType.STANDARD);
        }else{
            qrCode.setBusinessType(BusinessType.ORDER_PAY);
        }
        String customerLogo = customer.getCustomerLogo();
        long qrcodeIdSequence = sequenceGeneratorImpl.generateSingleSequence();//生成12位数
        String qrcodeId = SequenceUtils.createOrderSequence(qrcodeIdSequence);
        qrCode.setQrId(Long.toString(Long.parseLong(qrcodeId),36).toUpperCase());
        qrCode = createQrCode(qrCode,customerLogo);
        LOG.info("---Will to save qrCode :{} ", JSONUtils.toJsonString(qrCode));
        return qRCodeMapper.insert(qrCode);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int saveQrCode(OperateEntity<QRCode> en) {
        String customerNumber = en.getEntity().getCustomerNumber();
        Customer customer = customerService.findByCustomerNumber(customerNumber);
        String appType = customer.getAppType();
        if("NORMAL".equals(appType)){//生成二维码的类型
           // appType="standard";//标准版
            en.getEntity().setBusinessType(BusinessType.STANDARD);
        }else{
           // appType="orderPay";//订单版
            en.getEntity().setBusinessType(BusinessType.ORDER_PAY);
        }
        String customerLogo = customer.getCustomerLogo();
        long qrcodeIdSequence = sequenceGeneratorImpl.generateSingleSequence();//生成12位数
        String qrcodeId = SequenceUtils.createOrderSequence(qrcodeIdSequence);

        LOG.info("qrcodeId--{}",Long.toString(Long.parseLong(qrcodeId),36).toUpperCase());

        en.getEntity().setQrId(Long.toString(Long.parseLong(qrcodeId),36).toUpperCase());
        QRCode qrCode  = createQrCode(en.getEntity(),customerLogo);
        return qRCodeMapper.insert(qrCode);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int updateQrCode(OperateEntity<QRCode> en) {
        en.getEntity().setUpdateTime(new Date());
        return qRCodeMapper.updateByPrimaryKeySelective(en.getEntity());
    }

    @Override
    public List<QRCode> getQrCodeByCustomerNumber(String customerNumber) {
        QRCodeParam param = new QRCodeParam();
        param.createCriteria().andCustomerNumberEqualTo(customerNumber);
        List<QRCode> qrCodeList = qRCodeMapper.selectByFilter(param);
        if(qrCodeList!=null && qrCodeList.size() > 0){
            return  qrCodeList;
        }else {
            return null;
        }
    }

    @Override
    public List<QRCode> getActiveQrCodeByCustomerNumber(String customerNumber) {

        QRCodeParam param = new QRCodeParam();
        param.createCriteria().andCustomerNumberEqualTo(customerNumber).andStatusEqualTo(MaterialStatus.ACTIVE);
        List<QRCode> qrCodeList = qRCodeMapper.selectByFilter(param);
        if(qrCodeList!=null && qrCodeList.size() > 0){
            return  qrCodeList;
        }else {
            return null;
        }
    }

    @Override
	public QRCode selectByQrId(String qrId) {
	    QRCodeParam param = new QRCodeParam();
        param.createCriteria().andQrIdEqualTo(qrId);
        List<QRCode> qrCodeList = qRCodeMapper.selectByFilter(param);
        if(qrCodeList!=null && qrCodeList.size() > 0){
            return  qrCodeList.get(0);
        }else {
            return null;
        }
	}

    @Override
    public QRCode selectByCustomerAndStatus(String customerNumber) {
        QRCodeParam param = new QRCodeParam();
        param.createCriteria().andCustomerNumberEqualTo(customerNumber).andStatusEqualTo(MaterialStatus.ACTIVE);
        List<QRCode>  qrCodeList = qRCodeMapper.selectByFilter(param);
        if(qrCodeList!=null&&qrCodeList.size()!=0){
            return qrCodeList.get(0);
        }else {
            return null;
        }
    }
    @Override
    public int batchCreateQrCode(List<QRCode> orList){
        String customerLogo = null;
        String appType = null;
        List<QRCode> copyOrList = new ArrayList<QRCode>();
        QRCode qrcode=orList.get(0);
        String customerNumber=qrcode.getCustomerNumber();
        if(StringUtils.isNotEmpty(customerNumber)){
            Customer customer = customerService.findByCustomerNumber(customerNumber);
            customerLogo=customer.getCustomerLogo();
        }
        for(QRCode qrCode : orList ){
            long qrcodeIdSequence = sequenceGeneratorImpl.generateSequence();//生成12位数
            String qrcodeId = SequenceUtils.createOrderSequence(qrcodeIdSequence);
            qrCode.setQrId(Long.toString(Long.parseLong(qrcodeId),36).toUpperCase());
            qrCode = QRCodeUtil.createQrCode(qrCode,customerLogo);
            copyOrList.add(qrCode);
        }
        return qRCodeMapper.batchCreateQrCode(copyOrList);
    }


    @Override
    public int batchSaveQrCode(List<QRCode> orList){
        if (orList == null || orList.size() < 1) {
            throw new RuntimeException("Save orList is null");
        }
        return qRCodeMapper.batchCreateQrCode(orList);
    }



    @Override
    public List<QRCode> getByAgentNumberAndRelationID(String agentNumber, String relationID) {

        return qRCodeDao.getByAgentNumberAndRelationID(agentNumber,relationID);
    }

    @Override
    public int saveSignedPaperQrcode(QRCode qrCode)throws YmfTrxException {
        String customerLogo = null;
        String customerNumber = qrCode.getCustomerNumber();
        Customer customer = customerService.findByCustomerNumber(customerNumber);
        if(customer!=null){
             customerLogo = customer.getCustomerLogo();
        }
        long qrcodeIdSequence = sequenceGeneratorImpl.generateSequence();//生成12位数
        String qrcodeId = SequenceUtils.createOrderSequence(qrcodeIdSequence);
        qrCode.setQrId(Long.toString(Long.parseLong(qrcodeId),36).toUpperCase());
        qrCode = QRCodeUtil.createQrCode(qrCode,customerLogo);
        if(null == qrCode){
            throw new YmfTrxException(TrxCode.T1005);
        }
        return qRCodeDao.insertSignedPaperQrcode(qrCode);
    }

	@Override
	public int changeStatusByQrId(String status, String qrId) {
		// TODO Auto-generated method stub
		return qRCodeMapper.updateStatusByQrId(status, qrId);
	}

    @Override
    public String getBusinessCodeByQrID(String qrID) {

        return qRCodeDao.getBusinessCodeByQrID(qrID);
    }

    /**
     * 查询所有qrContent为空且BUSINESS_TYPE不为空的数据
     */
    @Override
    public List<QRCode> queryQrContentEmpty() {

        return qRCodeDao.queryQrContentEmpty();
    }

    /**
     * 查询所有未绑定默认网点的二维码
     */
    @Override
    public List<QRCode> queryShopNumberEmpty() {
        return qRCodeDao.queryShopNumberEmpty();
    }

    /**
     * 查询所有旧的二维码的地址,只查询1000条
     */
    @Override
    public List<QRCode> queryOldFtpUrl(String prefix) {
        if (StringUtils.isBlank(prefix)) {
            throw new RuntimeException("prefix can not be null");
        }
        prefix = prefix + "%";
        return qRCodeDao.queryOldFtpUrl(prefix);
    }

}
