package com.example.apublic.myretrofitlibrary;

import android.content.Context;
import android.util.Log;

import com.example.retrofitlibrary.Retrofit.ApiException;
import com.example.retrofitlibrary.Retrofit.ExceptionHandle;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.ObservableEmitter;


/**
 * 网络请求基类
 * Created by Administrator on 2017/8/18.
 */

public abstract class BaseSubscriber<T> implements ObservableEmitter {
    private Context context;


    public  BaseSubscriber(Context context) {
        this.context = context;
    }



    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable e) {
        Log.w("Subscriber onError", e);
        if (e instanceof ApiException){
            ApiException  exception = (ApiException) e;
            ExceptionHandle exceptionHandle=new ExceptionHandle(context,exception);
            exceptionHandle.handle();
            return;
        }
//        MyToast.CirToastshow(MyApplication.getContext(),MyApplication.getContext().getString(R.string.network_error));
//        MyToast.CirToastshow(MyApplication.getContext(),"访问出错："+e);
        Log.i("错误信息",e+"");
    }

}
