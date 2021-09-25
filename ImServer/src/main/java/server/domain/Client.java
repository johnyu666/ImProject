package server.domain;

import lombok.*;

import java.util.Objects;

@Setter@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    private Integer id;
    private String loginName;
    private String password;
    private String nickName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id.equals(client.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
