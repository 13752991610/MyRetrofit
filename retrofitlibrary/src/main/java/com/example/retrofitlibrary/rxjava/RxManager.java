package com.example.retrofitlibrary.rxjava;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

import io.reactivex.Observable;



/**
 * Rxjava管理
 * Created by Administrator on 2017/9/4.
 */

public class RxManager {
    public RxBus mRxBus = RxBus.getInstance(); //拿到rxBus
    //管理rxbus订阅
    private Map<String, Observable<?>> mObservables = new HashMap<>();

    /**
     * RxBus订阅
     * @param eventName
     * @param action1
     */
    public <T>void on(String eventName, Future<T> action1) {

    }

    /**
     * 单个presenter生命周期结束，取消订阅和所有rxbus观察
     */
    public void clear() {
        for (Map.Entry<String, Observable<?>> entry : mObservables.entrySet()) {
            mRxBus.unregisterAll();// 移除rxbus观察
        }
        mObservables.clear();
        mObservables = null;
        mRxBus = null;
    }

    /**
     * 发送RxBus
     */
    public void post(Object msg) {
        mRxBus.post(msg);
    }
}
