package hochenchong.chapter9.resp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hochenchong.chapter8.Command;
import hochenchong.chapter8.packet.Packet;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author hochenchong
 * @date 2024/08/22
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LoginResponsePacket extends Packet {
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

    @JsonIgnore
    @Override
    public byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }
}
