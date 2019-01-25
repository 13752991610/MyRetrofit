package com.example.retrofitlibrary.Retrofit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.example.retrofitlibrary.entity.resultbean.ResultBean;


/**
 * Created by Administrator on 2017/9/25.
 */

public class ExceptionHandle {
    private Context context;
    private ApiException apiException;

    public ExceptionHandle(Context context, ApiException apiException) {
        this.context = context;
        this.apiException = apiException;
    }

    public ExceptionHandle(Activity activity, ApiException apiException) {
        this.context = context;
        this.apiException = apiException;
    }

    public void handle(){
        if (apiException.getmErrorCode()== ResultBean.EXCEPTION_CODE){//异常

        }else {

        }
    }
}
