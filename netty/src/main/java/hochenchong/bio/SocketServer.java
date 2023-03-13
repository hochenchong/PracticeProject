package hochenchong.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9000);
        while (true) {
            System.out.println("阻塞IO，等待连接。。。");
            Socket socket = serverSocket.accept();
            System.out.println("有客户端连接。。。");
            handler(socket);
        }
    }

    private static void handler(Socket socket) throws IOException {
        byte[] bytes = new byte[1024];
        int read = socket.getInputStream().read(bytes);
        if (read != -1) {
            System.out.println("接收到数据：" + new String(bytes, 0, read));
        }
        System.out.println("end");
    }
}
