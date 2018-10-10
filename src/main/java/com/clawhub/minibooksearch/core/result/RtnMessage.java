package com.clawhub.minibooksearch.core.result;

import com.alibaba.fastjson.annotation.JSONField;
import com.clawhub.minibooksearch.core.util.ResourceUtil;

/**
 * <Description> 信息封装< <br>
 *
 * @author LiZhiming<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2018年02月05日<br>
 */
public class RtnMessage {

    /**
     * 返回消息
     */
    @JSONField
    private String msgText;

    /**
     * 返回结果
     */
    @JSONField
    private String msgCode;

    /**
     * Instantiates a new Rtn message.
     *
     * @param msgCode the msg code
     */
    public RtnMessage(String msgCode) {
        this.msgCode = msgCode;
        this.msgText = ResourceUtil.getMessage(msgCode);
    }

    /**
     * Instantiates a new Rtn message.
     *
     * @param msgCode the msg code
     * @param msgText the msg text
     */
    public RtnMessage(String msgCode, String msgText) {
        this.msgCode = msgCode;
        this.msgText = msgText;
    }

    /**
     * Instantiates a new Rtn message.
     *
     * @param msgCode the msg code
     * @param obj     the obj
     */
    public RtnMessage(String msgCode, Object... obj) {
        this.msgCode = msgCode;
        this.msgText = ResourceUtil.getMessage(msgCode, obj);
    }

    /**
     * Description: Get msg text <br>
     *
     * @return string
     * @author LiZhiming <br>
     * @taskId <br>
     */
    public String getMsgText() {
        return msgText;
    }

    /**
     * Description: Set msg text <br>
     *
     * @param msgText msg text
     * @author LiZhiming <br>
     * @taskId <br>
     */
    public void setMsgText(String msgText) {
        this.msgText = msgText;
    }

    /**
     * Description: Get msg code <br>
     *
     * @return string
     * @author LiZhiming <br>
     * @taskId <br>
     */
    public String getMsgCode() {
        return msgCode;
    }

    /**
     * Description: Set msg code <br>
     *
     * @param msgCode msg code
     * @author LiZhiming <br>
     * @taskId <br>
     */
    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode;
    }

}
