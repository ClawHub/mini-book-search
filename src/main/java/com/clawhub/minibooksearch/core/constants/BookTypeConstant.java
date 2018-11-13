package com.clawhub.minibooksearch.core.constants;

/**
 * @author: create by lhy
 * @version: v1.0
 * @description: com.clawhub.minibooksearch.core.constants
 * @date:2018/11/5
 */
public enum  BookTypeConstant {
    /**
     * 推荐榜类型 书籍类型
     */
    dataType1("1","周榜"),
    dataType2("2","月榜"),
    dataType3("3","总榜"),
    channel1("-1","全部分类"),
    channel2("21","玄幻"),
    channel3("1","奇幻"),
    channel4("2","武侠"),
    channel5("22","仙侠"),
    channel6("4","都市"),
    channel7("15","现实"),
    channel8("6","军事"),
    channel9("5","历史"),
    channel10("7","游戏"),
    channel11("8","体育"),
    channel12("9","科幻"),
    channel13("10","灵异"),
    channel14("12","二次元");

    /**
     * 书籍类型码
     */
    private String code;

    /**
     * 书籍类型描述
     */
    private String message;

    /**
     * 构造
     * @param code
     * @param message
     */
    private  BookTypeConstant(String code,String message){
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
