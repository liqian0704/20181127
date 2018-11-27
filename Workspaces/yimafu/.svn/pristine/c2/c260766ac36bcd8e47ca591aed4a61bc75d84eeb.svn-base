package com.yeepay.g3.core.ymf.service.impl;

import com.yeepay.g3.core.ymf.dao.qrcode.QRCodeMapper;
import com.yeepay.g3.core.ymf.entity.customer.Customer;
import com.yeepay.g3.core.ymf.entity.qrcode.QRCode;
import com.yeepay.g3.core.ymf.entity.qrcode.QRCodeParam;
import com.yeepay.g3.core.ymf.service.CustomerService;
import com.yeepay.g3.core.ymf.service.QrCodeService;
import com.yeepay.g3.core.ymf.service.impl.sequence.SequenceGeneratorImpl;
import com.yeepay.g3.core.ymf.support.OperateEntity;
import com.yeepay.g3.core.ymf.utils.qrCodeUtil.QRCodeUtil;
import com.yeepay.g3.core.ymf.utils.sequence.SequenceUtils;
import com.yeepay.g3.facade.ymf.enumtype.MaterialStatus;
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
    @Autowired
    private QRCodeMapper  qRCodeMapper;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private SequenceGeneratorImpl  sequenceGeneratorImpl;
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
        String customerNumber = qrCode.getCustomerNumber();
        Customer customer = customerService.findByCustomerNumber(customerNumber);
        String appType = customer.getAppType();
        if("NORMAL".equals(appType)){//生成二维码的类型
            appType="standard";//标准版
        }else{
            appType="orderPay";//订单版
        }
        String customerLogo = customer.getCustomerLogo();
        String qrcodeIdSequence = sequenceGeneratorImpl.generateSequence();//生成12位数
        String qrcodeId = SequenceUtils.createSequence(Long.parseLong(qrcodeIdSequence),new int[] { 3, 9, 3, 5, 6, 0, 7, 8, 2, 3, 4, 0 }, new int[] { 1, 10 }, new int[] { 2, 9 },
                new int[] { 3, 11 }, new int[] { 5, 8 });
        qrCode.setQrId(Long.toString(Long.parseLong(qrcodeId),36).toUpperCase());
        qrCode = createQrCode(qrCode,appType,customerLogo);
        return qRCodeMapper.insert(qrCode);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int saveQrCode(OperateEntity<QRCode> en) {
        String customerNumber = en.getEntity().getCustomerNumber();
        Customer customer = customerService.findByCustomerNumber(customerNumber);
        String appType = customer.getAppType();
        if("NORMAL".equals(appType)){//生成二维码的类型
            appType="standard";//标准版
        }else{
            appType="orderPay";//订单版
        }
        String customerLogo = customer.getCustomerLogo();
        String qrcodeIdSequence = sequenceGeneratorImpl.generateSequence();//生成12位数
         String qrcodeId = SequenceUtils.createSequence(Long.parseLong(qrcodeIdSequence),new int[] { 3, 9, 3, 5, 6, 0, 7, 8, 2, 3, 4, 0 }, new int[] { 1, 10 }, new int[] { 2, 9 },
                new int[] { 3, 11 }, new int[] { 5, 8 });
        en.getEntity().setQrId(Long.toString(Long.parseLong(qrcodeId),36).toUpperCase());
        QRCode qrCode  = createQrCode(en.getEntity(),appType,customerLogo);
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
        if(qrCodeList!=null){
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
        if(qrCodeList!=null){
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
        for(QRCode qrCode : orList ){
            if(null != qrCode.getCustomerNumber()){
                Customer customer = customerService.findByCustomerNumber(qrCode.getCustomerNumber());
                appType = customer.getAppType();
                if("NORMAL".equals(appType)){//生成二维码的类型
                    appType="standard";//标准版
                }else{
                    appType="orderPay";//订单版
                }
                customerLogo = customer.getCustomerLogo();
            }else{
                appType="standard";
            }
            String qrcodeIdSequence = sequenceGeneratorImpl.generateSequence();//生成12位数
            String qrcodeId = SequenceUtils.createSequence(Long.parseLong(qrcodeIdSequence),new int[] { 3, 9, 3, 5, 6, 0, 7, 8, 2, 3, 4, 0 }, new int[] { 1, 10 }, new int[] { 2, 9 },
                    new int[] { 3, 11 }, new int[] { 5, 8 });
            qrCode.setQrId(Long.toString(Long.parseLong(qrcodeId),36).toUpperCase());
            qrCode = QRCodeUtil.createQrCode(qrCode,appType,customerLogo);
            copyOrList.add(qrCode);
        }
        return qRCodeMapper.batchCreateQrCode(copyOrList);
    }
}
