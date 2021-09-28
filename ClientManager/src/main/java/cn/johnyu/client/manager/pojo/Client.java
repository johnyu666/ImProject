package cn.johnyu.client.manager.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Client {
    private Integer id;
    private String loginName;
    private String password;
    private String nickName;
}
