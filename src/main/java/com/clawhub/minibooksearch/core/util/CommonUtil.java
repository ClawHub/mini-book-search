package com.clawhub.minibooksearch.core.util;

import com.clawhub.minibooksearch.core.constants.BookTypeConstant;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: create by lhy
 * @version: v1.0
 * @description: com.clawhub.minibooksearch.core.util
 * @date:2018/11/5
 */
public class CommonUtil {
    /**
     * 根据输入字符串转换常量
     * @param dataType
     * @param channel
     * @return
     */
    public static Map<String,String> checkRecommend(String dataType, String channel){
        Map<String,String> map = new HashMap<String, String>();
        //推荐榜默认类型为周榜
        if(dataType==null||"".equals(dataType)){
            dataType = BookTypeConstant.dataType1.getCode();
        }
        //书籍榜单类型默认全部分类
        if(channel==null||"".equals(channel)){
            channel = BookTypeConstant.channel1.getCode();
        }
        if(dataType.equals(BookTypeConstant.dataType1.getMessage())){
            dataType = BookTypeConstant.dataType1.getCode();
        }
        if(dataType.equals(BookTypeConstant.dataType2.getMessage())){
            dataType = BookTypeConstant.dataType2.getCode();
        }
        if(dataType.equals(BookTypeConstant.dataType3.getMessage())){
            dataType = BookTypeConstant.dataType3.getCode();
        }
        if(channel.equals(BookTypeConstant.channel1.getMessage())){
            channel = BookTypeConstant.channel1.getCode();
        }
        if(channel.equals(BookTypeConstant.channel2.getMessage())){
            channel = BookTypeConstant.channel2.getCode();
        }
        if(channel.equals(BookTypeConstant.channel3.getMessage())){
            channel = BookTypeConstant.channel3.getCode();
        }
        if(channel.equals(BookTypeConstant.channel4.getMessage())){
            channel = BookTypeConstant.channel4.getCode();
        }
        if(channel.equals(BookTypeConstant.channel5.getMessage())){
            channel = BookTypeConstant.channel5.getCode();
        }
        if(channel.equals(BookTypeConstant.channel6.getMessage())){
            channel = BookTypeConstant.channel6.getCode();
        }
        if(channel.equals(BookTypeConstant.channel7.getMessage())){
            channel = BookTypeConstant.channel7.getCode();
        }
        if(channel.equals(BookTypeConstant.channel8.getMessage())){
            channel = BookTypeConstant.channel8.getCode();
        }
        if(channel.equals(BookTypeConstant.channel9.getMessage())){
            channel = BookTypeConstant.channel9.getCode();
        }
        if(channel.equals(BookTypeConstant.channel10.getMessage())){
            channel = BookTypeConstant.channel10.getCode();
        }
        if(channel.equals(BookTypeConstant.channel11.getMessage())){
            channel = BookTypeConstant.channel11.getCode();
        }
        if(channel.equals(BookTypeConstant.channel12.getMessage())){
            channel = BookTypeConstant.channel12.getCode();
        }
        if(channel.equals(BookTypeConstant.channel13.getMessage())){
            channel = BookTypeConstant.channel13.getCode();
        }
        if(channel.equals(BookTypeConstant.channel14.getMessage())){
            channel = BookTypeConstant.channel14.getCode();
        }
        map.put("dataType",dataType);
        map.put("channel",channel);
        return map;
    }
}
