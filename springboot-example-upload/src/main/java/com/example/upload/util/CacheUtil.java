package com.example.upload.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CacheUtil {

    private static Map<String,Boolean> md5Map = new ConcurrentHashMap<String,Boolean>();


    public static void add(String md5){
        md5Map.put(md5,true);
    }

    public static boolean containsKey(String key){
        return md5Map.containsKey(key);
    }

    public static void clear(){
        md5Map.clear();
    }
}
