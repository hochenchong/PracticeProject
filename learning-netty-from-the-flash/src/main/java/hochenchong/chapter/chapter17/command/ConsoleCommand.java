package hochenchong.chapter.chapter17.command;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author hochenchong
 * @date 2024/08/25
 */
public interface ConsoleCommand {
    void exec(Scanner scanner, Channel channel);
}
