package com.yeepay.g3.core.ymf.facade.impl.laike;

import com.yeepay.g3.core.ymf.biz.qrCode.QrCodeBizService;
import com.yeepay.g3.core.ymf.entity.customer.Customer;
import com.yeepay.g3.core.ymf.entity.qrcode.QRCode;
import com.yeepay.g3.core.ymf.entity.qrcode.QrRelation;
import com.yeepay.g3.core.ymf.service.CustomerService;
import com.yeepay.g3.core.ymf.service.qrcode.QrCodeService;
import com.yeepay.g3.core.ymf.service.qrcode.QrRelationService;
import com.yeepay.g3.facade.ymf.dto.qrCode.QrCodeInfoRequestDTO;
import com.yeepay.g3.facade.ymf.dto.qrCode.QrCodeInfoResponseDTO;
import com.yeepay.g3.facade.ymf.dto.qrCode.QrCodePurchasesRequestDTO;
import com.yeepay.g3.facade.ymf.dto.qrCode.QrCodePurchasesResponseDTO;
import com.yeepay.g3.facade.ymf.enumtype.Status;
import com.yeepay.g3.facade.ymf.enumtype.trade.OrderStatus;
import com.yeepay.g3.facade.ymf.enumtype.trade.TrxCode;
import com.yeepay.g3.facade.ymf.exception.YmfTrxException;
import com.yeepay.g3.facade.ymf.facade.laike.PurchaseQrCodeFacade;
import com.yeepay.g3.facade.ymf.dto.laike.QrCodeInfoDTO;
import com.yeepay.g3.utils.common.json.JSONUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dongxulu on 16/12/8.
 */
@Service("purchaseQrCodeFacade")
public class PurchaseQrCodeFacadeImpl implements PurchaseQrCodeFacade {
    private static Logger logger = LoggerFactory.getLogger(PurchaseQrCodeFacadeImpl.class);
    @Autowired
    private QrRelationService qrRelationService;
    @Autowired
    private QrCodeBizService qrCodeBizService;
    @Autowired
    private QrCodeService qrCodeService;
    @Autowired
    private CustomerService  customerService;
    @Override
    public synchronized QrCodePurchasesResponseDTO doPurchase(QrCodePurchasesRequestDTO requestDTO) {
        QrCodePurchasesResponseDTO responseDTO =  new QrCodePurchasesResponseDTO();
        if(null == requestDTO){
            logger.error("requestDTO is null!");
            responseDTO.setReturnCode(TrxCode.T1006.getCode());
            responseDTO.setTrxCode(TrxCode.T1006);
            responseDTO.setReturnMsg(TrxCode.T1006.getMsg());
            return responseDTO;
        }
        logger.info("doPurchase request param: "+requestDTO.toString());
        try {
            requestDTO.checkParam();
        } catch (YmfTrxException e) {
            responseDTO.setReturnCode(e.getCode());
            responseDTO.setTrxCode(e.getTrxCode());
            responseDTO.setReturnMsg(e.getMessage());
            return responseDTO;
        }
        boolean isExsist = checkPurchaseOrder(requestDTO);
        if(isExsist){
            responseDTO.setReturnCode(TrxCode.T1008.getCode());
            responseDTO.setTrxCode(TrxCode.T1008);
            responseDTO.setReturnMsg(TrxCode.T1008.getMsg());
            return responseDTO;
        }
        int qrCount = requestDTO.getCount();
        if(qrCount>200){
            responseDTO.setReturnCode(TrxCode.T1020.getCode());
            responseDTO.setTrxCode(TrxCode.T1020);
            responseDTO.setReturnMsg(TrxCode.T1020.getMsg());
            return responseDTO;
        }
        QrRelation relation = initQrRelation(requestDTO);
        qrCodeBizService.createQrCodeWithRelation(relation);
        List<QRCode> qrList = qrCodeService.getByAgentNumberAndRelationID(requestDTO.getAgentNumber(),requestDTO.getRequestID());
        List<String> pathList = new ArrayList<String>();
        for(QRCode code : qrList){
//            二维码id与路径通过逗号拼接返回
            pathList.add(code.getQrId()+","+code.getFtpUrl());
        }
        responseDTO.setAgentNumber(requestDTO.getAgentNumber());
        responseDTO.setReturnCode(TrxCode.T00.getCode());
        responseDTO.setTrxCode(TrxCode.T00);
        responseDTO.setFilePaths(pathList);
        logger.info(" qrcode doPurchase success!");
        return responseDTO;
    }

