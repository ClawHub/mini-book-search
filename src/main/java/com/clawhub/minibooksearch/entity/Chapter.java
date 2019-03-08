package com.clawhub.minibooksearch.entity;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "t_chapter")
public class Chapter {
    /**
     * 章节id，唯一
     */
    private String id;

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
     * 序列，用于排序
     */
    private Long sort;

    /**
     * 章节字数
     */
    private Integer number;

    /**
     * 章节序号，如第一章，用于展示
     */
    @Column(name = "serial_num")
    private String serialNum;

    /**
     * 获取章节id，唯一
     *
     * @return id - 章节id，唯一
     */
    public String getId() {
        return id;
    }

    /**
     * 设置章节id，唯一
     *
     * @param id 章节id，唯一
     */
    public void setId(String id) {
        this.id = id;
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
     * 获取序列，用于排序
     *
     * @return sort - 序列，用于排序
     */
    public Long getSort() {
        return sort;
    }

    /**
     * 设置序列，用于排序
     *
     * @param sort 序列，用于排序
     */
    public void setSort(Long sort) {
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

    /**
     * 获取章节序号，如第一章，用于展示
     *
     * @return serial_num - 章节序号，如第一章，用于展示
     */
    public String getSerialNum() {
        return serialNum;
    }

    /**
     * 设置章节序号，如第一章，用于展示
     *
     * @param serialNum 章节序号，如第一章，用于展示
     */
    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }
}