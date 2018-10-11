package com.clawhub.minibooksearch.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_chapter")
public class Chapter {
    /**
     * 章节id
     */
    @Id
    private String id;

    /**
     * 书籍源id
     */
    @Column(name = "source_id")
    private String sourceId;

    /**
     * 章节名称
     */
    private String name;

    /**
     * 章节链接
     */
    private String url;

    /**
     * 首发时间
     */
    @Column(name = "date_time")
    private Long dateTime;

    /**
     * 序列
     */
    private Integer sort;

    /**
     * 章节字数
     */
    private Integer number;

    /**
     * 获取章节id
     *
     * @return id - 章节id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置章节id
     *
     * @param id 章节id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取书籍源id
     *
     * @return source_id - 书籍源id
     */
    public String getSourceId() {
        return sourceId;
    }

    /**
     * 设置书籍源id
     *
     * @param sourceId 书籍源id
     */
    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    /**
     * 获取章节名称
     *
     * @return name - 章节名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置章节名称
     *
     * @param name 章节名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取章节链接
     *
     * @return url - 章节链接
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置章节链接
     *
     * @param url 章节链接
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取首发时间
     *
     * @return date_time - 首发时间
     */
    public Long getDateTime() {
        return dateTime;
    }

    /**
     * 设置首发时间
     *
     * @param dateTime 首发时间
     */
    public void setDateTime(Long dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * 获取序列
     *
     * @return sort - 序列
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置序列
     *
     * @param sort 序列
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 获取章节字数
     *
     * @return number - 章节字数
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * 设置章节字数
     *
     * @param number 章节字数
     */
    public void setNumber(Integer number) {
        this.number = number;
    }
}