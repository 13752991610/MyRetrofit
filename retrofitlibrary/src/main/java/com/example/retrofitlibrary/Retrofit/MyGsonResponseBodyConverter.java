package com.example.retrofitlibrary.Retrofit;

import com.example.retrofitlibrary.entity.resultbean.ResultBean;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Converter;

import static okhttp3.internal.Util.UTF_8;

/**
 * 自定义MyGsonResponseBodyConverter
 * Created by Administrator on 2017/9/25.
 */

public class MyGsonResponseBodyConverter<T> implements Converter<ResponseBody,T> {
    private Gson gson;
    private TypeAdapter<T> adapter;

    public MyGsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        ResultBean resultBean = gson.fromJson(response, ResultBean.class);
        if (resultBean.getStatus()== ResultBean.EXCEPTION_CODE){
            value.close();
            throw new ApiException(resultBean.getStatus(),resultBean.getMessage());
        }
        MediaType contentType = value.contentType();
        Charset charset = contentType != null ? contentType.charset(UTF_8) : UTF_8;
        InputStream inputStream = new ByteArrayInputStream(response.getBytes());
        Reader reader = new InputStreamReader(inputStream, charset);
        JsonReader jsonReader = gson.newJsonReader(reader);
        try {
            return adapter.read(jsonReader);
        } finally {
            value.close();
        }
    }
}