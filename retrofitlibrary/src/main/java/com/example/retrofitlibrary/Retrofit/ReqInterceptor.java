package com.example.retrofitlibrary.Retrofit;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ReqInterceptor implements Interceptor {
    /*
     * OKhttp缓存拦截器
     */
   /*
   1.  noCache  不使用缓存，全部走网络
   2.  noStore   不使用缓存，也不存储缓存
   3.  onlyIfCached 只使用缓存
   4.  maxAge  设置最大失效时间，失效则不使用 需要服务器配合
   5.  maxStale 设置最大失效时间，失效则不使用 需要服务器配合 感觉这两个类似 还没怎么弄清楚，清楚的同学欢迎留言
   6.  minFresh 设置有效时间，依旧如上
   7.  FORCE_NETWORK 只走网络
   8.  FORCE_CACHE 只走缓存
   */
    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request  =  chain.request()
                .newBuilder()
                .addHeader("X-APP-TYPE","android")
                .build();
        Response response = chain.proceed(request);
        return response;
    }
}
