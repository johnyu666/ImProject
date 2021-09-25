package server;

import com.fasterxml.jackson.databind.ObjectMapper;
import server.domain.Chater;
import server.service.ClientService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;

public class ChatContext implements Runnable {
    private static ChatConfig chatConfig;

    private static ChatContext chatContext;
    private ServerSocket serverSocket;
    private ObjectMapper mapper=new ObjectMapper();
    private HashSet<Chater> chaters=new HashSet<>();
    private ClientService clientService;
    static {
        chatContext = new ChatContext();
    }

    public static ChatContext config() {
        chatConfig = new ChatConfig();
        return chatContext;
    }

    public ChatContext build() {
        clientService=new ClientService(chatConfig.getRedisUrl(),chatConfig.getRedisPort(),chatConfig.getClientsRepo());
        //do other init work
        try {
            serverSocket = new ServerSocket(chatConfig.getPort());

        } catch (IOException exception) {
            System.out.println("聊天服务启动失败，端口：" + chatConfig.getPort());
        }
        return chatContext;
    }

    public HashSet<Chater> getChaters(){
        return this.chaters;
    }

    public ClientService getClientService() {
        return clientService;
    }

    @Override
    public void run() {
        //一个聊天室的线程，对应一个ChatContext
        while (true) {
            //接收到客户端的一个TCP连接
            try {
                final  Socket socket = serverSocket.accept();
                new Chater(socket,chatContext).start();
            } catch (IOException exception) {
                //serverSocket.accept()的异常处理
                System.out.println("聊天服务器监听服务发生异常");
            }
        }

    }

    public void start() {
        new Thread(chatContext).start();
    }
}
