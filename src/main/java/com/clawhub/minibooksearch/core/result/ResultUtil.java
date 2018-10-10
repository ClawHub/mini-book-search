package com.clawhub.minibooksearch.core.result;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.clawhub.minibooksearch.core.constants.StatusConstant;

/**
 * <Description> 返回结果工具类 <br>
 *
 * @author LiZhiming<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2018年02月05日<br>
 */
public class ResultUtil {

    /**
     * Description: 返回正确信息，有正确码 <br>
     *
     * @param obj         obj
     * @param messageCode message code
     * @return string
     * @author LiZhiming <br>
     * @taskId <br>
     */
    public static String getSucc(Object obj, String messageCode) {
        return JSONObject.toJSONString(new ResultInfo<>(StatusConstant.RESULT_SUCC, new RtnMessage(messageCode), obj),
                SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullListAsEmpty);
    }

    /**
     * Description:返回正确信息，有正确码,带占位符信息 <br>
     *
     * @param obj         obj
     * @param messageCode message code
     * @param msg         msg
     * @return string
     * @author LiZhiming <br>
     * @taskId <br>
     */
    public static String getSucc(Object obj, String messageCode, Object... msg) {
        return JSONObject.toJSONString(new ResultInfo<>(StatusConstant.RESULT_SUCC, new RtnMessage(messageCode, msg), obj),
                SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullListAsEmpty);
    }

    /**
     * 返回成功数据
     *
     * @param obj obj
     * @return string
     */
    public static String getSucc(Object obj) {
        return JSONObject.toJSONString(new ResultInfo<>(StatusConstant.RESULT_SUCC, new RtnMessage("1000"), obj),
                SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullListAsEmpty);
    }

    /**
     * Gets succ.
     *
     * @return the succ
     */
    public static String getSucc() {
        return JSONObject.toJSONString(new ResultInfo<>(StatusConstant.RESULT_SUCC, new RtnMessage("1000")),
                SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullListAsEmpty);

    }


    /**
     * Description: 有正确码,带占位符信息<br>
     *
     * @param messageCode message code
     * @param msg         msg
     * @return string
     * @author LiZhiming <br>
     * @taskId <br>
     */
    public static String getSucc(String messageCode, Object... msg) {
        return JSONObject.toJSONString(new ResultInfo<>(StatusConstant.RESULT_SUCC, new RtnMessage(messageCode, msg)),
                SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullListAsEmpty);
    }

    /**
     * Description: 根据错误编码构造一条表示错误信息的json字符串 <br>
     *
     * @param errorCode error code
     * @return string
     * @author LiZhiming <br>
     * @taskId <br>
     */
    public static String getError(String errorCode) {
        return JSONObject.toJSONString(new ResultInfo<>(StatusConstant.RESULT_ERROR, new RtnMessage(errorCode)),
                SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullListAsEmpty);
    }

    /**
     * Gets error.
     *
     * @param errorCode the error code
     * @param msgText   the msg text
     * @return the error
     */
    public static String getError(String errorCode, String msgText) {
        return JSONObject.toJSONString(new ResultInfo<>(StatusConstant.RESULT_ERROR, new RtnMessage(errorCode, msgText)),
                SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullListAsEmpty);
    }
}