package hochenchong.protocol.req;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hochenchong.protocol.command.Command;
import hochenchong.protocol.Packet;
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
public class LoginReqPacket extends Packet {

    private Integer userId;

    private String username;

    private String password;

    @JsonIgnore
    @Override
    public byte getCommand() {
        return Command.LOGIN_REQ;
    }
}
