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
public class SemaphoreDemo2 {
    private static final Semaphore semaphore = new Semaphore(0);

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            System.out.println(Thread.currentThread() + " over!");
            semaphore.release();
        };

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        // 第一阶段任务
        executorService.submit(runnable);
        executorService.submit(runnable);
        // 等待子线程执行完毕
        semaphore.acquire(2);
        System.out.println("step1 over");
        // 第二阶段任务
        executorService.submit(runnable);
        executorService.submit(runnable);
        // 等待子线程执行完毕
        semaphore.acquire(2);
        System.out.println("step2 over!");
        // 关闭线程池
        executorService.shutdown();
    }
}
