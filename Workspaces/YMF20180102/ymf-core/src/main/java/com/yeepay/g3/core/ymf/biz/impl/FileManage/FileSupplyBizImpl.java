package com.yeepay.g3.core.ymf.biz.impl.FileManage;

import com.yeepay.g3.core.ymf.biz.FileManage.FileSupplyBiz;
import com.yeepay.g3.core.ymf.utils.common.FileManageUtil;
import com.yeepay.g3.core.ymf.utils.dateutils.DateUtil;
import com.yeepay.g3.utils.common.StringUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author jiwei.lv
 * @create 2016-09-16/9/22
 */
@Service
public class FileSupplyBizImpl implements FileSupplyBiz {
    private static final Logger log = LoggerFactory.getLogger(FileSupplyBizImpl.class);

    @Override
    public void deleteDownedFile() {
        String root = System.getProperty("java.io.tmpdir");
//                String root = "/Users/yp-tc-m-2803/work/mq/apache-tomcat-7.0.62/temp";
        if(StringUtils.isBlank(root)){
            log.info("获取临时目录失败, 定时删除失败");
        }else{
            int count=FileManageUtil.deletePoiFile(root);
            String datePath = "ymf" + DateUtil.getPreDate(1, "yyyyMMdd");
            String path = FileManageUtil.mergePath(root, datePath);
            if (StringUtils.isBlank(path)) {
                log.info("路径有误, 定时删除失败");
            } else {
                log.info("定时删除文件开始,删除文件夹:" + path);
                long start = System.currentTimeMillis();
                int fcount= FileManageUtil.deleteFiles(path);
                int total = fcount+count;
                log.info("定时删除文件结束, 删除文件总数:" + total +
                        ", 耗时:" + (System.currentTimeMillis() - start) + "毫秒");
            }
        }
    }
}
