package hochenchong.aio;


/**
 * AIO 客户端
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
        // 实际使用中，不需要独立的线程创建异步连接对象
        // 因为底层都是通过 JDK 的系统回调实现
        new Thread(new AsyncTimeClientHandler("127.0.0.1", port), "AIO-AsyncTimeClientHandler-001").start();
    }
}
