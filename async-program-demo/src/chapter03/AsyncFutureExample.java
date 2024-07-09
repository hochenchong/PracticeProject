package chapter03;

import java.util.concurrent.*;

/**
 * 3.2 JDK 中的 FutureTask
 *
 * @author hochenchong
 * @date 2024/6/6
 */
public class AsyncFutureExample {
    private static String doSomethingA() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("---doSomethingA---");
        return "TaskAResult";
    }
    private static String doSomethingB() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("---doSomethingB---");
        return "TaskBResult";
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        FutureTask<String> futureTask = new FutureTask<>(() -> {
            String result = null;
            try {
                result = doSomethingA();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        });
        Thread thread = new Thread(futureTask);
        thread.start();
        String taskBResult = doSomethingB();
        // 同步等待线程 A 运行结束
        String taskAResult = null;
        try {
            taskAResult = futureTask.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        System.out.println(taskAResult + " " + taskBResult);
        System.out.println(System.currentTimeMillis() - start);

        System.out.println("------");

        // 也可以提交给线程池来运行
        FutureTask<String> futureTask1 = new FutureTask<>(() -> {
            String result = null;
            try {
                result = doSomethingA();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        });
        int AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();
        ThreadPoolExecutor POOL_EXECUTOR = new ThreadPoolExecutor(AVAILABLE_PROCESSORS, AVAILABLE_PROCESSORS * 2, 1, TimeUnit.MINUTES,
                new LinkedBlockingQueue<>(5), new ThreadPoolExecutor.CallerRunsPolicy());
        start = System.currentTimeMillis();
        POOL_EXECUTOR.execute(futureTask1);
        taskBResult = doSomethingB();
        // 同步等待线程 A 运行结束
        taskAResult = null;
        try {
            taskAResult = futureTask1.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        System.out.println(taskAResult + " " + taskBResult);
        System.out.println(System.currentTimeMillis() - start);
        POOL_EXECUTOR.shutdown();
    }
}