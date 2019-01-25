package com.example.apublic.myretrofitlibrary;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


/**
 * 签名
 * Created by Administrator on 2017/1/9.
 */

public class Sign_param {

    public static Context contexts;
    public Sign_param(Context context) {
        this.contexts = context;
    }

    /**
     * 签名方式一
     * @param jsonObject
     * @return
     */
    public static HashMap<String,Object> sign_map(JSONObject jsonObject){
        try {
            jsonObject.put("plat","张大伯商城");
            jsonObject.put("source","android");
            jsonObject.put("userPlat","张大伯商城");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String data= String.valueOf(jsonObject);
        Log.i("数据源", String.valueOf(jsonObject));
//        String basedata = "";
//        try {
//            PublicKey publicKey = MyRSAUtil.loadPublicKey(Public_key_RSA.RSAKey);
//            Cipher cipher=MyRSAUtil.recipher(publicKey);
//            byte[] encryptBytes= RSAUtil.divideEnKey(data.getBytes(),cipher);
//            Log.i("加密数据", String.valueOf(encryptBytes));
//            Log.i("加密数据长度", String.valueOf(encryptBytes.length));
//            basedata= Base64.encode(encryptBytes);
//            Log.i("加密数据", String.valueOf(basedata));
//            Log.i("转换之后长度", String.valueOf(basedata.length()));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        HashMap<String,Object> map=new HashMap<>();
        map.put("plat","android");
//        map.put("appsecret",Base64.encode(data.getBytes()));
        map.put("appsecret",  android.util.Base64.encodeToString(data.getBytes(), android.util.Base64.NO_WRAP));
        map.put("sign",0);
//        Log.i("平台参数","android");
        Log.i("加密参数",android.util.Base64.encodeToString(data.getBytes(), android.util.Base64.NO_WRAP));
//        Log.i("签名参数", String.valueOf(0));
        return map;
    }




}
