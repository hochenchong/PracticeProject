package hochenchong.chapter7;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * JDK 的任务调度 API
 */
public class ScheduledExecutorExample {

    public static void example() {
        // 创建一个其线程池具有 10个线程的 ScheduledExecutorService
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);
        // 创建一个  Runnable 以供调度稍后执行，调度任务在从现在开始的 60秒之后执行
        ScheduledFuture<?> future = executorService.schedule(() -> System.out.println("60 seconds later"), 60, TimeUnit.SECONDS);
        // ...
        // 一旦调度任务执行完成，就关闭 ScheduledExecutorService 释放资源
        executorService.shutdown();
    }
}
