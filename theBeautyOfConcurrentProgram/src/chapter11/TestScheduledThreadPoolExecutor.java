package chapter11;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 11.5 使用Timer时需要注意的事情
 *     使用 ScheduledThreadPoolExecutor 来规避 Timer 一个任务抛出异影响到其他任务的问题
 *
 * @author hochenchong
 * @date 2024/6/4
 */
public class TestScheduledThreadPoolExecutor {
    public static void main(String[] args) {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);

        scheduledThreadPoolExecutor.schedule(() -> {
            System.out.println("---one task---");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            throw new RuntimeException("error");
        }, 500, TimeUnit.MICROSECONDS);

        scheduledThreadPoolExecutor.schedule(() -> {
            for (int i = 0; i < 2; i++) {
                System.out.println("---two task---");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, 1000, TimeUnit.MICROSECONDS);

        scheduledThreadPoolExecutor.shutdown();
    }
}
