package hochenchong.protocol.resp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hochenchong.protocol.command.Command;
import hochenchong.protocol.Packet;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author hochenchong
 * @date 2024/08/22
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LoginRespPacket extends Packet {
    /**
     * 版本号
     */
    private byte version;
    /**
     * 是否登录成功
     */
    private boolean success;
    /**
     * 原因
     */
    private String reason;

    /**
     * chapter16 新增的用户 id
     */
    private String userId;
    private String username;

    @JsonIgnore
    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESP;
    }
}
