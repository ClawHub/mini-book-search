package com.clawhub.minibooksearch.service.impl;

import com.clawhub.minibooksearch.entity.Volume;
import com.clawhub.minibooksearch.mapper.VolumeMapper;
import com.clawhub.minibooksearch.service.SpiderService;
import com.clawhub.minibooksearch.service.VolumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <Description> 卷服务实现<br>
 *
 * @author ClawHub<br>
 * @version 1.0<br>
 * @taskId <br>
 * @create 2019-03-10 10:07<br>
 */
@Service
public class VolumeServiceImpl implements VolumeService {

    /**
     * The Volume mapper.
     */
    @Autowired
    private VolumeMapper volumeMapper;
    /**
     * The Spider service.
     */
    @Autowired
    private SpiderService spiderService;

    /**
     * 查询卷
     *
     * @param sourceId   书籍源ID
     * @param catalogUrl 目录url
     * @param webSite    站点
     * @return 卷列表
     */
    @Override
    public List<Volume> searchVolumes(String sourceId, String catalogUrl, String webSite) {
        //首先查询数据库
        List<Volume> volumeList = volumeMapper.searchVolumesBySourceId(sourceId);
        //如果查的数据，则直接返回
        //否则调用爬虫接口,同步调用
        if (!CollectionUtils.isEmpty(volumeList)) {
            return volumeList;
        } else {
            return spiderService.crawlCatalog(webSite, catalogUrl, sourceId).getVolumeList();
        }
    }
}
