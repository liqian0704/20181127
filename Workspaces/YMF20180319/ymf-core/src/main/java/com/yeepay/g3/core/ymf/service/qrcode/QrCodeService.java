package com.yeepay.g3.core.ymf.service.qrcode;

import com.yeepay.g3.core.ymf.entity.qrcode.QRCode;
import com.yeepay.g3.core.ymf.support.OperateEntity;
import com.yeepay.g3.facade.ymf.exception.YmfTrxException;

import java.util.List;

/**
 * Created by yp-tc-m-2762 on 16/8/13.
 */
public interface QrCodeService {
    /**
     * 根据id获得二维码
     *  add by fei.lu
     * @param id
     * @return
     */
    public QRCode getQrCodeById(Long id);

    /**
     * 修改二维码
     *  add by fei.lu
     * @return
     */
    public int updateQrCode(QRCode qrCode);

    /**
     * 保存二维码
     *  add by fei.lu
     * @param qrCode
     * @return
     */
    public int saveQrCode(QRCode qrCode);

    /**
     * 新增二维码加日志
     * @param en
     * @return
     */
    public int saveQrCode(OperateEntity<QRCode> en);

    /**
     * 修改二维码加日志
     * @param en
     * @return
     */
    public int updateQrCode(OperateEntity<QRCode> en);

    /**
     * 根据商编查询二维码
     * @param customerNumber
     * @return
     */
    public List<QRCode> getQrCodeByCustomerNumber(String customerNumber);
    public List<QRCode> getActiveQrCodeByCustomerNumber(String customerNumber);

    /**
     * 根据qrId查询二维码
     * @param qrId
     * @return
     */
    QRCode selectByQrId(String qrId) ;

    /**
     * 根据商编查询状态为ACTIVE可用的二维码
     * @param customerNumber
     * @return
     */
    QRCode selectByCustomerAndStatus(String customerNumber);

    /**
     * 批量创建二维码
     * @param orList
     * @return
     */
    int batchCreateQrCode(List<QRCode> orList);

    /**
     * 批量保存二维码。
     * @param orList
     * @return
     */
    int batchSaveQrCode(List<QRCode> orList);

    /**
     *
     *
     * @return
     */
    List<QRCode> getByAgentNumberAndRelationID(String agentNumber,String relationID);

    /**
     * 生成台签二维码
     * @param qrCode
     * @return
     */
    int saveSignedPaperQrcode(QRCode qrCode) throws YmfTrxException;
    
    int changeStatusByQrId(String status, String qrId);

    /**
     * 根据二维码id查询 业务编码
     * @param qrID
     * @return
     */
    String getBusinessCodeByQrID(String qrID);

    /**
     * 查询所有qrContent为空且BUSINESS_TYPE不为空的数据
     */
    List<QRCode> queryQrContentEmpty();

    /**
     * 查询所有未绑定默认网点的二维码
     */
    List<QRCode> queryShopNumberEmpty();

    /**
     * 查询所有旧的二维码的地址,只查询1000条
     */
    List<QRCode> queryOldFtpUrl(String prefix);
}
