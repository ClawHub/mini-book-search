package com.clawhub.minibooksearch.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_book_source")
public class BookSource {
    /**
     * 书籍源id
     */
    @Id
    @Column(name = "source_id")
    private String sourceId;

    /**
     * 书籍id
     */
    @Column(name = "book_id")
    private String bookId;

    /**
     * 小说链接
     */
    private String url;

    /**
     * 更新时间
     */
    @Column(name = "update_Time")
    private Long updateTime;

    /**
     * 目录链接
     */
    @Column(name = "catalog_url")
    private String catalogUrl;

    /**
     * web站点
     */
    @Column(name = "web_site")
    private String webSite;

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
     * 获取书籍id
     *
     * @return book_id - 书籍id
     */
    public String getBookId() {
        return bookId;
    }

    /**
     * 设置书籍id
     *
     * @param bookId 书籍id
     */
    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    /**
     * 获取小说链接
     *
     * @return url - 小说链接
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置小说链接
     *
     * @param url 小说链接
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取更新时间
     *
     * @return updateTime - 更新时间
     */
    public Long getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取目录链接
     *
     * @return catalog_url - 目录链接
     */
    public String getCatalogUrl() {
        return catalogUrl;
    }

    /**
     * 设置目录链接
     *
     * @param catalogUrl 目录链接
     */
    public void setCatalogUrl(String catalogUrl) {
        this.catalogUrl = catalogUrl;
    }

    /**
     * 获取web站点
     *
     * @return web_site - web站点
     */
    public String getWebSite() {
        return webSite;
    }

    /**
     * 设置web站点
     *
     * @param webSite web站点
     */
    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }
}