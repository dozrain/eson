package com.rain.utils;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author rain
 * @date    2017/11/15
 */
@SuppressWarnings("serial")
public class Result<T> implements Serializable {
    public Result(){}

    public Result(int code){
        this.code = code;
        this.msg = "";
    }

    public Result(int code, Object data){
        this.code = code;
        this.msg = "";
        this.data = data;
    }

    public Result(int code, Object data, int page, long total){
        this.code = code;
        this.msg = "";
        this.data = data;
        this.page = page;
        this.total = total;
    }


    private int code;
    private int page = 1;
    private long total = 0;
    private String msg = "";
    private String[] msgData = new String[]{};
    private Object data = new ArrayList<>();

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
        this.msg = "";
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
        if(true){
//            this.total = ((Page) data).getTotal();
//            this.page = ((Page) data).getPageNum();
        }else if(true){
//            this.total = ((PageInfo) data).getTotal();
//            this.page = ((PageInfo) data).getPageNum();
        }
        if(this.page == 0){
            this.page = 1;
        }
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    public void setTotal(long total) {
        this.total = total;
    }

    public String[] getMsgData() {
        return msgData;
    }

    public void setMsgData(String[] msgData) {
        this.msgData = msgData;
    }

    public static Result buildSuccess(){
        return  new Result();
    }

    public static Result buildError(){
        return  new Result();
    }
}

