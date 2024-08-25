package hochenchong.protocol.resp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hochenchong.protocol.Packet;
import hochenchong.protocol.command.Command;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author hochenchong
 * @date 2024/08/25
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CreateGroupRespPacket extends Packet {
    private boolean success;

    private String groupId;

    private List<String> usernames;

    @JsonIgnore
    @Override
    public Byte getCommand() {
        return Command.CREATE_GROUP_RESP;
    }
}
