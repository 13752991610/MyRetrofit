package com.example.retrofitlibrary.Retrofit;

import android.util.Log;

import java.io.IOException;
import java.util.Locale;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

public class LogInterceptor implements Interceptor {
    private String TAG = "okhttp";
    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        Log.w(TAG,"request:" + request.toString());
        long t1 = System.nanoTime();
        okhttp3.Response response = chain.proceed(chain.request());
        long t2 = System.nanoTime();
        Log.w(TAG,String.format(Locale.getDefault(), "Received response for %s in %.1fms%n%s",
                response.request().url(), (t2 - t1) / 1e6d, response.headers()));
        okhttp3.MediaType mediaType = response.body().contentType();
        String content = response.body().string();
        Log.w(TAG, "response body:" + content);
        return response.newBuilder()
                .body(okhttp3.ResponseBody.create(mediaType, content))
                .build();
    }


//    /**
//     * 日志拦截器
//     */
//    private class LogInterceptor implements Interceptor {
//        @Override
//        public Response intercept(Interceptor.Chain chain) throws IOException {
//            Request request  =  chain.request();
//            String url = request.url().toString();
//            String params = requestBodyToString(request.body());
//            Response response = chain.proceed(request);
//            String responseString = JsonHandleUtils.jsonHandle(response.body().string());
//            String time = DateUtils.getNowDateFormat(DateUtils.DATE_FORMAT_2);
//            String log = "\n\n*****请求时间*****:\n" + time+"\n*******路径*******:\n" + url + "\n*******参数*******:\n" + params +  "\n*******报文*******:\n"  + responseString+"\n \n";
//            Log.d(TGA,log);
//            return chain.proceed(request);
//        }
//    }

//    private  String requestBodyToString(final RequestBody request) {
//        try {
//            final RequestBody copy = request;
//            final Buffer buffer = new Buffer();
//            if (copy != null){
//
//                copy.writeTo(buffer);
//            }
//            else{
//                return "";
//            }
//            return buffer.readUtf8();
//        } catch (final IOException e) {
//            return "did not work";
//        }



}
