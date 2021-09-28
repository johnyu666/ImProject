package server.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import redis.clients.jedis.Jedis;
import server.domain.Client;

public class ClientService {
    private String redisUrl;
    private int redisPort;
    private String clientsRepo;
    private Jedis jedis;

    public ClientService(String redisUrl, int redisPort, String clientsRepo) {
        this.redisUrl = redisUrl;
        this.redisPort = redisPort;
        this.clientsRepo = clientsRepo;
        jedis=new Jedis(redisUrl,redisPort);
    }
    public void close(){
        this.jedis.close();
    }

    public void initClients(){
        jedis.hset(this.redisUrl,"6f89","{\"id\":1,\"loginName\":\"johnyu\",\"password\":\"123\",\"nickName\":\"johnyu\"}");
        jedis.hset(this.redisUrl,"f876","{\"id\":2,\"loginName\":\"tom\",\"password\":\"123\",\"nickName\":\"tomli\"}");
        jedis.hset(this.redisUrl,"2cd3","{\"id\":3,\"loginName\":\"mike\",\"password\":\"123\",\"nickName\":\"mikeliu\"}");
    }

    public  Client loadClient(String token) {
        Client client = null;
        String info = jedis.hget(this.clientsRepo, token);
        if (info != null) {
            try {
                client = new ObjectMapper().readValue(info, Client.class);
            } catch (JsonProcessingException e) {
                System.out.println("利用token从redis中读到数据进行解析时发生错误！");
            }
        } else {
            System.out.println("无法从redis中获取用户信息");
        }

        return client;
    }
    public void logout(String token){
       long m= jedis.hdel("clients",token);
    }

}
