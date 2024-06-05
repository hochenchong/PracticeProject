package chapter02;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 使用线程池来实现线程复用
 *
 * @author hochenchong
 * @date 2024/6/5
 */
public class ThreadPoolExample {
    private static final int AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();

    private static final ThreadPoolExecutor POOL_EXECUTOR = new ThreadPoolExecutor(AVAILABLE_PROCESSORS, AVAILABLE_PROCESSORS * 2, 1, TimeUnit.MINUTES,
            new LinkedBlockingQueue<>(5), new ThreadPoolExecutor.CallerRunsPolicy());

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        POOL_EXECUTOR.execute(() -> {
            try {
                SyncExample.doSomethingA();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        POOL_EXECUTOR.execute(() -> {
            try {
                SyncExample.doSomethingB();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        System.out.println(System.currentTimeMillis() - start);

        // 挂起当前线程
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
