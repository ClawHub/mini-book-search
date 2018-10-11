package com.clawhub.minibooksearch.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_recommend")
public class Recommend {
    /**
     * id
     */
    @Id
    private String id;

    /**
     * 书名
     */
    private String book;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Long createTime;

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
     * 获取书名
     *
     * @return book - 书名
     */
    public String getBook() {
        return book;
    }

    /**
     * 设置书名
     *
     * @param book 书名
     */
    public void setBook(String book) {
        this.book = book;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}