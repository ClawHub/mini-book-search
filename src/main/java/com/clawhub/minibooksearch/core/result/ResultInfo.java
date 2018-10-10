package com.clawhub.minibooksearch.core.result;

/**
 * <Description>返回结果封装 <br>
 *
 * @author LiZhiming<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2018 -09-10 <br>
 */
public class ResultInfo<E> {

    /**
     * The Status.
     */
    private boolean status;

    /**
     * The Msg.
     */
    private RtnMessage msg;

    /**
     * The Data.
     */
    private E data;

    /**
     * Instantiates a new Result info.
     *
     * @param status the status
     * @param msg    the msg
     * @param data   the data
     */
    public ResultInfo(boolean status, RtnMessage msg, E data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    /**
     * Instantiates a new Result info.
     *
     * @param status the status
     * @param msg    the msg
     */
    public ResultInfo(boolean status, RtnMessage msg) {
        this.status = status;
        this.msg = msg;
    }

    /**
     * Instantiates a new Result info.
     *
     * @param status the status
     * @param data   the data
     */
    public ResultInfo(boolean status, E data) {
        this.status = status;
        this.data = data;
    }

    /**
     * Is status boolean.
     *
     * @return the boolean
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * Gets msg.
     *
     * @return the msg
     */
    public RtnMessage getMsg() {
        return msg;
    }

    /**
     * Sets msg.
     *
     * @param msg the msg
     */
    public void setMsg(RtnMessage msg) {
        this.msg = msg;
    }

    /**
     * Gets data.
     *
     * @return the data
     */
    public E getData() {
        return data;
    }

    /**
     * Sets data.
     *
     * @param data the data
     */
    public void setData(E data) {
        this.data = data;
    }
}
