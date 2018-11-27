package com.yeepay.g3.core.ymf.utils.common;

import com.yeepay.g3.core.ymf.constants.Constants;
import com.yeepay.g3.core.ymf.utils.ConfigureSetting;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * 公用线程池
 *
 */
public class ThreadPoolUtil {

    private static Logger logger = LoggerFactory.getLogger(ThreadPoolUtil.class);
    private static ThreadPoolExecutor staThreadPool;
    private static ThreadPoolUtil threadPoolUtil;
    private  int corePoolSize;
    private  int maximumPoolSize;
    private  long keepAliveTime;
    private  int workQueueSize;
    public LinkedBlockingQueue<Runnable> bqueue;
    private ThreadPoolUtil() {
        initPoole();
        bqueue = new LinkedBlockingQueue<Runnable>(workQueueSize);
        staThreadPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize,
                keepAliveTime, TimeUnit.MILLISECONDS, bqueue,
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

    public synchronized static ThreadPoolUtil getInstance() {
        if (threadPoolUtil == null)
            threadPoolUtil = new ThreadPoolUtil();
        return threadPoolUtil;
    }

    private void initPoole(){
        try {
            corePoolSize = Integer.valueOf(ConfigureSetting.getValue(Constants.INIT_POOL_SIZE,""));
        } catch (NumberFormatException e) {
            corePoolSize=5;
        }
        try {
            maximumPoolSize =Integer.valueOf(ConfigureSetting.getValue(Constants.MAX_POOL_SIZE,""));
        } catch (NumberFormatException e) {
            maximumPoolSize=50;
        }
        keepAliveTime = 3000;
        try {
            workQueueSize = Integer.valueOf(ConfigureSetting.getValue(Constants.QUEUE_SIZE,""));
        } catch (NumberFormatException e) {
            workQueueSize=5;
        }
        logger.info("ThreadPool Param: corePoolSize:"+corePoolSize+",maximumPoolSize:"+maximumPoolSize+
                ",keepAliveTime:"+keepAliveTime+", workQueueSize:"+workQueueSize);

    }
    /**
     * 添加处理线程
     *
     * @param runable
     */
    public void excutorTask(Runnable runable) {
        logger.info("activeCount:"+staThreadPool.getActiveCount());
        logger.info("TaskCount:"+staThreadPool.getTaskCount());
        logger.info("PoolSize:"+staThreadPool.getPoolSize());
        logger.info("bqueue.size:"+bqueue.size());
        staThreadPool.execute(runable);
    }
}

