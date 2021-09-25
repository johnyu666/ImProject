package cn.johnyu.server.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import server.domain.Client;
import server.service.ClientService;

public class ClientServiceTest {
    private ClientService clientService;
    @Before
    public void before(){
        clientService=new ClientService("athyu.com",6379,"clients");
        clientService.initClients();

    }
    @Test
    public void testLoadClient(){
        Client client = clientService.loadClient("6f89");
        Assert.assertTrue(client.getLoginName().equals("john"));
    }
    @After
    public void after(){
        clientService.close();
    }


}
