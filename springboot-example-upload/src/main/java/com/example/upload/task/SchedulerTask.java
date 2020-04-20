package com.example.upload.task;

import com.example.upload.util.CacheUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulerTask {

    /**
     * 每30秒清除缓存
     */
    @Scheduled(cron="0 0 2 * * ?")
    private void process(){
        CacheUtil.clear();
    }
}
