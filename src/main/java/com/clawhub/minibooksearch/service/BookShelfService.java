package com.clawhub.minibooksearch.service;

/**
 * <Description> 书架接口<br>
 *
 * @author ClawHub<br>
 * @version 1.0<br>
 * @taskId <br>
 * @create 2018-10-25 21:30<br>
 */
public interface BookShelfService {
    /**
     * 新增
     *
     * @param bookId bookId
     * @param openId openId
     * @return String
     */
    String addBookShelf(String bookId, String openId);

    /**
     * 删除
     *
     * @param bookId the book id
     * @param openId the open id
     */
    void delBookShelf(String bookId, String openId);

    /**
     * 查看
     *
     * @param openId
     * @return
     */
    String viewBookShelf(String openId);
}
