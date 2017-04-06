package com.jiaokaokeji.gaochuangkeji.login.pojo;

/**
 * Created by Jingsheng Liang on 2017/3/31.
 */

public class ResultBean {
    /**
     * resultCode : 500
     * message : 注册失败
     * data : 此用户名已存在
     */

    private int resultCode;
    private String message;
    private String data;

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