    @Override
    public QrCodeInfoResponseDTO getQrCodeInfo(QrCodeInfoRequestDTO requestDTO) {
        QrCodeInfoResponseDTO responseDTO = new QrCodeInfoResponseDTO();
        if(null == requestDTO){
            logger.error("requestDTO is null!");
            responseDTO.setReturnCode(TrxCode.T1006.getCode());
            responseDTO.setTrxCode(TrxCode.T1006);
            responseDTO.setReturnMsg(TrxCode.T1006.getMsg());
            return responseDTO;
        }
        logger.info("getQrCodeInfo request param: "+requestDTO.toString());
        String qrID =  requestDTO.getQrCodeID();
        QrRelation relation = qrRelationService.getByQrCodeID(qrID);
        if(null==relation){
            logger.warn("qrRelationService.getByQrCodeID return null:qrID="+qrID);
            responseDTO.setReturnCode(TrxCode.T1021.getCode());
            responseDTO.setTrxCode(TrxCode.T1021);
            responseDTO.setReturnMsg(TrxCode.T1021.getMsg());
            return responseDTO;
        }
        responseDTO.setAgentNumber(relation.getAgentNumber());
        responseDTO.setSalesName(relation.getSalesName());
        responseDTO.setSalesNo(relation.getSalesNo());
        responseDTO.setQrCodeID(qrID);
        responseDTO.setTrxCode(TrxCode.T00);
        responseDTO.setReturnCode(TrxCode.T00.getCode());
        return responseDTO;
    }

    @Override
    public QrCodeInfoResponseDTO bindCustomerInfo(QrCodeInfoRequestDTO requestDTO) {
        QrCodeInfoResponseDTO responseDTO = new QrCodeInfoResponseDTO();
        if(null == requestDTO){
            logger.error("requestDTO is null!");
            responseDTO.setReturnCode(TrxCode.T1006.getCode());
            responseDTO.setTrxCode(TrxCode.T1006);
            responseDTO.setReturnMsg(TrxCode.T1006.getMsg());
            return responseDTO;
        }
        logger.info("bindCustomerInfo request param: "+ JSONUtils.toJsonString(requestDTO));
        try {
            requestDTO.checkParam();
        } catch (YmfTrxException e) {
            responseDTO.setReturnCode(e.getCode());
            responseDTO.setTrxCode(e.getTrxCode());
            responseDTO.setReturnMsg(e.getMessage());
            return responseDTO;
        }
        String qrCodeID = requestDTO.getQrCodeID();
        String customerNumber = requestDTO.getCustomerNumber();
        Customer customer = customerService.findByCustomerNumber(customerNumber);
        if(null == customer || !Status.ACTIVE.equals(customer.getStatus())){
            responseDTO.setReturnCode(TrxCode.T1023.getCode());
            responseDTO.setTrxCode(TrxCode.T1023);
            responseDTO.setReturnMsg(TrxCode.T1023.getMsg());
            return responseDTO;
        }
        QRCode qrCode = qrCodeService.selectByQrId(qrCodeID);
        if(null == qrCode ){
            responseDTO.setReturnCode(TrxCode.T1002.getCode());
            responseDTO.setTrxCode(TrxCode.T1002);
            responseDTO.setReturnMsg(TrxCode.T1002.getMsg());
            return responseDTO;
        }else if(!StringUtils.isEmpty(qrCode.getCustomerNumber())){
            responseDTO.setReturnCode(TrxCode.T1022.getCode());
            responseDTO.setTrxCode(TrxCode.T1022);
            responseDTO.setReturnMsg(TrxCode.T1022.getMsg());
            return responseDTO;
        }
        qrCode.setCustomerNumber(customer.getCustomerNumber());
        qrCode.setUpdateTime(new Date());
        qrCodeService.updateQrCode(qrCode);
        responseDTO.setCustomerNumber(customerNumber);
        responseDTO.setQrCodePath(qrCode.getFtpUrl());
        responseDTO.setQrCodeID(qrCodeID);
        responseDTO.setTrxCode(TrxCode.T00);
        responseDTO.setReturnCode(TrxCode.T00.getCode());
        return responseDTO;
    }

