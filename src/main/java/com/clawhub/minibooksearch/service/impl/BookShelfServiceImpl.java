package com.clawhub.minibooksearch.service.impl;

import com.clawhub.minibooksearch.constant.MessageConstant;
import com.clawhub.minibooksearch.core.result.ResultUtil;
import com.clawhub.minibooksearch.core.util.IDGenarator;
import com.clawhub.minibooksearch.entity.BookShelf;
import com.clawhub.minibooksearch.entity.BookShelfInfo;
import com.clawhub.minibooksearch.mapper.BookShelfInfoMapper;
import com.clawhub.minibooksearch.mapper.BookShelfMapper;
import com.clawhub.minibooksearch.service.BookShelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <Description> 书架逻辑<br>
 *
 * @author ClawHub<br>
 * @version 1.0<br>
 * @taskId <br>
 * @create 2018-10-25 21:37<br>
 */
@Service
public class BookShelfServiceImpl implements BookShelfService {

    /**
     * The Book shelf mapper.
     */
    @Autowired
    private BookShelfMapper bookShelfMapper;
    /**
     * The Book shelf info mapper.
     */
    @Autowired
    private BookShelfInfoMapper bookShelfInfoMapper;

    /**
     * The String redis template.
     */
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * The Book shelf redis key prefix.
     */
    @Value("${redis.key.prefix.book.shelf}")
    protected String bookShelfRedisKeyPrefix;

    /**
     * /**
     * 新增
     *
     * @param bookId bookId
     * @param openId openId
     * @return string
     */
    @Override
    public String addBookShelf(String bookId, String openId) {
        //判断是否加入过书架
        boolean flag = stringRedisTemplate.opsForSet().isMember(bookShelfRedisKeyPrefix + openId, bookId);
        if (flag) {
            return ResultUtil.getError(MessageConstant.BOOK_SHELF_EXIST);
        }
        BookShelf bookShelf = new BookShelf();
        bookShelf.setBookId(bookId);
        bookShelf.setOpenId(openId);
        bookShelf.setId(IDGenarator.getID());
        bookShelf.setDateTime(System.currentTimeMillis());
        bookShelfMapper.insert(bookShelf);
        //加入书架
        stringRedisTemplate.opsForSet().add(bookShelfRedisKeyPrefix + openId, bookId);
        return ResultUtil.getSucc();
    }

    /**
     * 删除
     *
     * @param bookId the book id
     * @param openId the open id
     */
    @Override
    public void delBookShelf(String bookId, String openId) {
        //删除数据库中记录
        BookShelf bookShelf = new BookShelf();
        bookShelf.setOpenId(openId);
        bookShelf.setBookId(bookId);
        bookShelfMapper.delete(bookShelf);
        //redis中去处
        stringRedisTemplate.opsForSet().remove(bookShelfRedisKeyPrefix + openId, bookId);
    }

    /**
     * 查看
     *
     * @param openId openId
     * @return
     */
    @Override
    public String viewBookShelf(String openId) {
        List<BookShelfInfo> list = bookShelfInfoMapper.viewBookShelf(openId);
        return ResultUtil.getSucc(list);
    }
}
