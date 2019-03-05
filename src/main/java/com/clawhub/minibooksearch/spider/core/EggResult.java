package com.clawhub.minibooksearch.spider.core;

import com.clawhub.minibooksearch.entity.BookInfo;

import java.util.List;

/**
 * <Description> 爬虫结果<br>
 *
 * @author ClawHub<br>
 * @version 1.0<br>
 * @taskId <br>
 * @create 2018-10-15 21:21<br>
 */
public class EggResult {
    /**
     * The Success.
     */
    private boolean success;
    /**
     * The Msg.
     */
    private String msg;
    /**
     * The Book info list.
     */
    private List<BookInfo> bookInfoList;

    /**
     * Error egg result.
     *
     * @param msg the msg
     * @return the egg result
     */
    public static EggResult error(String msg) {
        EggResult result = new EggResult();
        result.success = false;
        result.msg = msg;
        return result;
    }

    /**
     * Success egg result.
     *
     * @param bookInfoList the book info list
     * @return the egg result
     */
    public static EggResult success(List<BookInfo> bookInfoList) {
        EggResult result = new EggResult();
        result.success = true;
        result.bookInfoList = bookInfoList;
        return result;
    }

    /**
     * Is success boolean.
     *
     * @return the boolean
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Gets msg.
     *
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * Gets book info list.
     *
     * @return the book info list
     */
    public List<BookInfo> getBookInfoList() {
        return bookInfoList;
    }
}
