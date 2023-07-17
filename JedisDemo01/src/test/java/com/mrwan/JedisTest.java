package com.mrwan;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @Classname JedisTest
 * @Description: TODO
 * @Author: ...
 */
public class JedisTest {

    @Test
    public  void test01(){
        //连接Redis
        Jedis jedis = new Jedis("127.0.0.1", 6379);

        //针对数据设置或者获取

        jedis.set("infoFromJava","Jedis");

        String infoFromJava = jedis.get("infoFromJava");

        System.out.println("infoFromJava = " + infoFromJava);

        //关闭Redis
        jedis.close();
    }

    @Test
    public  void test02(){
        //连接Redis
        Jedis jedis = new Jedis("127.0.0.1", 6379);

        //针对数据设置或者获取
        Long rpush = jedis.rpush("names", "Andy", "Jack", "Lucy", "Jim");

        System.out.println("rpush = " + rpush);

        List<String> names = jedis.lrange("names", 0, -1);

        for (String name : names) {
            System.out.println(name);
        }

        //关闭Redis
        jedis.close();
    }
}
