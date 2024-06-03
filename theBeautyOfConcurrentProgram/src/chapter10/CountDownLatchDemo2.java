package chapter10;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 10.1 CountDownLatch 原理剖析
 *     线程池使用 CountDownLatch
 *
 * @author hochenchong
 * @date 2024/6/3
 */
public class CountDownLatchDemo2 {
    private static final CountDownLatch countDownLatch = new CountDownLatch(2);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Runnable runnable = () -> {
            try {
                Thread.sleep(1000);
                // do something
                System.out.println(Thread.currentThread().getName() + " over!");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                countDownLatch.countDown();
            }
        };
        executorService.submit(runnable);
        executorService.submit(runnable);

        System.out.println("wait all child thread over!");
        // 等待子线程执行完毕
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("all child thread over!");
        executorService.shutdown();
    }
}
