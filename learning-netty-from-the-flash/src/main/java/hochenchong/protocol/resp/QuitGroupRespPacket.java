package hochenchong.protocol.resp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hochenchong.protocol.Packet;
import hochenchong.protocol.command.Command;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author hochenchong
 * @date 2024/08/25
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QuitGroupRespPacket extends Packet {

    private boolean success;

    private String groupId;
    /**
     * 信息
     */
    private String data;

    @JsonIgnore
    @Override
    public Byte getCommand() {
        return Command.QUIT_GROUP_RESP;
    }
}
