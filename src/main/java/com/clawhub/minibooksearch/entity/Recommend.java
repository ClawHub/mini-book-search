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
     * 推荐榜类型
     */
    @Column(name = "data_type")
    private String dataType;

    /**
     * 书籍所属渠道类型
     */
    private String channel;

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

    /**
     * 获取推荐类型
     * @return
     */
    public String getDataType() {
        return dataType;
    }

    /**
     * 设置推荐类型
     * @param dataType
     */
    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    /**
     * 获取书籍所属类型
     * @return
     */
    public String getChannel() {
        return channel;
    }

    /**
     * 设置书籍所属类型
     * @param channel
     */
    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Recommend(){

    }
    /**
     * 构造
     * @param book
     * @param dataType
     * @param channel
     * @param createTime
     */
    public Recommend(String book, String dataType, String channel,Long createTime){
        this.book = book;
        this.dataType = dataType;
        this.channel = channel;
        this.createTime = createTime;
    }
}