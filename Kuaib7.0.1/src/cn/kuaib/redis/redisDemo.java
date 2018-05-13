package cn.kuaib.redis;
/*
* 测试连接阿里云服务器，后期引入redis查询用户及文件等
* */

import redis.clients.jedis.Jedis;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class redisDemo {
    public static void main(String[] args) {
        System.out.println("==============================阿里云");
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("*");
        jedis.auth("*");
        System.out.println("连接成功");
        //查看服务是否运行
        System.out.println("服务正在运行: "+jedis.ping());

        System.out.println("==============================获取字符串");
        jedis.set("hello", "world");
        // 获取存储的数据并输出
        System.out.println("redis 存储的字符串为: "+ jedis.get("hello"));

        System.out.println("==============================获取字符串列表");
        //存储数据到列表中
        jedis.lpush("site-list", "Runoob");
        jedis.lpush("site-list", "Google");
        jedis.lpush("site-list", "Taobao");
        // 获取存储的数据并输出
        List<String> list = jedis.lrange("site-list", 0 ,2);
        for(int i=0; i<list.size(); i++) {
            System.out.println("列表项为: "+list.get(i));
        }

        System.out.println("==============================获取所有数据库所有的建值");
        Set<String> keys = jedis.keys("*");
        Iterator<String> it=keys.iterator() ;
        while(it.hasNext()){
            String key = it.next();
            System.out.println(key);
        }

        System.out.println("==============================本地");
        //连接本地的 Redis 服务
        Jedis jedisLocal = new Jedis("*");
        jedisLocal.auth("*");
        System.out.println("连接成功");
        //查看服务是否运行
        System.out.println("服务正在运行: "+jedis.ping());

    }

}
