package cn.johnyu.client.manager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = ClientManagerApp.class)
@RunWith(SpringRunner.class)
public class MyTest1 {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    private MyService service;
    @Test
    public void test1(){
        User user=new User();
        user.setId(100);
        user.setUname("john");
        redisTemplate.opsForValue().set("user1",user);
    }
    @Test
    public void test2(){
        User obj=(User)redisTemplate.opsForValue().get("user1");
        System.out.println(obj.getUname());
    }

    @Test
    public void test3(){
        String ss=service.xx();
    }

}
