package server.domain;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.ObjectMapper;
import server.ChatContext;
import server.dto.CommonData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class Chater implements Runnable {
    private Client client;
    private Socket socket;
    private BufferedReader reader = null;
    private OutputStream outputStream = null;
    private ChatContext context;

    public Chater(Socket socket, ChatContext context) {
        this.socket = socket;
        this.context = context;
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            outputStream = socket.getOutputStream();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public Client getClient() {
        return this.client;
    }

    public void send(CommonData commonData) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String info = mapper.writeValueAsString(commonData) + "\r\n";
            outputStream.write(info.getBytes());
        } catch (IOException exception) {
            System.out.println(client.getLoginName() + ":数据发送时出现了异常！");
        }
    }

    @Override
    public void run() {
        ObjectMapper mapper = new ObjectMapper();
        boolean running = true;

        while (running) {

            try {
                //阻塞：等待客户端发送信息
                String info = reader.readLine();
                if (info != null) {
                    final CommonData commonData = mapper.readValue(info, CommonData.class);
                    switch (commonData.getCode()) {
                        case 601://进入聊天室的处理代码
                            String token = commonData.getContent();
                            Client client = context.getClientService().loadClient(token);
                            if (client != null) {
                                this.client = client;
                                context.getChaters().add(this);
                                System.out.println(client.getLoginName() + ":进入了聊天室！");
                                sendStatus();
                            } else {
                                System.out.println("用户token信息有误，无法从redis中查到该用户信息！");
                            }

                            break;
                        case 602:
                            running = false;
                            //TODO: 处理退出
                            break;
                        case 611:
                            //TODO:处理交谈
                            CommonData sendData = new CommonData();
                            sendData.setCode(711);
                            sendData.setContent(commonData.getContent());
                            sendData.setFrom(this.client);
                            System.out.println(sendData);
                            context.getChaters().stream()
                                    .filter(chater -> commonData.getTo().contains(chater.getClient()))
                                    .forEach(chater -> chater.send(sendData));
                            break;
                    }
                } else {
                    System.out.println("客户端：" + "中断了TCP连接！");
                    running = false;
                }
            } catch (JacksonException exception) {
                System.out.println("客户端输入的数据格式不对！");
            } catch (IOException exception) {
                System.out.println("读客户端数据时，出现错误！");
            }
        }
        //线程结束前的收尾工作：
        System.out.println("客户：" + " 退出聊天！！！");
        context.getChaters().remove(this);
        sendStatus();
        //关闭socket
        try {
            socket.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }


    public void start() {
        new Thread(this).start();
    }
    //向所有的客户端发送用户状态信息
    private void sendStatus() {
        CommonData data = new CommonData();
        data.setCode(701);
        context.getChaters().forEach(chater -> data.getTo().add(chater.getClient()));
        context.getChaters().stream().forEach(chater -> chater.send(data));
    }
}
