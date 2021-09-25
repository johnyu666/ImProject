package cn.johnyu;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import server.domain.Client;
import server.dto.CommonData;

public class JacksonTest {
    @Test
    public void testBase() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Client client = new Client(1, "john", "123", "johnyu");
        String result = mapper.writeValueAsString(client);
        System.out.println(result);

        Client client1 = mapper.readValue(result, Client.class);
        System.out.println(client1.getNickName());
    }

    @Test
    public void testBase2() throws Exception {
        CommonData cd = new CommonData();
        cd.setCode(611);
        cd.setContent("John Love You");
        Client client = new Client();
        client.setId(1);
        client.setLoginName("john");

        cd.setFrom(client);

        for (int i = 2; i <= 3; i++) {
            Client c1 = new Client();
            c1.setId(i);
//            c1.setLoginName("c" + i);
            cd.getTo().add(c1);
        }
        ObjectMapper mapper = new ObjectMapper();
        String s1 = mapper.writeValueAsString(cd);
        System.out.println(s1);

        CommonData commonData = mapper.readValue(s1, CommonData.class);
        Client client1 = commonData.getFrom();
        System.out.println(client1.getLoginName());

        commonData.getTo().forEach(System.out::println);
    }

}
