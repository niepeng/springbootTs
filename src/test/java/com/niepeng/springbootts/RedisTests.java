package com.niepeng.springbootts;
/**
 * Created by lsb on 17/5/18.
 */


import com.alibaba.fastjson.JSONObject;
import com.niepeng.springbootts.common.RedisManager;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author 聂鹏
 * @version 1.0
 * @email lisenbiao@51huadian.cn
 * @date 17/5/18
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes= App.class)
public class RedisTests {

  @Autowired
  private RedisManager redisManager;

  @Autowired
  private StringRedisTemplate stringRedisTemplate;

  @Test
  public void test() throws Exception {
    String value = "helloworld1";
    String key = "key";
    stringRedisTemplate.opsForValue().set(key, value, 1000, TimeUnit.SECONDS);
    Assert.assertEquals(value, stringRedisTemplate.opsForValue().get(key));
  }

  }