package hochenchong.nio;

/**
 * 异步非阻塞 I/O 的 TimeServer
 *
 * @author hochenchong
 * @date 2024/07/17
 */
public class TimeServer {
    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length > 0) {
            port = Integer.parseInt(args[0]);
        }
        MultiplexerTimeServer timeServer = new MultiplexerTimeServer(port);
        new Thread(timeServer, "NIO-MultiplexerTimeServer-001").start();
    }
}
