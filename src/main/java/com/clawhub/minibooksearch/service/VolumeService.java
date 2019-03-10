package com.clawhub.minibooksearch.service;

import com.clawhub.minibooksearch.entity.Volume;

import java.util.List;

/**
 * <Description> 卷 服务<br>
 *
 * @author ClawHub<br>
 * @version 1.0<br>
 * @taskId <br>
 * @create 2019-03-10 10:03<br>
 */
public interface VolumeService {
    /**
     * 查询卷
     *
     * @param sourceId   书籍源ID
     * @param catalogUrl 目录url
     * @param webSite    站点
     * @return 卷列表
     */
    List<Volume> searchVolumes(String sourceId, String catalogUrl, String webSite);
}
