package com.clawhub.minibooksearch.service;

import com.clawhub.minibooksearch.entity.Chapter;

import java.util.List;

/**
 * <Description> 章节服务<br>
 *
 * @author ClawHub<br>
 * @version 1.0<br>
 * @taskId <br>
 * @create 2019-03-11 0:01<br>
 */
public interface ChapterService {
    /**
     * 根据卷ID获取章节列表
     *
     * @param volumeId 卷ID
     * @return 章节列表
     */
    List<Chapter> searchChaptersByVolumeId(String volumeId);
}
