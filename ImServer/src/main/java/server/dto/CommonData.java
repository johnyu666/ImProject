package server.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import server.domain.Client;
import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
public class CommonData {
    /**
    600表示上行（客户端到服务）
    601:enter
    602:exit
    611:点对点聊天
    612: 群广播
    **/

    /**
    700表示下行（服务器端到客户端）

    701: 所有用户数据更新（有登录，退出等情况变化，统一下发所有的用户信息）
    711: 点对点聊天
    712: 群广播

     721: 强制退出
     **/

    private int code;
    private long timestamp; //信息时间信息
    private Client from;//信息由谁发出
    private List<Client> to=new ArrayList<>();//使用JSON类型表示，信息向谁发出
    private String content;//信息的内容
    private String action;//talk表示聊天，talk_group表示分组，talk_broadcast表示广播，file表示传文件，shake表示震屏,enter表示进入，exit退出
}
