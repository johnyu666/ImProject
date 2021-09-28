package cn.johnyu.client.manager.service;

import cn.johnyu.client.manager.pojo.Client;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class ClientServiceImpl implements ClientService{

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    public String storeClientAndCreateToken(Client client){
        ObjectMapper mapper=new ObjectMapper();
        String  token=UUID.randomUUID().toString().split("-")[4];
        
        try {
            redisTemplate.opsForHash().put("clients",token,mapper.writeValueAsString(client));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return  token;
    }


}
