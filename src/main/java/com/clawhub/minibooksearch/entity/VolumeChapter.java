package com.clawhub.minibooksearch.entity;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "t_volume_chapter")
public class VolumeChapter {
    /**
     * id
     */
    private String id;

    /**
     * 卷ID
     */
    @Column(name = "volume_id")
    private String volumeId;

    /**
     * 章节ID
     */
    @Column(name = "chapter_id")
    private String chapterId;

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
     * 获取卷ID
     *
     * @return volume_id - 卷ID
     */
    public String getVolumeId() {
        return volumeId;
    }

    /**
     * 设置卷ID
     *
     * @param volumeId 卷ID
     */
    public void setVolumeId(String volumeId) {
        this.volumeId = volumeId;
    }

    /**
     * 获取章节ID
     *
     * @return chapter_id - 章节ID
     */
    public String getChapterId() {
        return chapterId;
    }

    /**
     * 设置章节ID
     *
     * @param chapterId 章节ID
     */
    public void setChapterId(String chapterId) {
        this.chapterId = chapterId;
    }
}