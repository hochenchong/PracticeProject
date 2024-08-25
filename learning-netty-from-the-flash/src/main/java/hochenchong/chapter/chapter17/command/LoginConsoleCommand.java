package hochenchong.chapter.chapter17.command;

import hochenchong.protocol.req.LoginReqPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author hochenchong
 * @date 2024/08/25
 */
public class LoginConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("输入用户名登录：");
        String username = scanner.next();

        LoginReqPacket loginReqPacket = new LoginReqPacket();
        loginReqPacket.setUsername(username);
        channel.writeAndFlush(loginReqPacket);

        waitForLoginResp();
    }

    private static void waitForLoginResp() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
    }
}
