package com.yeepay.g3.core.ymf.biz.data.impl;

import com.yeepay.g3.core.ymf.biz.data.DataHandlerBiz;
import com.yeepay.g3.core.ymf.constants.CfgConstant;
import com.yeepay.g3.core.ymf.entity.customer.Customer;
import com.yeepay.g3.core.ymf.entity.qrcode.QRCode;
import com.yeepay.g3.core.ymf.entity.shop.Shop;
import com.yeepay.g3.core.ymf.service.CustomerService;
import com.yeepay.g3.core.ymf.service.qrcode.QrCodeService;
import com.yeepay.g3.core.ymf.service.shop.ShopService;
import com.yeepay.g3.core.ymf.utils.qrCodeUtil.QRCodeUtil;
import com.yeepay.g3.facade.ymf.enumtype.common.ShopStatus;
import com.yeepay.g3.utils.common.StringUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: xiaobin.liu
 * @Date: 17/8/30
 * @Time: 上午10:39
 */
@Service
public class DataHandlerBizImpl implements DataHandlerBiz {
    private static final Logger logger = LoggerFactory.getLogger(DataHandlerBizImpl.class);
    @Autowired
    private QrCodeService qrCodeService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ShopService shopService;
    /**
     * 处理历史数据中二维码QrContent为空数据
     */
    @Override
    public int handleQrCodeContent() {
        logger.info("------开始 [handleQrCodeContent]");
        List<QRCode> qrCodes = qrCodeService.queryQrContentEmpty();
        if (qrCodes == null || qrCodes.size() <= 0) {
            return 0;
        }
        int size = qrCodes.size();
        logger.info("处理数据数目：{}",size);

        for (QRCode qrCode : qrCodes) {
            String qrCotent = QRCodeUtil.createQrCotent(qrCode.getQrId());
            logger.info("qrCotent :{}",qrCotent);
            qrCode.setQrContent(qrCotent);
            qrCodeService.updateQrCode(qrCode);
        }
        logger.info("------结束 [handleQrCodeContent]");
        return size;
    }

    /**
     * 生成默认网点
     * @return
     */
    @Override
    public int handleDefaultShop() {
        logger.info("------开始 [handleDefaultShop]");
        List<Customer> noShopCustomers = customerService.findNOShopCustomers();
        if (noShopCustomers == null || noShopCustomers.size() <= 0) {
            return 0;
        }
        int size = noShopCustomers.size();
        logger.info("处理数据数目：{}",size);

        for (Customer customer : noShopCustomers) {
            String customerNumber = customer.getCustomerNumber();
            customerService.addDefaultShop(customerNumber);
        }

        logger.info("------结束 [handleDefaultShop]");
        return size;
    }

    /**
     * 绑定默认二维码到默认网点
     * @return
     */
    @Override
    public int bindQrCodeToShop() {
        logger.info("------开始 [bindQrCodeToShop]");
        List<QRCode> qrCodes = qrCodeService.queryShopNumberEmpty();
        if (qrCodes == null || qrCodes.size() <= 0) {
            return 0;
        }
        int size = qrCodes.size();
        logger.info("处理数据数目：{}",size);

        for (QRCode qrCode : qrCodes) {
            String customerNumber = qrCode.getCustomerNumber();
            List<Shop> shops = shopService.queryShopByCustomerNumber(customerNumber, ShopStatus.ACTIVE);
            if (shops == null || shops.size() <= 0) {
                continue;
            }
            Shop shop = shops.get(0);
            qrCode.setShopNumber(shop.getShopNumber());
            qrCodeService.updateQrCode(qrCode);
            logger.info("更新二维码:{} 网点为:{}",qrCode.getQrId(),shop.getShopNumber());
        }

        logger.info("------结束 [bindQrCodeToShop]");
        return size;
    }

    /**
     * 批量更新二维码地址
     */
    @Override
    public int updateQrFtpUrl() {
        logger.info("------开始 [updateQrFtpUrl]");
        String oldPrefix = CfgConstant.getYMF_FTP_SERVER_PREFIX();
        List<QRCode> qrCodes = qrCodeService.queryOldFtpUrl(oldPrefix);
        int size = qrCodes.size();
        logger.info("处理数据数目：{}",size);
        String prefix = CfgConstant.getYMF_SERVER_PREFIX();

        for (QRCode qrCode : qrCodes) {
            //http://attachment.yeepay.com/ucm/201609/bcc1833cb4a94614bc756fc5a6fb7b3c.jpg
            String ftpUrl = qrCode.getFtpUrl();
            if (StringUtils.isNotBlank(ftpUrl) && ftpUrl.contains(oldPrefix)) {
                String substring = ftpUrl.substring(oldPrefix.length());
                String newftpUrl = prefix + substring;
                qrCode.setFtpUrl(newftpUrl);
                qrCodeService.updateQrCode(qrCode);
            }
        }
        logger.info("------结束 [updateQrFtpUrl]");
        return size;
    }

    /**
     * 批量更新商户logo地址
     */
    @Override
    public int updateCustomerLogoFtpUrl() {
        logger.info("------开始 [updateCustomerLogoFtpUrl]");
        String oldPrefix = CfgConstant.getYMF_FTP_SERVER_PREFIX();
        List<Customer> customers = customerService.findOldFtpPrefix(oldPrefix);
        int size = customers.size();
        logger.info("处理数据数目：{}",size);

        String prefix = CfgConstant.getYMF_SERVER_PREFIX();

        for (Customer customer : customers) {
            String customerLogo = customer.getCustomerLogo();
            if (StringUtils.isNotBlank(customerLogo) && customerLogo.contains(oldPrefix)) {
                String substring = customerLogo.substring(oldPrefix.length());
                String newftpUrl = prefix + substring;
                customer.setCustomerLogo(newftpUrl);
                customerService.updateCustomer(customer);
            }
        }
        logger.info("------结束 [updateCustomerLogoFtpUrl]");
        return size;
    }

    public static void main(String args[]) {
        String url = "http://attachment.yeepay.com/ucm/201609/bcc1833cb4a94614bc756fc5a6fb7b3c.jpg";
        String oldPrefix = "http://attachment.yeepay.com/ucm";
        if (url.contains("http://attachment.yeepay.com/ucm")) {
            String substring = url.substring(oldPrefix.length());
            System.out.println(substring);
        }
    }


}
