package hochenchong.protocol.resp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hochenchong.protocol.Packet;
import hochenchong.protocol.command.Command;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author hochenchong
 * @date 2024/08/26
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class HeartBeatRespPacket extends Packet {

    @JsonIgnore
    @Override
    public Byte getCommand() {
        return Command.HEARTBEAT_RESP;
    }
}
