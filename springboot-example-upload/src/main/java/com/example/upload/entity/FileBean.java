package com.example.upload.entity;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class FileBean {

    private static AtomicInteger atomic = new AtomicInteger(0);

    private String name;
    private int chunks;
    private ConcurrentHashMap<Integer, Boolean> map;
    private String md5;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getChunks() {
        return chunks;
    }

    public void setChunks(int chunks) {
        this.chunks = chunks;
    }

    public ConcurrentHashMap<Integer, Boolean> getMap() {
        return map;
    }

    public void setMap(ConcurrentHashMap<Integer, Boolean> map) {
        this.map = map;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public FileBean(String name, int chunks, String md5) {
        this.name = name;
        this.chunks = chunks;
        this.md5 = md5;
        map = new ConcurrentHashMap<>();
        for(int i = 0; i < chunks; i++) {
            map.put(i, false);
        }
    }

    public void addIndex(int chunk) {
        atomic.getAndIncrement();
    }

    public boolean isLoadComplate(int chunks) {
//        System.out.println("atomic：" + atomic.get() +"，chunks：" + chunks);
        if(atomic.get() == chunks){
            return true;
        }else{
            return false;
        }
    }
}
