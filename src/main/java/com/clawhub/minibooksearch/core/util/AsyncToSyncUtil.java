package com.clawhub.minibooksearch.core.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <Description> com.clawhub.minibooksearch.core.util <br>
 *
 * @author LHY<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2018/12/17<br>
 */
public class AsyncToSyncUtil{

    /**
     * The Logger.
     */
    private static Logger logger = LoggerFactory.getLogger(AsyncToSyncUtil.class);

    private static final Object lock = new Object();

    public static void callback() {
        synchronized (lock){
            lock.notifyAll();
        }
        logger.info("异步爬虫结束，线程lock释放");
    }

    public static void transfer() {
        AsyncToSyncUtil asyncToSyncUtil = new AsyncToSyncUtil();
        logger.info("异步爬虫开始，线程lock等待");
        synchronized (asyncToSyncUtil.lock) {
            try {
                asyncToSyncUtil.lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
