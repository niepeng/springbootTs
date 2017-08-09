package com.niepeng.springbootts;
/**
 * Created by lsb on 17/5/18.
 */


import com.alibaba.fastjson.JSONObject;
import com.niepeng.springbootts.common.RedisManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisConnectionUtils;
import org.springframework.data.redis.core.ScanOptions;
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

  @Test
  public void testAddKeys() {
    for (int i = 0; i < 100; i++) {
      stringRedisTemplate.opsForValue().set("key" + i, String.valueOf(i), 1000, TimeUnit.SECONDS);
    }
  }

  @Test
  public void  testKeysAll() {
    String pattern = "*";
    long start = System.currentTimeMillis();
    Set<String> set = stringRedisTemplate.keys(pattern);
    long end = System.currentTimeMillis();
    System.out.println("size=" + set.size() + ",dis=" + (end-start));
  }

  @Test
  public void testSelectKeyByPage() {
    try {
      String pattern = "key38";
      int pageNum = 1;
      int pageSize = 10;
      List<String> list = findKeysForPage(pattern, pageNum, pageSize);
      for(String s : list) {
          System.out.println("===>" + s);
      }
    } catch(Exception e) {
        e.printStackTrace();
    }
  }


  /**
   *
   * @param patternKey
   * @param pageNum  从1开始
   * @param pageSize  每页数量
   * @return
   */
  public List<String> findKeysForPage(String patternKey, int pageNum, int pageSize) {
    ScanOptions options = ScanOptions.scanOptions().match(patternKey).build();
    RedisConnectionFactory factory = stringRedisTemplate.getConnectionFactory();
    RedisConnection rc = factory.getConnection();
    Cursor<byte[]> cursor = rc.scan(options);
    List<String> result = new ArrayList<String>(pageSize);
    int tmpIndex = 0;
    int startIndex = (pageNum - 1) * pageSize;
    int end = pageNum * pageSize;
    while (cursor.hasNext()) {
      if (tmpIndex >= startIndex && tmpIndex < end) {
        result.add(new String(cursor.next()));
        tmpIndex++;
        continue;
      }

      // 获取到满足条件的数据后,就可以退出了
      if(tmpIndex >=end) {
          break;
      }

      tmpIndex++;
      cursor.next();
    }

//    try {
//      cursor.close();
//    } catch (Exception e) {
//      e.printStackTrace();
//    }

    try {
        RedisConnectionUtils.releaseConnection(rc, factory);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return result;
  }

  }