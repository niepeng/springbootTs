package com.niepeng.springbootts.config;
/**
 * Created by lsb on 17/5/16.
 */


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author 聂鹏
 * @version 1.0
 * @date 17/5/16
 */
@Configuration
@MapperScan("com.niepeng.springbootts.dao")
// 启动注解事务管理
@EnableTransactionManagement
// 开启定时任务
@EnableScheduling
public class AppConfig {




}