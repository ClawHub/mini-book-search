package com.clawhub.minibooksearch.service.impl;

import com.clawhub.minibooksearch.entity.Chapter;
import com.clawhub.minibooksearch.mapper.ChapterMapper;
import com.clawhub.minibooksearch.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <Description> 章节服务实现类<br>
 *
 * @author ClawHub<br>
 * @version 1.0<br>
 * @taskId <br>
 * @create 2019-03-11 0:02<br>
 */
@Service
public class ChapterServiceImpl implements ChapterService {

    @Autowired
    private ChapterMapper chapterMapper;

    /**
     * 根据卷ID获取章节列表
     *
     * @param volumeId 卷ID
     * @return 章节列表
     */
    @Override
    public List<Chapter> searchChaptersByVolumeId(String volumeId) {

        return chapterMapper.searchChaptersByVolumeId(volumeId);
    }
}
