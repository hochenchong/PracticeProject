package chapter03;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 3.3 JDK 中的 CompletableFuture
 *
 * @author hochenchong
 * @date 2024/6/6
 */
public class TwoCompletableFutureExample {
    private static CompletableFuture<String> doSomethingOne(String id) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return id;
        });
    }
    private static CompletableFuture<String> doSomethingTwo(String id) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return id + " doSomethingTwo";
        });
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // thenCompose 执行一个后执行下一个
        CompletableFuture<String> result = doSomethingOne("123").thenCompose(TwoCompletableFutureExample::doSomethingTwo);
        System.out.println(result.get());

        System.out.println();
        System.out.println("------");
        // thenCombine 两个并发执行，然后将两个结果合并
        result = doSomethingOne("111").thenCombine(doSomethingTwo("222"), (a, b) -> {
            return a + " - " + b;
        });
        System.out.println(result.get());

        System.out.println();
        System.out.println("------");
        // allOf 待多个并发运行的任务执行完毕
        List<CompletableFuture<String>> list = new ArrayList<>();
        list.add(doSomethingOne("1"));
        list.add(doSomethingOne("2"));
        list.add(doSomethingOne("3"));
        list.add(doSomethingOne("4"));
        // 将多个 future 转换为一个
        CompletableFuture<Void> allOfResult = CompletableFuture.allOf(list.toArray(new CompletableFuture[list.size()]));
        // 等待所有 future 都完成
        System.out.println(allOfResult.get());
        // anyOf 同 allOf，只是只要有一个执行完毕就返回

        System.out.println();
        System.out.println("------");
        // 异常处理
        CompletableFuture<String> exceptionFuture = new CompletableFuture<>();
        new Thread(() -> {
            try {
                if (true) {
                    throw new RuntimeException("exception test");
                }
                exceptionFuture.complete("OK");
            } catch (Exception e) {
                exceptionFuture.completeExceptionally(e);
            }
        }).start();
        System.out.println(exceptionFuture.exceptionally(t -> "default").get());
    }
}
