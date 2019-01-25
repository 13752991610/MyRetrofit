package com.example.retrofitlibrary.Retrofit;

import android.Manifest;
import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.util.concurrent.TimeUnit;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * 网络请求封装
 * Created by Administrator on 2017/8/11.
 */

public class RetrofitUtil {
    /**
     * 服务器地址
     */
//    private static final String baseUrl  ="http://192.168.43.165:13001/";
    private static final String baseUrl  ="http://39.105.72.147:13001/";
//    private static final String baseUrl  ="http://192.168.43.165:13001/";
//    private static final String baseUrl  ="http://39.105.43.227:13001/";

    private static final int DEFAULT_TIME_OUT = 20;//超时时间
    private static final int DEFAULT_WRITE_TIME_OUT = 20;//超时时间
    private static final int DEFAULT_READ_TIME_OUT = 20;//超时时间
    private static OkHttpClient okHttpClient;
    private static Context mContext;
    private static RetrofitUtil mInstance;
    private static Retrofit retrofit;

    /**
     * 获取Retrofit单例
     * @return
     */
    public static RetrofitUtil getInstance(){
        if (mInstance == null){
            synchronized (RetrofitUtil.class){
                mInstance = new RetrofitUtil();
            }
        }
        return mInstance;
    }

    private Retrofit getRetrofit(Context context, String baseUrl) {
        /*
         * 设置OKhttp
         */
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LogInterceptor())//添加打印拦截器
                .addInterceptor(new ReqInterceptor())//添加请求拦截器
                .addNetworkInterceptor(new LogInterceptor())
                .addNetworkInterceptor(new ReqInterceptor())
                .connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)//设置请求超时时间
                .writeTimeout(DEFAULT_WRITE_TIME_OUT,TimeUnit.SECONDS)//设置写入超时时间
                .readTimeout(DEFAULT_READ_TIME_OUT,TimeUnit.SECONDS)//设置读取超时时间
                .retryOnConnectionFailure(true)//设置出现错误进行重新连接。
                .build();

        /*
         * 设置Retrofit
         */
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .serializeNulls()
                .registerTypeAdapterFactory(new SafeTypeAdapterFactory())
                .registerTypeAdapter(String.class,new StringConverter())
                .serializeNulls()
                .create();
        // 初始化Retrofit
       return retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(MyGsonConverterFactory.create(gson))
               .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build();

    }


    //返回一个泛型类
    public static  <T>T getService(Class<T> service){
        return getInstance().getRetrofit(mContext,baseUrl).create(service);
    }

//    public static ApiService  getApiService(){
//        return getInstance().getRetrofit(mContext,baseUrl).create(ApiService.class);
//    }

}
