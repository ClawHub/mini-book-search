package com.clawhub.minibooksearch.entity;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "t_book_shelf")
public class BookShelf {
    /**
     * id
     */
    private String id;

    /**
     * openId
     */
    @Column(name = "open_id")
    private String openId;

    /**
     * bookId
     */
    @Column(name = "book_id")
    private String bookId;

    /**
     * 新增时间
     */
    @Column(name = "date_time")
    private Long dateTime;

    /**
     * 获取id
     *
     * @return id - id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取openId
     *
     * @return open_id - openId
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * 设置openId
     *
     * @param openId openId
     */
    public void setOpenId(String openId) {
        this.openId = openId;
    }

    /**
     * 获取bookId
     *
     * @return book_id - bookId
     */
    public String getBookId() {
        return bookId;
    }

    /**
     * 设置bookId
     *
     * @param bookId bookId
     */
    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    /**
     * 获取新增时间
     *
     * @return date_time - 新增时间
     */
    public Long getDateTime() {
        return dateTime;
    }

    /**
     * 设置新增时间
     *
     * @param dateTime 新增时间
     */
    public void setDateTime(Long dateTime) {
        this.dateTime = dateTime;
    }
}