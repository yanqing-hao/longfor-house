package com.lf.commons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis工具类
 */
@Component
public class RedisUtil {
        
    // 私有化 redis操作类
    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 写入缓存
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 写入缓存设置时效时间
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 单独设置key 的失效时间
     * @param key
     * @param expireTime
     * @return
     */
    public boolean expire(final String key,Long expireTime){
        boolean result = false;
        try {
            if(exists(key)) {
                redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 批量删除key
     * @param pattern
     */
    public void removePattern(final String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0) {
            redisTemplate.delete(keys);
        }
    }
    /**
     * 删除对应的value
     * @param key
     */
    public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }
    /**
     * 判断缓存中是否有对应的value
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }
    /**
     * 读取缓存
     * @param key
     * @return
     */
    public Object get(final String key) {
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        Object result = operations.get(key);
        return result;
    }
    /**
     * 哈希 添加
     * @param key
     * @param hashKey
     * @param value
     */
    public void hmSet(String key, Object hashKey, Object value){
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        hash.put(key,hashKey,value);
    }

    /**
     * 哈希 添加Map
     * @param key
     * @param value
     */
    public void hmSet(String key, Map value){
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        hash.putAll(key,value);
    }

    /**
     * 哈希获取数据
     * @param key
     * @param hashKey
     * @return
     */
    public Object hmGet(String key, Object hashKey){
        HashOperations<String, Object, Object>  hash = redisTemplate.opsForHash();
        return hash.get(key,hashKey);
    }

    /**
     * 哈希获取数据 返回Map<String,Object>
     * @param key
     * @return
     */
//    public Map<String,Object> hmGet(String key){
//
//    }

    /**
     * 列表添加
     * @param k
     * @param v
     */
    public void lPush(String k,Object v){
        ListOperations<Serializable, Object> list = redisTemplate.opsForList();
        list.rightPush(k,v);
    }

    /**
     * 列表添加List
     * @param k
     * @param v
     */
    public void lPush(String k,List v){
        ListOperations<Serializable, Object> list = redisTemplate.opsForList();
        list.rightPushAll(k,v);
    }

    /**
     * 列表获取
     * @param k
     * @param start
     * @param end
     * @return
     */
    public List<Object> lRange(String k, long start, long end){
        ListOperations<Serializable,Object> list = redisTemplate.opsForList();
        return list.range(k,start,end);
    }

    /**
     * 集合添加
     * @param key
     * @param value
     */
    public void add(String key,Object value){
        SetOperations<Serializable, Object> set = redisTemplate.opsForSet();
        set.add(key,value);
    }

    /**
     * 集合添加set
     * @param key
     * @param value
     */
    public void add(String key,Object... value){
        SetOperations<Serializable, Object> set = redisTemplate.opsForSet();
        set.add(key,value);
    }

    /**
     * 集合获取
     * @param key
     * @return
     */
    public Set<Object> setMembers(String key){
        SetOperations<Serializable, Object> set = redisTemplate.opsForSet();
        return set.members(key);
    }

    /**
     * 有序集合添加
     * @param key
     * @param value
     * @param scoure
     */
    public void zAdd(String key,Object value,double scoure){
        ZSetOperations<Serializable, Object> zset = redisTemplate.opsForZSet();
        zset.add(key,value,scoure);
    }

    /**
     * 有序集合添加 set
     * @param key
     * @param value
     */
    public void zAdd(String key,Set value){
        ZSetOperations<Serializable, Object> zset = redisTemplate.opsForZSet();
        zset.add(key,value);
    }

    /**
     * 有序集合获取
     * @param key
     * @param scoure
     * @param scoure1
     * @return
     */
    public Set<Object> rangeByScore(String key,double scoure,double scoure1){
        ZSetOperations<Serializable, Object> zset = redisTemplate.opsForZSet();
        return zset.rangeByScore(key, scoure, scoure1);
    }
}