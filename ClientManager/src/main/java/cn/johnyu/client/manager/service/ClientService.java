package cn.johnyu.client.manager.service;

import cn.johnyu.client.manager.pojo.Client;

public interface ClientService {
    String storeClientAndCreateToken(Client client);
}

