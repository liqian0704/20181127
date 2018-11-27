package com.yeepay.g3.core.ymf.ext.impl;

import com.yeepay.g3.core.ymf.constants.CfgConstant;
import com.yeepay.g3.core.ymf.ext.DigitalSecurityExt;
import com.yeepay.g3.facade.ymf.exception.YmfTrxException;
import com.yeepay.g3.facade.yop.ca.dto.DigitalSignatureDTO;
import com.yeepay.g3.facade.yop.ca.enums.CertTypeEnum;
import com.yeepay.g3.facade.yop.ca.enums.DigestAlgEnum;
import com.yeepay.g3.facade.yop.ca.exceptions.DecryptFailedException;
import com.yeepay.g3.facade.yop.ca.exceptions.EncryptFailedException;
import com.yeepay.g3.facade.yop.ca.exceptions.SignFailedException;
import com.yeepay.g3.facade.yop.ca.exceptions.VerifySignFailedException;
import com.yeepay.g3.facade.yop.ca.facade.DigitalSecurityFacade;
import com.yeepay.g3.utils.common.json.JSONUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * @Description: YOP签名及验签接口
 * @Author: xiaobin.liu
 * @Date: 17/2/22
 * @Time: 下午3:39
 */
@Service
public class DigitalSecurityExtImpl implements DigitalSecurityExt {
    private static final Logger logger = LoggerFactory.getLogger(DigitalSecurityExtImpl.class);


    @Override
    public String sign(String plainText) throws YmfTrxException {
        try {
            DigitalSignatureDTO digitalSignatureDTO = buildBaseParams();
            digitalSignatureDTO.setPlainText(plainText);
            DigitalSecurityFacade service = RemoteServiceFactory.getService(DigitalSecurityFacade.class);
            logger.info("开始调用 [YOP签名] 接口，请求参数:{}", JSONUtils.toJsonString(digitalSignatureDTO));
            DigitalSignatureDTO signatureDTO = service.sign(digitalSignatureDTO);
            logger.info("调用成功 [YOP签名] 接口，响应参数：" + JSONUtils.toJsonString(signatureDTO));
            return signatureDTO.getSignature();
        } catch (SignFailedException e) {
            logger.error("签名异常：", e);
            throw YmfTrxException.YOP_SIGN_ERROR;
        } catch (EncryptFailedException e) {
            logger.error("加密异常:", e);
            throw YmfTrxException.YOP_SIGN_ERROR;
        }
    }

    @Override
    public String verify(String plainText, String signatre) throws YmfTrxException {
        try {
            DigitalSignatureDTO digitalSignatureDTO = buildBaseParams();
            digitalSignatureDTO.setPlainText(plainText);
            digitalSignatureDTO.setSignature(signatre);
            DigitalSecurityFacade service = RemoteServiceFactory.getService(DigitalSecurityFacade.class);
            logger.info("开始调用 [YOP验签] 接口，请求参数:{}", JSONUtils.toJsonString(digitalSignatureDTO));
            DigitalSignatureDTO signatureDTO = service.verify(digitalSignatureDTO);
            logger.info("调用成功 [YOP验签] 接口，响应参数：" + JSONUtils.toJsonString(signatureDTO));
            return signatureDTO.getSignature();
        } catch (InvalidKeySpecException e) {
            logger.error("异常：", e);
            throw YmfTrxException.YOP_SIGN_ERROR;
        } catch (NoSuchAlgorithmException e) {
            logger.error("异常:", e);
            throw YmfTrxException.YOP_SIGN_ERROR;
        } catch (VerifySignFailedException e) {
            logger.error("验签异常或者未通过:", e);
            throw YmfTrxException.YOP_SIGN_ERROR;
        } catch (DecryptFailedException e) {
            logger.error("解密失败:", e);
            throw YmfTrxException.YOP_SIGN_ERROR;
        }
    }

    @Override
    public String encryptAndSign(String plainText) throws YmfTrxException {
        //未实现
        DigitalSecurityFacade service = RemoteServiceFactory.getService(DigitalSecurityFacade.class);
        return null;
    }

    @Override
    public String decryptAndVerify(String cipherText, String signatre) throws YmfTrxException {
        //未实现
        DigitalSecurityFacade service = RemoteServiceFactory.getService(DigitalSecurityFacade.class);
        String plainText = null ;
        return plainText;
    }

    /**
     * 基本参数
     *
     * @return digitalSignatureDTO
     */
    private DigitalSignatureDTO buildBaseParams() {
        DigitalSignatureDTO digitalSignatureDTO = new DigitalSignatureDTO();
        digitalSignatureDTO.setAppKey(CfgConstant.getYOPAppKey());
        digitalSignatureDTO.setCertType(CertTypeEnum.RSA2048);
        digitalSignatureDTO.setDigestAlg(DigestAlgEnum.SHA256);
        return digitalSignatureDTO;
    }
}
