package hochenchong.nio;

/**
 * 异步非阻塞 I/O 的 TimeClient
 * @author hochenchong
 * @date 2024/07/17
 */
public class TimeClient {
    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length > 0) {
            // 没捕获异常以及，没对端口范围进行校验
            port = Integer.parseInt(args[0]);
        }
        new Thread(new TimeClientHandle("127.0.0.1", port), "TimeClient-001").start();
    }
}
