package cn.johnyu;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.Map;

public class RedisTest {
    private Jedis jedis;
    @Before
    public void before(){
        jedis=new Jedis("athyu.com",6379);
//        jedis.auth("");
    }
    @Test
    public void testBase(){
        jedis.set("userName","JohnYu123");
        String value=jedis.get("userName");
        System.out.println(value);
    }
    @Test public void testHashMap(){
        jedis.hset("users","1","JohnYu");
        String s=jedis.hget("users","1");
        System.out.println(s);
        Map<String, String> users = jedis.hgetAll("users");
        users.forEach((key,value)->{
            System.out.println(key+"...."+value);
        });
    }
    @Test
    public void testLogin(){
        String info = jedis.hget("clients", "6f89");
        System.out.println(info);
    }

    @After
    public void after(){
        jedis.close();;
    }

}
