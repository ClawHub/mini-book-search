package com.clawhub.minibooksearch.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * <Description> 目录结果封装<br>
 *
 * @author ClawHub<br>
 * @version 1.0<br>
 * @taskId <br>
 * @create 2019-03-10 12:10<br>
 */
public class CatalogResult {

    private boolean success;
    private String sourceId;
    private List<Volume> volumeList;
    private List<Chapter> chapterList;
    private List<BookSourceVolume> bookSourceVolumeList;
    private List<VolumeChapter> volumeChapterList;


    public CatalogResult() {
        volumeList = new ArrayList<>();
        chapterList = new ArrayList<>();
        bookSourceVolumeList = new ArrayList<>();
        volumeChapterList = new ArrayList<>();
    }

    public CatalogResult error() {
        this.success = false;
        return this;
    }

    public CatalogResult result() {
        this.success = true;
        return this;
    }

    public CatalogResult setSourceId(String sourceId) {
        this.sourceId = sourceId;
        return this;
    }

    public CatalogResult addVolumeChapterList(List<VolumeChapter> volumeChapterList) {
        this.volumeChapterList.addAll(volumeChapterList);
        return this;
    }

    public CatalogResult addBookSourceVolumeList(List<BookSourceVolume> bookSourceVolumeList) {
        this.bookSourceVolumeList.addAll(bookSourceVolumeList);
        return this;
    }

    public CatalogResult addChapterList(List<Chapter> chapterList) {
        this.chapterList.addAll(chapterList);
        return this;
    }

    public CatalogResult addVolumeList(List<Volume> volumeList) {
        this.volumeList.addAll(volumeList);
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getSourceId() {
        return sourceId;
    }

    public List<Volume> getVolumeList() {
        return volumeList;
    }

    public List<Chapter> getChapterList() {
        return chapterList;
    }

    public List<BookSourceVolume> getBookSourceVolumeList() {
        return bookSourceVolumeList;
    }

    public List<VolumeChapter> getVolumeChapterList() {
        return volumeChapterList;
    }
}
