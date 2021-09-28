package cn.johnyu.client.manager;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class MyService {
    @Cacheable(value = "tom")
    public String xx(){
        return  "success!!!";
    }
}
