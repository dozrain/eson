package com.rain.rabbit.mq;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ron
 *         2016/12/15.
 */
public enum BusinessStatus {

    //消息类型，0：测试1
    MESSAGE_TYPE_MAIL(0, "邮件");

    private String text;
    private Object state;
    private String key;

    BusinessStatus(Object state, String text){
        this.text = text;
        this.state = state;
        this.key = "null";
    }

    BusinessStatus(Object state, String text, String key){
        this.text = text;
        this.state = state;
        this.key = key;
    }
    public int intValue() {
        int temp = temp = (int)state;
        return temp;
    }



    public BigDecimal bigDecimalValue(){
        return new BigDecimal(stringValue());
    }

    public String stringValue() {
        return state+"";
    }

    public String getKey() {
        return key;
    }

    public static Map getMap(String key){
        Map<String, BusinessStatus> statusMap = new HashMap<>();
        for (BusinessStatus status : values()) {
            if(status.getKey().equals(key)){
                statusMap.put(status.stringValue(), status);
            }
        }
        return statusMap;
    }

    @Override
    public String toString() {
        return text;
    }
}
