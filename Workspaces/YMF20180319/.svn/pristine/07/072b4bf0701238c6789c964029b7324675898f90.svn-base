package com.yeepay.g3.ymf.boss.controller.mpfile;

import com.yeepay.g3.facade.ymf.dto.common.ResponseMessage;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.ymf.boss.controller.ValidateController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @Description: 公众号文件管理
 * @Author: xiaobin.liu
 * @Date: 16/11/8
 * @Time: 上午10:34
 */
@Controller
@RequestMapping("mpfile")
public class MpfileController extends ValidateController {
    private static final Logger log = LoggerFactory.getLogger(MpfileController.class);
    private static final String fileBasePath = "/apps/mp";
//    private static final String fileBasePath = "/Users/yp-tc-m-2889/tmp";

    /**
     * 查询文件
     *
     * @return
     */
    @RequestMapping("query")
    public String queryMpfile() {
        return "mpfile/mpfileQuery";
    }


    /**
     * 文件上传
     *
     * @return
     */
    @RequestMapping("toUploadFile")
    public String toUploadFile() {
        return "mpfile/uploadFile";
    }

    /**
     * 件上传
     *
     * @return
     */
    @RequestMapping("uploadFile")
    public
    @ResponseBody
    ResponseMessage uploadFile(@RequestParam(value = "fileUpload", required = false) MultipartFile file) {
        try {
            //保存文件
            String dir = fileBasePath + "/";
            String fileName = file.getOriginalFilename();
            log.info("上传原文件名称" + fileName);
            //        String fileName = new Date().getTime()+".jpg";
            File targetFile = new File(dir, fileName);
            log.info("保存文件到:" + targetFile);
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }
            file.transferTo(targetFile);
        } catch (Exception e) {
            log.error("文件上传失败:", e);
            return ResponseMessage.error("文件上传失败") ;
        }
//        out = new FileOutputStream(dir + "/" + appFileName) ;
//        in = new FileInputStream(app) ;
//
//        byte[] buffer = new byte[1024] ;
//        int len = 0 ;
//        while ((len = in.read(buffer)) != -1) {
//            out.write(buffer,0,len);
//        }
//        out.flush();
        return ResponseMessage.ok();
    }


}
