package com.clawhub.minibooksearch.service;

/**
 * 书籍接口
 */
public interface BookService {
    /**
     * 推荐书籍
     *
     * @param pageNum  页数
     * @param pageSize 每页数据量
     * @return 推荐书籍
     */
    String recommend(int pageNum, int pageSize);

    /**
     * 查询书籍信息
     * @param pageNum 页数
     * @param pageSize 每页数据量
     * @param name 关键词
     * @return 书籍信息
     */
    String searchBookInfo(int pageNum, int pageSize, String name);

    /**
     * 查询书籍源
     * @param bookId 书籍id
     * @return 书籍源
     */
    String searchBookSource(String bookId);

    /**
     * 查询章节信息
     * @param sourceId 书籍源id
     * @return 章节信息
     */
    String searchChapter(String sourceId);
}
