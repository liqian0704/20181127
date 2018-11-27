package com.yeepay.g3.ymf.boss.query;

import com.yeepay.g3.core.ymf.utils.qrCodeUtil.QRCodeUtil;
import com.yeepay.g3.core.ymf.utils.qrCodeUtil.ZipCompress;
import com.yeepay.g3.utils.common.StringUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.query.QueryParam;
import com.yeepay.g3.utils.query.jdbc.IJdbcQueryer;
import com.yeepay.g3.utils.query.jdbc.JdbcQueryResult;
import com.yeepay.g3.utils.query.parser.QueryRequest;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * @Description: 二维码图片生成，zip文件下载
 * @Author: xiaobin.liu
 * @Date: 17/9/14
 * @Time: 下午4:25
 */
public class QrCodeDownload {
    private static Logger logger = LoggerFactory.getLogger(YmfDownServiceImpl.class);

    /**
     * 二维码数据的生成
     */
    protected String dataOtherDown(String filePath, QueryParam queryParam,
                                   IJdbcQueryer queryer, int page, String sql, QueryRequest queryRequest,
                                   String operator) {
        String msg=null;

        //二维码导出01706050$商户编号$商户名称
        String fileName =  "二维码图片导出-"+ Calendar.getInstance().getTimeInMillis()+ ".zip";
        try {
            List<String> souceFiles = new ArrayList<String>();
            for(int i=0;i<page;i++){
                JdbcQueryResult jdbcRe = queryer.query(i, i*1000+1, 1000, sql, queryRequest.getSqlParams(), queryParam.getCounter());
                List<Map<String,String>> resultlist=jdbcRe.getData();
                int num=i*1000;
                for (int j = 0; j < resultlist.size(); j++) {
                    Map<String, String> map = resultlist.get(j);
                    String customerNumber = map.get("customernumber");
                    String customerName = map.get("customername");
                    String shopName = map.get("shop_name");
                    String qrID = map.get("qrid");
                    String customerLogo = map.get("customer_logo");
                    String qrContent = map.get("qr_content");
                    if (StringUtils.isNotBlank(qrContent)) {
                        String qrCodePath = createQrCode(customerNumber, customerName, shopName, qrID, customerLogo, qrContent);
                        souceFiles.add(qrCodePath);
                    }
                }
            }
            File file = new File(filePath);
            if (!file.exists()) {
                file.mkdirs();
            }
            new ZipCompress(filePath + fileName).compress(souceFiles);
            logger.info("将下载文件打包{}", filePath + fileName);
            //删除生成的文件
            for (String filename : souceFiles) {
                logger.info("删除" + filename);
                new File(filename).delete();
            }
            msg = fileName;
            logger.info("文件创建成功!fileName:{}",msg);
        } catch (Exception e) {
            logger.error("二维码图片生成异常", e);
            msg="error";
        }
        return msg;
    }

    /**
     * 创建二维码
     */
    private String createQrCode(String customerNumber,String customerName,String shopName,String qrID,
                                String customerLogo,String qrContent) throws Exception {
        String destPath = System.getProperty("java.io.tmpdir");
        String qrFileName = null;
        if (StringUtils.isBlank(customerNumber)) {
            qrFileName = "空码_";
        } else {
            qrFileName = customerNumber + "_" + customerName + "_";
        }
        if (org.apache.commons.lang.StringUtils.isNotBlank(shopName)) {
            qrFileName += shopName + "_" + qrID;
        } else {
            qrFileName += qrID;
        }
        return QRCodeUtil.createQrCodeImg(qrFileName, destPath, customerLogo, qrContent);
    }
}
