package com.example.elasticsearch.util;

import com.alibaba.fastjson.JSONObject;

public class Result {

    public static Object success(){
        JSONObject result = new JSONObject();
        result.put("code",200);
        result.put("msg","");
        return result;
    }

    public static Object success(Object data){
        JSONObject result = new JSONObject();
        result.put("code",200);
        result.put("msg","");
        result.put("data",data);
        return result;
    }

    public static Object error(){
        JSONObject result = new JSONObject();
        result.put("code",500);
        result.put("msg","请求失败");
        return result;
    }

}
