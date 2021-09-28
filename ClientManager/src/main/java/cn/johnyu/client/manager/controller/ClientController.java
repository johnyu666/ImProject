package cn.johnyu.client.manager.controller;

import cn.johnyu.client.manager.mapper.ClientMapper;
import cn.johnyu.client.manager.pojo.Client;
import cn.johnyu.client.manager.pojo.CommonResult;
import cn.johnyu.client.manager.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {
    @Autowired
    private ClientMapper mapper;
    @Autowired
    private ClientService clientService;

    @GetMapping("/login")
    public CommonResult login(String loginName,String password){
        Client client=mapper.findClientByLoginNameAndPassword(loginName,password);
        CommonResult commonResult=null;
        if(client!=null){
            String token=clientService.storeClientAndCreateToken(client);
            commonResult=new CommonResult(200,token,client);
        }
        else{
            commonResult=new CommonResult(404,"",null);
        }
        return commonResult;
    }

}
