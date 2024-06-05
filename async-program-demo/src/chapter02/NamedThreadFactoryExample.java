package chapter02;

import java.util.concurrent.*;

/**
 * @author hochenchong
 * @date 2024/6/6
 */
public class NamedThreadFactoryExample {
    private static final int AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) {
        ThreadPoolExecutor POOL_EXECUTOR = new ThreadPoolExecutor(AVAILABLE_PROCESSORS, AVAILABLE_PROCESSORS * 2, 1, TimeUnit.MINUTES,
                new LinkedBlockingQueue<>(5), new NamedThreadFactory("MyPool"), new ThreadPoolExecutor.CallerRunsPolicy());
        // 提交任务
        for (int i = 0; i < 10; i++) {
            POOL_EXECUTOR.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " is executing task.");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
        // 关闭线程池
        POOL_EXECUTOR.shutdown();
    }
}
