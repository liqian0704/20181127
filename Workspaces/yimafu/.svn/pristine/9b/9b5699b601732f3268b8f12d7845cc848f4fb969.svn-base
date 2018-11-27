package com.yeepay.g3.core.ymf.utils.common;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 公用线程池
 *
 */
public class ThreadPoolUtil {

    private static ExecutorService exec;

    /**
     * 获取线程池
     * @return
     */
    public static ExecutorService getThreadPool() {
        if (exec == null) {
            exec = Executors.newFixedThreadPool(10);
        }
        return exec;
    }

}
