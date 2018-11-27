package com.yeepay.g3.core.ymf.timer;
/**
 * Created by jiwei.lv on 16/10/8.
 */

import com.yeepay.g3.core.ymf.biz.FileManage.FileSupplyBiz;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author jiwei.lv
 * @create 2016-10-16/10/8
 */
@Component
public class FileManageTimer {
    @Autowired
    private FileSupplyBiz supplyBiz;

    private static Logger logger = LoggerFactory.getLogger(FileManageTimer.class);
    @Scheduled(cron="0 10 1 * * ?")
    public void deleteFile() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date= format.format(new Date());
        logger.info(date+"定时开始");
        supplyBiz.deleteDownedFile();
        logger.info(date+"定时结束");
    }
}
