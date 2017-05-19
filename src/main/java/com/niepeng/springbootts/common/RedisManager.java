package com.niepeng.springbootts.common;

import com.alibaba.fastjson.JSONObject;
import com.niepeng.springbootts.exception.MyException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Redis管理
 * 其中:RedisManager用来存放对象
 * StringRedisTemplate 用来存放字符串
 */
@Slf4j
@Service
public class RedisManager {


    @Autowired
    // stringRedisTemplate.opsForValue().set(xx,xx,xx)
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 1、查询redis 数据库
     *
     * @param keyEnum 查询关键字
     * @param clazz   指定返回List内存放的对象类型
     * @param <T>     返回对象类型,集合泛型
     * @return List<T>返回对象集合
     */
    public <T> List<T> queryListByKey(final String keyEnum, final Class<T> clazz) {
        log.debug("queryListByKey request:{}", keyEnum);
        try {
            String resultStr = queryObjectByKey(keyEnum);
            if (StringUtils.isBlank(resultStr)) {
                return null;
            }

            List<T> value = JSONObject.parseArray(resultStr, clazz);

            log.debug("queryListByKey response:{}", value.toString());
            return value;
        } catch (Exception e) {
            log.warn("redis error:{}", e.getMessage());
        }
        return null;
    }

    /**
     * 2、插入redis 数据库
     *
     * @param obj     保存对象
     * @param keyEnum 关键字
     * @return 对象类型, 泛型
     */
    public boolean insertObject(final Object obj, final String keyEnum) {
        return insertObject(obj, keyEnum, 0L);
    }

    /**
     * 3、插入redis 数据库,设置有效期
     *
     * @param obj     保存对象
     * @param keyEnum 关键字
     * @param timeout 有效期（毫秒）
     * @return 对象类型, 泛型
     */
    public boolean insertObject(final Object obj, final String keyEnum, final long timeout) {
        log.debug("insertObject request:key={},obj={}", keyEnum, obj.toString());
            if(obj instanceof String) {
                // 直接使用 stringRedisTemplate.opsForValue().set(xx,xx,xx), 不要调用该方法
                throw new MyException("string value use stringRedisTemplate, if use this method, get from redis value is not equals put value, because JSONObject.toJSONString add \" in before end.  ");
            }
        try {
            final String value = JSONObject.toJSONString(obj);
            boolean result = stringRedisTemplate.execute(new RedisCallback<Boolean>() {
                @Override
                public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                    byte[] redisKey = stringRedisTemplate.getStringSerializer().serialize(keyEnum);
                    byte[] redisValue = stringRedisTemplate.getStringSerializer().serialize(value);
                    connection.set(redisKey, redisValue);
                    if (timeout > 0) {
                        stringRedisTemplate.expire(keyEnum, timeout, TimeUnit.MILLISECONDS);
                    }
                    return true;
                }
            });

            log.debug("insertObject response：{}", result);
            return result;
        } catch (Exception e) {
            log.warn("redis error:{}", e.getMessage());
        }
        return false;

    }

    /**
     * 4、删除redis 保存对象
     *
     * @param keyEnum 查询关键字
     * @return 删除结果
     */
    public boolean deleteObject(final String keyEnum) {
        log.debug("deleteObject request:key={}", keyEnum);
        try{
            Long result = stringRedisTemplate.execute(new RedisCallback<Long>() {
                @Override
                public Long doInRedis(RedisConnection connection) throws DataAccessException {
                    byte[] redisKey = stringRedisTemplate.getStringSerializer().serialize(keyEnum);
                    return connection.del(redisKey);
                }
            });

            log.debug("deleteObject response：{}", result);
            return result > 0;
        } catch (Exception e) {
            log.warn("redis error:{}", e.getMessage());
        }
        return false;

    }

    /**
     * 5、更新 redis
     *
     * @param obj      操作对象
     * @param keyEnums keys数组
     * @return boolean 更新状态
     */
    public boolean modify(final Object obj, final String... keyEnums) {
        for (String key : keyEnums) {
            deleteObject(key);
            insertObject(obj, key);
        }
        return true;
    }

    /**
     * 6、查询redis 数据库
     *
     * @param keyEnum 查询关键字
     * @return String
     */
    public String queryObjectByKey(final String keyEnum) {
        log.debug("queryObjectByKey request:{}", keyEnum);
        //return stringRedisTemplate.opsForValue().get(keyEnum);
        try {
            String resultStr = (String) stringRedisTemplate.execute(new RedisCallback<Object>() {
                @Override
                public String doInRedis(RedisConnection connection) throws DataAccessException {
                    byte[] redisKey = stringRedisTemplate.getStringSerializer().serialize(keyEnum);
                    if (connection.exists(redisKey)) {
                        byte[] value = connection.get(redisKey);
                        // JSONObject.parse(value)
                        return stringRedisTemplate.getStringSerializer().deserialize(value);
                    }
                    return null;
                }
            });

            log.debug("queryObjectByKey response:{}", resultStr);
            return resultStr;
        } catch (Exception e) {
            log.warn("redis error:{}", e.getMessage());
        }
        return null;
    }

    /**
     * 7、查询redis 数据库
     *
     * @param keyEnum 查询关键字
     * @return <T> T
     */
    public <T> T queryObjectByKey(final String keyEnum, final Class<T> clazz) {
        log.debug("queryObjectByKey request:{}", keyEnum);
        try {
            String resultStr = queryObjectByKey(keyEnum);
            if (StringUtils.isBlank(resultStr)) {
                return null;
            }

            T value = JSONObject.parseObject(resultStr, clazz);

            log.debug("queryObjectByKey response:{}", value.toString());
            return value;
        } catch (Exception e) {
            log.warn("redis error:{}", e.getMessage());
        }
        return null;
    }

    /**
     * 清空DB
     * @return
     */
    public boolean flushDB() {
        return stringRedisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection)
                    throws DataAccessException {
                connection.flushDb();
                return true;
            }
        });
    }

}
