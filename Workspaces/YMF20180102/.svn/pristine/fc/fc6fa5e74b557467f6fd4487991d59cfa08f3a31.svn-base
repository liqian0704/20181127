/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.ymf.pay.controller.fileUpLoad;
/**
 * 类名称: ManageFtpFileController <br>
 * 类描述: <br>
 *
 * @author: xxxx.xxx
 * @since: 17/9/8 上午11:21
 * @version: 1.0.0
 */

import com.yeepay.g3.core.ymf.constants.CfgConstant;
import com.yeepay.g3.core.ymf.constants.Constants;
import com.yeepay.g3.utils.common.json.JSONUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.utils.ftp.FtpFileStoreUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

/**
 * Created by dongxulu on 17/9/8.
 */
@Controller
@RequestMapping("/ftp")
public class ManageFtpFileController {
    private static final Logger logger = LoggerFactory.getLogger(ManageFtpFileController.class) ;
    @RequestMapping("hello")
    @ResponseBody
    public String sayHello(){

        return "hello";
    }

    @RequestMapping("getFtpFile/**")
    public void getFtpFile(HttpServletResponse response, HttpServletRequest request){
        InputStream inputStream = null;
        OutputStream os = null;
        String uri = request.getRequestURI();
        logger.info("uri"+uri);
        try {
            response.setContentType("image/jpeg");
            os = response.getOutputStream();  //创建输出流
            Map<String,String> ftpMap = CfgConstant.getYMF_FTP_SERVER_INFO();
            if(ftpMap.isEmpty()){
                logger.info("getFtpServer OR");
                return;
            }
            logger.info("ftpMap-->"+ JSONUtils.toJsonString(ftpMap));
            String ftpPath = uri.substring(uri.indexOf("getFtpFile")+"getFtpFile".length());
            logger.info("request fileInfo:"+ftpPath);
            inputStream = FtpFileStoreUtils.getStream(ftpPath,ftpMap.get(Constants.FTP_IP),ftpMap.get(Constants.FTP_USERNAME),
                    ftpMap.get(Constants.FTP_PWD));
            if(null==inputStream){
                logger.error("getStream ERROR !");
                return;
            }
            IOUtils.copy(inputStream,os);

        } catch (IOException e) {
            logger.error("getStream ERROR !");
            return;
        }finally {
            if(null!=inputStream){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    logger.error("inputStream.close ERROR !",e);
                    return;
                }
            }
            if(null!= os){
                try {
                    os.close();
                } catch (IOException e) {
                    logger.error("outputStream.close ERROR !",e);
                    return;
                }
            }
        }

    }


}