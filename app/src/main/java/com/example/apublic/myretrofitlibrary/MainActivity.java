package com.example.apublic.myretrofitlibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofitlibrary.Retrofit.RetrofitUtil;
import com.example.retrofitlibrary.Retrofit.SchedulersTransformer;
import com.example.retrofitlibrary.entity.resultbean.ResultBean;
import com.example.retrofitlibrary.rxjava.RxBus;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


public class MainActivity extends AppCompatActivity {
    private TextView tv;
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.sample_text);
        button=findViewById(R.id.sample_btn);
        final JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("key","");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Map<String, Object> map = Sign_param.sign_map(jsonObject);
        RetrofitUtil.getService(ApiService.class).Help(map)
                .compose(SchedulersTransformer.schedulersTransformer())
                .subscribe(new BaseObserver<ResultBean>(MainActivity.this) {

                    @Override
                    public void onNext(ResultBean resultBean) {
                        Toast.makeText(MainActivity.this,"请求成功"+resultBean.getStatus(),Toast.LENGTH_SHORT).show();
                        Log.i("请求结果","请求成功"+resultBean.getStatus());
                        tv.setText(resultBean.getDatas()+"");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(MainActivity.this,"请求失败"+e,Toast.LENGTH_SHORT).show();
                        Log.i("请求结果","请求失败"+e);
                        tv.setText(e+"");
                    }

                });

        //接受RxBus
        RxBus.getInstance().register().subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                Toast.makeText(MainActivity.this,"接受到消息"+o,Toast.LENGTH_SHORT).show();
            }
        });


        //测试rxBus
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxBus.getInstance().post("这是心消息");
            }
        });


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.getInstance().unregisterAll();
    }
}