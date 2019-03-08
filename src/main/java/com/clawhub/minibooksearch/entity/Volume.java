package com.clawhub.minibooksearch.entity;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "t_volume")
public class Volume {
    /**
     * 卷ID,唯一
     */
    private String id;

    /**
     * 卷名称
     */
    private String name;

    /**
     * 章节数量
     */
    @Column(name = "chapter_count")
    private Integer chapterCount;

    /**
     * 卷文字字数
     */
    @Column(name = "word_count")
    private Integer wordCount;

    /**
     * 卷序号，如：第三卷
     */
    @Column(name = "serial_num")
    private String serialNum;

    /**
     * 序列，用于排序
     */
    private Long sort;

    /**
     * 获取卷ID,唯一
     *
     * @return id - 卷ID,唯一
     */
    public String getId() {
        return id;
    }

    /**
     * 设置卷ID,唯一
     *
     * @param id 卷ID,唯一
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取卷名称
     *
     * @return name - 卷名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置卷名称
     *
     * @param name 卷名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取章节数量
     *
     * @return chapter_count - 章节数量
     */
    public Integer getChapterCount() {
        return chapterCount;
    }

    /**
     * 设置章节数量
     *
     * @param chapterCount 章节数量
     */
    public void setChapterCount(Integer chapterCount) {
        this.chapterCount = chapterCount;
    }

    /**
     * 获取卷文字字数
     *
     * @return word_count - 卷文字字数
     */
    public Integer getWordCount() {
        return wordCount;
    }

    /**
     * 设置卷文字字数
     *
     * @param wordCount 卷文字字数
     */
    public void setWordCount(Integer wordCount) {
        this.wordCount = wordCount;
    }

    /**
     * 获取卷序号，如：第三卷
     *
     * @return serial_num - 卷序号，如：第三卷
     */
    public String getSerialNum() {
        return serialNum;
    }

    /**
     * 设置卷序号，如：第三卷
     *
     * @param serialNum 卷序号，如：第三卷
     */
    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
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
}