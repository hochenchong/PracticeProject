package chapter10;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 10.3 信号量 Semaphore 原理探究
 *
 * @author hochenchong
 * @date 2024/6/4
 */
public class SemaphoreDemo1 {
    private static final Semaphore semaphore = new Semaphore(0);

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            System.out.println(Thread.currentThread() + " over!");
            semaphore.release();
        };

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(runnable);
        executorService.submit(runnable);
        // 等待子线程执行完毕
        semaphore.acquire(2);
        System.out.println("all child thread over!");
        executorService.shutdown();
    }
}
