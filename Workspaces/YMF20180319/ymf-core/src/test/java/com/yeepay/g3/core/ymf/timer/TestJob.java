package com.yeepay.g3.core.ymf.timer;
/**
 * Created by jiwei.lv on 16/10/8.
 */

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author jiwei.lv
 * @create 2016-10-16/10/8
 */
@Component
public class TestJob {
    @Scheduled(cron = "0/1 * * * * ?")
    public void job1() {
        System.out.println("任务进行中。。。");
    }
}
