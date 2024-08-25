package hochenchong.protocol;

import lombok.Data;

/**
 * 通信过程中的 Java 对象
 *
 * @author hochenchong
 * @date 2024/08/22
 */
@Data
public abstract class Packet {
    /**
     * 协议版本
     */
    private byte version = 1;

    /**
     * 指令
     */
    public abstract Byte getCommand();
}
