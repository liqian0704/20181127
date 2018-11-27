package com.yeepay.g3.ymf.boss.utils;/**
 * Created by jiwei.lv on 16/8/31.
 */

import com.yeepay.g3.core.ymf.utils.ConfigureSetting;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author jiwei.lv
 * @create 2016-08-16/8/31
 */
public class FileManageUtils {
    private static Logger logger = LoggerFactory.getLogger(FileManageUtils.class);
    public static void  exportFile(HttpServletResponse response, String fileName){
//        String filePath= ConfigureSetting.getValue("YMF_DOWNLOAD_PATH",String.class);
        String filePath=System.getProperty("java.io.tmpdir");
        if(null != filePath && !"".equals(filePath)){
            filePath=filePath+File.separator;
           String filepath=filePath+fileName;
            File file=new File(filepath);
            //下载Excel
            response.setHeader("Content-Disposition", "attachment; filename="+fileName);
            response.setHeader("Content-Length",String.valueOf(file.length()));
            response.setContentType("APPLICATION/OCTET-STREAM");

            // 文件全路径
            BufferedInputStream in = null;
            BufferedOutputStream out = null;
            try {
                in = new BufferedInputStream(new FileInputStream(file));
                out = new BufferedOutputStream(response.getOutputStream());
                byte[] data = new byte[1024];
                int len = 0;
                while (-1 != (len=in.read(data, 0, data.length))) {
                    out.write(data, 0, len);
                }
            } catch (Exception e) {
                logger.error("写入文件失败!",e);
            } finally {
                    try {
                        if(in != null){
                            in.close();
                        }
                        if (out != null) {
                            out.close();
                            if (file.exists()) {
                                file.delete();
                            }
                        }
                    } catch (IOException e) {
                        logger.info("关闭失败",e);
                        throw new RuntimeException();
                    }
            }
        }

    }
    public static String getFilePath() throws IOException {
        String path=null;
        String filePath=System.getProperty("java.io.tmpdir");
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMdd");
        String datePath=dateFormat.format(new Date());
        datePath="ymf"+datePath;
        if(null==filePath || "".equals(filePath) ){
            throw new IOException("获取tomcat临时目录路径出错!");
        }else{
            if(filePath.endsWith(File.separator)){
                path=filePath+datePath+File.separator;
            }else{
                path=filePath+File.separator+datePath+File.separator;
            }

        }
        return path;
    }
}
