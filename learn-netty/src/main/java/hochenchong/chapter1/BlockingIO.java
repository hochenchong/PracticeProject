package hochenchong.chapter1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 代码清单 1-1 阻塞 I/O 示例
 */
public class BlockingIO {
    public void server(int portNum) throws IOException {
        ServerSocket serverSocket = new ServerSocket(portNum);
        Socket clientSocket = serverSocket.accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        String request, response;
        while ((request = in.readLine()) != null) {
            if ("Done".equals(request)) {
                break;
            }
            // 请求被传递给服务器的处理方法
            response = processRequest(request);
            out.println(response);
        }
    }

    private String processRequest(String request) {
        return "Processed";
    }
}
