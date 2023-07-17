package com.mrwan;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisDataException;

/**
 * @ClassName Service
 * @Description: TODO
 * @Author: ice_wan@msn.cn
 */
public class Service {
    private String id;  // 用户级别
    private int num;    // 操作次数

    public Service(String id, int num) {
        this.id = id;
        this.num = num;
    }

    /**
     * 权限控制单元
     */
    public void service() {
        Jedis jedis = JedisUtils.getJedis();
        String value = jedis.get("USERID:" + id);
        // 判断这个 Key 是否存在
        try {
            if (value == null) {    // 不过不存在，则新建这个 key, 设置时效
                jedis.setex("USERID:" + id, 10, Long.MAX_VALUE - num + "");
            } else {
                // 如果存在，先自增，再执行下载
                Long val = jedis.incr("USERID:" + id);
                download(id, num - (Long.MAX_VALUE - val));
            }
        } catch (JedisDataException e) {
            System.out.println(Thread.currentThread().getName() + " >>> 下载已经到达次数上限，请升级会员级别");
            return;
        } finally {
            jedis.close();
        }
    }

    //业务操作
    public void download(String id, Long val) {
        System.out.println("用户:" + id + " 下载行第" + val + "次");
    }
}