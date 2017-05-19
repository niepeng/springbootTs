package com.niepeng.springbootts;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    // 开启每5秒执行一次
//    @Scheduled(fixedRate = 5000)
//    public void reportCurrentTime() {
//        System.out.println("当前时间：" + dateFormat.format(new Date()));
//    }

}