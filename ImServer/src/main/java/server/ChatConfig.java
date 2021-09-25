package server;

public class ChatConfig {
    private int port=9000;
    private String redisUrl="athyu.com";
    private int redisPort=6379;
    private String clientsRepo="clients";

    public int getPort() {
        return port;
    }

    public String getRedisUrl() {
        return redisUrl;
    }

    public int getRedisPort() {
        return redisPort;
    }

    public String getClientsRepo() {
        return clientsRepo;
    }
}
