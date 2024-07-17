package hochenchong.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author hochenchong
 * @date 2024/07/17
 */
public class TimeServerHandler implements Runnable {
    private Socket socket;

    public TimeServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
             PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true)) {
            String currentTime = null;
            String body = null;
            while (true) {
                body = in.readLine();
                if (body == null) {
                    break;
                }
                System.out.println("The time server receive order : " + body);
                currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) :  "BAD ORDER";
                out.println(currentTime);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            this.socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.socket = null;
    }
}
