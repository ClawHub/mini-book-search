package com.clawhub.minibooksearch.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_book_info")
public class BookInfo {
    /**
     * 书籍id
     */
    @Id
    private String id;

    /**
     * 书名
     */
    private String name;

    /**
     * 作者
     */
    private String auther;

    /**
     * 站点 0:女 1:男  
     */
    private String site;

    /**
     * 分类 0：玄幻 1：奇幻 2：武侠 3：仙侠 4：都市 。。。。
     */
    private String classify;

    /**
     * 状态 0：连载 1：完本
     */
    private String state;

    /**
     * 属性 0：免费 1:vip
     */
    private String attribute;

    /**
     * 字数
     */
    private Integer number;

    /**
     * 简介
     */
    private String remark;

    /**
     * 图片链接
     */
    @Column(name = "pic_url")
    private String picUrl;

    /**
     * 获取书籍id
     *
     * @return id - 书籍id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置书籍id
     *
     * @param id 书籍id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取书名
     *
     * @return name - 书名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置书名
     *
     * @param name 书名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取作者
     *
     * @return auther - 作者
     */
    public String getAuther() {
        return auther;
    }

    /**
     * 设置作者
     *
     * @param auther 作者
     */
    public void setAuther(String auther) {
        this.auther = auther;
    }

    /**
     * 获取站点 0:女 1:男  
     *
     * @return site - 站点 0:女 1:男  
     */
    public String getSite() {
        return site;
    }

    /**
     * 设置站点 0:女 1:男  
     *
     * @param site 站点 0:女 1:男  
     */
    public void setSite(String site) {
        this.site = site;
    }

    /**
     * 获取分类 0：玄幻 1：奇幻 2：武侠 3：仙侠 4：都市 。。。。
     *
     * @return classify - 分类 0：玄幻 1：奇幻 2：武侠 3：仙侠 4：都市 。。。。
     */
    public String getClassify() {
        return classify;
    }

    /**
     * 设置分类 0：玄幻 1：奇幻 2：武侠 3：仙侠 4：都市 。。。。
     *
     * @param classify 分类 0：玄幻 1：奇幻 2：武侠 3：仙侠 4：都市 。。。。
     */
    public void setClassify(String classify) {
        this.classify = classify;
    }

    /**
     * 获取状态 0：连载 1：完本
     *
     * @return state - 状态 0：连载 1：完本
     */
    public String getState() {
        return state;
    }

    /**
     * 设置状态 0：连载 1：完本
     *
     * @param state 状态 0：连载 1：完本
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * 获取属性 0：免费 1:vip
     *
     * @return attribute - 属性 0：免费 1:vip
     */
    public String getAttribute() {
        return attribute;
    }

    /**
     * 设置属性 0：免费 1:vip
     *
     * @param attribute 属性 0：免费 1:vip
     */
    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    /**
     * 获取字数
     *
     * @return number - 字数
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * 设置字数
     *
     * @param number 字数
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * 获取简介
     *
     * @return remark - 简介
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置简介
     *
     * @param remark 简介
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取图片链接
     *
     * @return pic_url - 图片链接
     */
    public String getPicUrl() {
        return picUrl;
    }

    /**
     * 设置图片链接
     *
     * @param picUrl 图片链接
     */
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
}