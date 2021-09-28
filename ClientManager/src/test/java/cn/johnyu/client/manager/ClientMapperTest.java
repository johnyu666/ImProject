package cn.johnyu.client.manager;

import cn.johnyu.client.manager.mapper.ClientMapper;
import cn.johnyu.client.manager.pojo.Client;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ClientMapperTest {
    @Autowired
    private ClientMapper mapper;
    @Test
    public void testLogin(){

        Client client=mapper.findClientByLoginNameAndPassword("john","123");
        System.out.println(client.getLoginName());
    }

}
