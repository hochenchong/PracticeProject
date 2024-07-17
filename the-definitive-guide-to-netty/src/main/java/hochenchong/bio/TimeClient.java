package hochenchong.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 同步阻塞 I/O 的 TimeClient
 *
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
        try (Socket socket = new Socket("127.0.0.1", port);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ){
            out.println("QUERY TIME ORDER");
            System.out.println("Send server succeed.");
            String resp = in.readLine();
            System.out.println("Now is : " + resp);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
