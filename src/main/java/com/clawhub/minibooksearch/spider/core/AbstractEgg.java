package com.clawhub.minibooksearch.spider.core;

import com.clawhub.minibooksearch.core.http.HttpGenerator;
import com.clawhub.minibooksearch.core.http.HttpResInfo;
import com.clawhub.minibooksearch.core.util.IDGenarator;
import com.clawhub.minibooksearch.entity.BookInfo;
import com.clawhub.minibooksearch.entity.BookSource;
import com.clawhub.minibooksearch.entity.Chapter;
import com.clawhub.minibooksearch.mapper.BookInfoMapper;
import com.clawhub.minibooksearch.mapper.BookSourceMapper;
import com.clawhub.minibooksearch.mapper.ChapterMapper;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;

/**
 * <Description>爬虫抽象类<br>
 *
 * @author LiZhiming<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2018/10/24 10:50 <br>
 */
public abstract class AbstractEgg implements Egg {
    /**
     * The Logger.
     */
    protected Logger logger = LoggerFactory.getLogger(Egg.class);
    /**
     * The Book info mapper.
     */
    @Autowired
    protected BookInfoMapper bookInfoMapper;

    /**
     * The Book source mapper.
     */
    @Autowired
    protected BookSourceMapper bookSourceMapper;

    /**
     * The Chapter mapper.
     */
    @Autowired
    protected ChapterMapper chapterMapper;
    /**
     * The String redis template.
     */
    @Autowired
    protected StringRedisTemplate stringRedisTemplate;
    /**
     * The Book duplicate removal redis key prefix.
     */
    @Value("${redis.key.prefix.book.duplicate.removal}")
    protected String bookDuplicateRemovalRedisKeyPrefix;

    /**
     * The Source duplicate removal redis key prefix.
     */
    @Value("${redis.key.prefix.source.duplicate.removal}")
    protected String sourceDuplicateRemovalRedisKeyPrefix;
    /**
     * 最多解析书籍条数
     */
    @Value("${parse.search.keyword.max.num}")
    protected int maxNum;

    /**
     * 获取bookId
     *
     * @param bookName 书名
     * @param author   作者
     * @return bookId
     */
    private String getBookId(String bookName, String author) {
        String key = bookDuplicateRemovalRedisKeyPrefix + bookName + "-" + author;
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 书籍基础信息去重
     *
     * @param bookName 书名
     * @param author   作者
     * @param bookId   bookId
     */
    private void bookInfoDuplicateRemoval(String bookName, String author, String bookId) {
        String key = bookDuplicateRemovalRedisKeyPrefix + bookName + "-" + author;
        stringRedisTemplate.opsForValue().set(key, bookId);
    }

    /**
     * 检查book数据源信息是否存在
     *
     * @param webSite  来源站点
     * @param sourceId 源书籍ID
     * @return true:已存在  false:不存在
     */
    private boolean checkBookSource(String webSite, String sourceId) {
        String key = sourceDuplicateRemovalRedisKeyPrefix + webSite + "-" + sourceId;
        String value = stringRedisTemplate.opsForValue().get(key);
        return StringUtils.isNotBlank(value);
    }

    /**
     * 书籍源信息去重
     *
     * @param webSite  站点
     * @param sourceId 源站点书籍ID
     */
    private void bookSourceDuplicateRemoval(String webSite, String sourceId) {
        String key = sourceDuplicateRemovalRedisKeyPrefix + webSite + "-" + sourceId;
        stringRedisTemplate.opsForValue().set(key, "1");
    }

    /**
     * 书籍源信息入库
     *
     * @param bookId     bookId
     * @param bookSource the book source
     */
    private void insertBookSource(String bookId, BookSource bookSource) {
        bookSource.setBookId(bookId);
        bookSourceMapper.insert(bookSource);
    }

    /**
     * 书籍基础信息入库
     *
     * @param bookId   bookId
     * @param bookinfo the bookinfo
     */
    private void insertBookInfo(String bookId, BookInfo bookinfo) {
        bookinfo.setId(bookId);
        bookInfoMapper.insert(bookinfo);
    }

    @Override
    public EggResult touch(String keyword) {
        searchKeyword(keyword);
        return null;
    }

    /**
     * 查询关键词
     *
     * @param keyword the keyword
     */
    private void searchKeyword(String keyword) {
        logger.info("查询关键词");
        String url = getSearchUrl(keyword);
        if (StringUtils.isBlank(url)) {
            return;
        }
        //搜索请求
        HttpResInfo resInfo = HttpGenerator.sendGet(url, 6000, 6000, null, false);
        //请求成功
        if (resInfo.getSuccess()) {
            String html = resInfo.getResult();
            //解析查询到的第一页数据
            parseSearchKeyword(html);
        }

    }

    /**
     * 解析查询到的第一页数据
     *
     * @param html the html
     */
    private void parseSearchKeyword(String html) {
        logger.info("解析查询到的第一页数据");
        Document document = Jsoup.parse(html);
        Elements elements = getBookList(document);
        int num = 0;
        for (Element element : elements) {
            //控制解析的数量
            num++;
            if (num > maxNum) {
                return;
            }
            //解析单条数据
            parseSingleBook(element);
        }
    }

    /**
     * 解析单条数据
     *
     * @param element the element
     */
    private void parseSingleBook(Element element) {
        //书籍基础信息处理
        BookInfo bookinfo = getBookInfo(element);
        //书籍ID
        String bookId = getBookId(bookinfo.getName(), bookinfo.getAuthor());
        logger.info("bookId:{}", bookId);
        if (StringUtils.isBlank(bookId)) {
            bookId = IDGenarator.getID();
            //插入书籍信息
            insertBookInfo(bookId, bookinfo);
            //书籍信息去重
            bookInfoDuplicateRemoval(bookinfo.getName(), bookinfo.getAuthor(), bookId);
        }
        //书籍源信息处理
        BookSource bookSource = getBookSource(element);
        if (!checkBookSource(bookSource.getWebSite(), bookSource.getSourceId())) {
            //书籍源信息入库
            insertBookSource(bookId, bookSource);
            //书籍源信息去重
            bookSourceDuplicateRemoval(bookSource.getWebSite(), bookSource.getSourceId());
        }
    }

    /**
     * 查询url
     *
     * @param keyword the keyword
     * @return the search url
     */
    protected abstract String getSearchUrl(String keyword);

    /**
     * 获取查询到的书籍列表
     *
     * @param document the document
     * @return the book list
     */
    protected abstract Elements getBookList(Document document);

    /**
     * 获取书籍信息
     *
     * @param element the element
     * @return the book info
     */
    protected abstract BookInfo getBookInfo(Element element);

    /**
     * 获取书籍源信息
     *
     * @param element the element
     * @return the book source
     */
    protected abstract BookSource getBookSource(Element element);


    public abstract List<Chapter> chapter(String catalogUrl, String sourceId);
}