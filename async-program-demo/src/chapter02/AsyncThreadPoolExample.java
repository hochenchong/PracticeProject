package chapter02;

import java.util.concurrent.*;

/**
 * @author hochenchong
 * @date 2024/6/5
 */
public class AsyncThreadPoolExample {

    private static String doSomethingA() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("---doSomethingA---");
        return "Task A Done";
    }

    private static final int AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();

    private static final ThreadPoolExecutor POOL_EXECUTOR = new ThreadPoolExecutor(AVAILABLE_PROCESSORS, AVAILABLE_PROCESSORS * 2, 1, TimeUnit.MINUTES,
            new LinkedBlockingQueue<>(5), new ThreadPoolExecutor.CallerRunsPolicy());

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Future<String> resultA = POOL_EXECUTOR.submit(AsyncThreadPoolExample::doSomethingA);
        try {
            // 同步等待执行结果
            System.out.println(resultA.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        System.out.println(System.currentTimeMillis() - start);

        POOL_EXECUTOR.shutdown();
    }
}
