package com.clawhub.minibooksearch.associative;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * <Description> 联想搜索接口<br>
 *
 * @author ClawHub<br>
 * @version 1.0<br>
 * @taskId <br>
 * @create 2019-03-04 22:40<br>
 */
public interface ASInterface {
    /**
     * 获取书籍名称
     *
     * @param key 关键词
     * @return 书籍名称
     */
    List<String> searchBookName(String key) throws UnsupportedEncodingException;
}
