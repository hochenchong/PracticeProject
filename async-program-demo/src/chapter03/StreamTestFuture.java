package chapter03;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * 3.4 JDK8 Stream & CompletableFuture
 *
 * @author hochenchong
 * @date 2024/6/6
 */
public class StreamTestFuture {
    private static String rpcCall(String ip, String param) {
        System.out.println(ip + " rpcCall:" + param);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return param;
    }

    public static void main(String[] args) {
        // 生成 ip 列表
        List<String> ipList = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            ipList.add("192.168.0." + i);
        }
        long start = System.currentTimeMillis();
        // 发起广播调用
        List<String> result = new ArrayList<>();
        for (String ip : ipList) {
            result.add(rpcCall(ip, ip));
        }
        // 输出
        result.forEach(System.out::println);
        System.out.println(System.currentTimeMillis() - start);

        // 使用并发调用
        System.out.println();
        System.out.println("------");
        start = System.currentTimeMillis();
        List<CompletableFuture<String>> futures = ipList.stream().map(ip -> CompletableFuture.supplyAsync(() -> rpcCall(ip, ip)))
                .toList();
        // 等待所有异步任务执行完毕
        result = futures.stream().map(CompletableFuture::join).toList();
        // 输出
        result.forEach(System.out::println);
        System.out.println(System.currentTimeMillis() - start);
    }
}
