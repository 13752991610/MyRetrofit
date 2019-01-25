package com.example.retrofitlibrary.entity.resultbean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/8/15.
 */

public class ResultBean<T> implements Serializable {
    public static final int SUCCESS=200;//请求成功
    public static  int EXCEPTION_CODE=506;//TOKEN过期
    public static final int PHONE_ERROR=600;//TOKEN过期
    private int status;
    private String message;
    private T datas;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getDatas() {
        return datas;
    }

    public void setDatas(T datas) {
        this.datas = datas;
    }
}