    @Override
    public QrCodeInfoResponseDTO getQrCodeByCustomer(QrCodeInfoRequestDTO requestDTO) throws YmfTrxException {
        QrCodeInfoResponseDTO responseDTO = new QrCodeInfoResponseDTO();
        if(null==requestDTO){
            logger.error("getQrCodeByCustomer param is null!");
            responseDTO.setReturnCode(TrxCode.T1006.getCode());
            responseDTO.setReturnMsg(TrxCode.T1006.getMsg());
            return responseDTO;
        }
        logger.info("getQrCodeByCustomer param :"+requestDTO.toString());
        String customerNumber = requestDTO.getCustomerNumber();
        if(StringUtils.isEmpty(customerNumber)){
            responseDTO.setReturnCode(TrxCode.T1006.getCode());
            responseDTO.setReturnMsg(TrxCode.T1006.getMsg());
            return responseDTO;
        }
        responseDTO.setCustomerNumber(customerNumber);
        List<QRCode> qrCodes = qrCodeService.getActiveQrCodeByCustomerNumber(customerNumber);
        List<QrCodeInfoDTO> infos = new ArrayList<QrCodeInfoDTO>();
        if(null!=qrCodes && qrCodes.size()>0){
            logger.info(" getQrCodeByCustomerNumber resultList size :"+qrCodes.size() +" customerNumber:"+customerNumber);
            for(QRCode qrCode:qrCodes){
                QrCodeInfoDTO dto = initQrCodeInfo(qrCode);
                infos.add(dto);
            }
            //兼容老版本
            responseDTO.setQrCodePath(qrCodes.get(0).getFtpUrl());
            responseDTO.setQrCodeID(qrCodes.get(0).getQrId());
            responseDTO.setQrCodeInfos(infos);
        }else{
            //创建台签二维码
            logger.info("customerNumber:"+customerNumber);
            QRCode qrCode=signedPaperCrate(customerNumber);
            if(qrCode!=null){
                QrCodeInfoDTO dto = initQrCodeInfo(qrCode);
                infos.add(dto);
                responseDTO.setQrCodePath(qrCode.getFtpUrl());
                responseDTO.setQrCodeID(qrCode.getQrId());
                responseDTO.setQrCodeInfos(infos);
            }else{
                logger.error(" getQrCodeByCustomerNumber return null! customerNumber:"+customerNumber);
                responseDTO.setReturnCode(TrxCode.T1002.getCode());
                responseDTO.setReturnMsg(TrxCode.T1002.getMsg());
                return responseDTO;
            }
        }
        responseDTO.setReturnCode(TrxCode.T00.getCode());
        return responseDTO;
    }


    private boolean checkPurchaseOrder(QrCodePurchasesRequestDTO requestDTO){
        int flag =  requestDTO.getPurchaseflag();
//          0:代理商,1:销售,2:直销商户
        if(flag==0){
            List<QrRelation> list = qrRelationService.getByAgentNumberAndRequestId(requestDTO.getAgentNumber(),requestDTO.getRequestID());
            if(list.size()>0){
                return true;
            }
        }else if(flag==1){
            List<QrRelation> list = qrRelationService.getByAgentNumberAndRequestId(requestDTO.getSalesName(),requestDTO.getRequestID());
            if(list.size()>0){
                return true;
            }
        }

        return false;
    }
    private QrRelation initQrRelation(QrCodePurchasesRequestDTO requestDTO){
        QrRelation qrRelation = new QrRelation();
        qrRelation.setAgentNumber(requestDTO.getAgentNumber());
        qrRelation.setQrcount((short)requestDTO.getCount());
        qrRelation.setSalesName(requestDTO.getSalesName());
        qrRelation.setSalesNo(requestDTO.getSalesNo());
        qrRelation.setStatus(OrderStatus.SUCCESS);
        qrRelation.setCreateTime(new Date());
        qrRelation.setRequestId(requestDTO.getRequestID());
        return qrRelation;
    }

    protected QRCode signedPaperCrate(String customerNumber){
        try {
            QRCode qrCode=qrCodeBizService.signedPaperQrcode(customerNumber);
            return qrCode;
        }catch (Exception e){
            logger.error("生成台签牌二维码失败",e);
            return null;
        }
    }

    private QrCodeInfoDTO initQrCodeInfo(QRCode qrCode){
        QrCodeInfoDTO dto = new QrCodeInfoDTO();
        dto.setQrCodeUrl(qrCode.getQrContent());
        dto.setShopName(qrCode.getShopName());
        dto.setQrID(qrCode.getQrId());
        dto.setShopNumber(qrCode.getShopNumber());
        return dto;
    }
}
