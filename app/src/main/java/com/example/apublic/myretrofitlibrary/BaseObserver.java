package com.example.apublic.myretrofitlibrary;

import android.content.Context;
import android.util.Log;

import com.example.retrofitlibrary.Retrofit.ApiException;
import com.example.retrofitlibrary.Retrofit.ExceptionHandle;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class BaseObserver<T> implements Observer<T> {
    private Context context;

    public BaseObserver(Context context) {
        this.context = context;
    }

    @Override
    public void onSubscribe(Disposable d) {
        d.dispose();
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
        Log.i("错误信息",e+"");
    }

    @Override
    public void onComplete() {

    }
}
