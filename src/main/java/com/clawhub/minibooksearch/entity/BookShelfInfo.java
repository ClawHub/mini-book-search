package com.clawhub.minibooksearch.entity;

/**
 * <Description> 书架上书籍信息<br>
 *
 * @author ClawHub<br>
 * @version 1.0<br>
 * @taskId <br>
 * @create 2018-10-25 21:43<br>
 */
public class BookShelfInfo {
    /**
     * The Book shelf.
     */
    private BookShelf bookShelf;
    /**
     * The Book info.
     */
    private BookInfo bookInfo;

    /**
     * Gets book shelf.
     *
     * @return the book shelf
     */
    public BookShelf getBookShelf() {
        return bookShelf;
    }

    /**
     * Sets book shelf.
     *
     * @param bookShelf the book shelf
     */
    public void setBookShelf(BookShelf bookShelf) {
        this.bookShelf = bookShelf;
    }

    /**
     * Gets book info.
     *
     * @return the book info
     */
    public BookInfo getBookInfo() {
        return bookInfo;
    }

    /**
     * Sets book info.
     *
     * @param bookInfo the book info
     */
    public void setBookInfo(BookInfo bookInfo) {
        this.bookInfo = bookInfo;
    }
}
