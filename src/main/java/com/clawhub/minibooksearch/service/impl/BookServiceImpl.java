package com.clawhub.minibooksearch.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.clawhub.minibooksearch.constant.MessageConstant;
import com.clawhub.minibooksearch.core.constants.ParamConstant;
import com.clawhub.minibooksearch.core.result.ResultUtil;
import com.clawhub.minibooksearch.entity.BookInfo;
import com.clawhub.minibooksearch.entity.BookSource;
import com.clawhub.minibooksearch.entity.Chapter;
import com.clawhub.minibooksearch.entity.Recommend;
import com.clawhub.minibooksearch.mapper.BookInfoMapper;
import com.clawhub.minibooksearch.mapper.BookSourceMapper;
import com.clawhub.minibooksearch.mapper.ChapterMapper;
import com.clawhub.minibooksearch.mapper.RecommendMapper;
import com.clawhub.minibooksearch.service.BookService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <Description> 书籍接口实现<br>
 *
 * @author ClawHub<br>
 * @version 1.0<br>
 * @taskId <br>
 * @create 2018 -10-11 20:32<br>
 */
@Service
public class BookServiceImpl implements BookService {
    /**
     * 推荐mapper
     */
    @Autowired
    private RecommendMapper recommendMapper;

    /**
     * The Book info mapper.
     */
    @Autowired
    private BookInfoMapper bookInfoMapper;

    /**
     * The Book source mapper.
     */
    @Autowired
    private BookSourceMapper bookSourceMapper;

    /**
     * The Chapter mapper.
     */
    @Autowired
    private ChapterMapper chapterMapper;

    /**
     * 推荐书籍
     *
     * @param pageNum  页数
     * @param pageSize 每页数据量
     * @return 推荐书籍
     */
    @Override
    public String recommend(int pageNum, int pageSize) {
        if (pageSize >= 500) {
            return ResultUtil.getError(MessageConstant.PATAM_ERROR);
        }
        Page<Recommend> page = PageHelper.startPage(pageNum, pageSize);
        List<Recommend> list = recommendMapper.selectAll();
        page.getTotal();
        JSONObject pageObject = new JSONObject();
        pageObject.put(ParamConstant.PAGE_ROWS, list);
        pageObject.put(ParamConstant.PAGE_TOTAL, page.getTotal());
        return ResultUtil.getSucc(pageObject);
    }

    /**
     * 查询书籍
     *
     * @param pageNum  页数
     * @param pageSize 每页数据量
     * @param name     关键词
     * @return 书籍信息
     */
    @Override
    public String searchBookInfo(int pageNum, int pageSize, String name) {
        if (pageSize >= 500) {
            return ResultUtil.getError(MessageConstant.PATAM_ERROR);
        }
        Page<BookInfo> page = PageHelper.startPage(pageNum, pageSize);
        List<BookInfo> list = bookInfoMapper.searchBookInfo(name);
        page.getTotal();
        JSONObject pageObject = new JSONObject();
        pageObject.put(ParamConstant.PAGE_ROWS, list);
        pageObject.put(ParamConstant.PAGE_TOTAL, page.getTotal());
        return ResultUtil.getSucc(pageObject);
    }

    /**
     * 查询书籍源
     *
     * @param bookId 书籍id
     * @return 书籍源
     */
    @Override
    public String searchBookSource(String bookId) {
        BookSource record = new BookSource();
        record.setBookId(bookId);
        List<BookSource> list = bookSourceMapper.select(record);
        return ResultUtil.getSucc(list);
    }

    /**
     * 查询章节信息
     *
     * @param sourceId 书籍源id
     * @return 章节信息
     */
    @Override
    public String searchChapter(String sourceId) {
        Chapter record = new Chapter();
        List<Chapter> list = chapterMapper.select(record);
        return ResultUtil.getSucc(list);
    }
}
