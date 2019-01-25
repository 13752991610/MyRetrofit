package com.example.apublic.myretrofitlibrary;

import com.example.retrofitlibrary.entity.resultbean.ResultBean;

import java.util.Map;


import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface ApiService {
    /**
     * 帮助指南
     * @param maps
     * @return
     */

    @POST("article/querySearch.do")
    Observable<ResultBean> Help(@QueryMap Map<String, Object> maps);

}
