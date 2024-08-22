package hochenchong.chapter8.packet;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hochenchong.chapter8.Command;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 登录请求
 *
 * @author hochenchong
 * @date 2024/08/22
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LoginRequestPacket extends Packet {

    private Integer userId;

    private String username;

    private String password;

    @JsonIgnore
    @Override
    public byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}
