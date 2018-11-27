package com.yeepay.g3.core.ymf.service;

import com.yeepay.g3.core.ymf.entity.qrcode.QRCode;
import com.yeepay.g3.core.ymf.support.OperateEntity;

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
}
