package com.yeepay.g3.core.ymf.ext;

import com.yeepay.g3.facade.ymf.exception.YmfTrxException;

/**
 * @Description: YOP签名及验签接口
 * @Author: xiaobin.liu
 * @Date: 17/2/22
 * @Time: 下午3:37
 */
public interface DigitalSecurityExt {
    /**
     * 数字签名（先用摘要算法生成摘要，再用yop平台私钥加密摘要）
     * 必填参数：
     * plainText 明文
     *
     * @return 签名
     * @throws YmfTrxException    签名或加密异常
     */
    String sign(String plainText) throws YmfTrxException;

    /**
     * 验证签名（一般不需要调用此方法，可获取yop平台公钥自行验证））
     * 必填参数：
     * plainText 明文
     * signature 签名
     *
     * @return
     * @throws YmfTrxException    验签异常或者未通过
     */
    String verify(String plainText,String signatre) throws YmfTrxException;

    /**
     * 加密签名...(包含数字信封、数字签名)，默认证书类型RSA2048
     * 必填参数：
     * plainText 明文
     * <p/>
     * 数字信封和数字签名拼接保存于cipherText中
     *
     * @throws YmfTrxException 签名失败
     */
    String encryptAndSign(String plainText) throws YmfTrxException;

    /**
     * 拆开数字信封并验证数字签名
     * 必填参数：
     * cipherText 密文
     * <p/>
     * 如解密成功且验签通过，明文保存于plainText中
     *
     * @throws YmfTrxException 验签失败
     */
    String decryptAndVerify(String cipherText,String signatre) throws YmfTrxException;
}
