package com.clawhub.minibooksearch.entity;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "t_book_source_volume")
public class BookSourceVolume {
    /**
     * id
     */
    private String id;

    /**
     * 书籍源ID
     */
    @Column(name = "book_source_id")
    private String bookSourceId;

    /**
     * 卷ID
     */
    @Column(name = "volume_id")
    private String volumeId;

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
     * 获取书籍源ID
     *
     * @return book_source_id - 书籍源ID
     */
    public String getBookSourceId() {
        return bookSourceId;
    }

    /**
     * 设置书籍源ID
     *
     * @param bookSourceId 书籍源ID
     */
    public void setBookSourceId(String bookSourceId) {
        this.bookSourceId = bookSourceId;
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
}