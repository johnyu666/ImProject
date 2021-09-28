package cn.johnyu.client.manager.mapper;

import cn.johnyu.client.manager.pojo.Client;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ClientMapper {
    Client findClientByLoginNameAndPassword(@Param(value = "lname") String loginName, @Param(value = "password") String password);
}
