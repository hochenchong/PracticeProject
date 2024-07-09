package chapter03;

import java.util.concurrent.*;

/**
 * 3.3 JDK 中的 CompletableFuture
 *     CompletableFuture 如果不指定线程池，则默认使用 ForkJoinPool.commonPool() 线程池来执行任务
 *     ForkJoinPool.commonPool() 里的线程，默认都是 Daemon 线程
 *
 * @author hochenchong
 * @date 2024/6/6
 */
public class CompletableFutureExample {
    private static final int AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();
    private static final ThreadPoolExecutor POOL_EXECUTOR = new ThreadPoolExecutor(AVAILABLE_PROCESSORS, AVAILABLE_PROCESSORS * 2, 1, TimeUnit.MINUTES,
            new LinkedBlockingQueue<>(5), new ThreadPoolExecutor.CallerRunsPolicy());

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        // 开启线程计算任务结果，并设置结果
        POOL_EXECUTOR.execute(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("---" + Thread.currentThread().getName() + " set future result---");
            completableFuture.complete("hello, hochen");
        });
        System.out.println("---main thread wait future result---");
        System.out.println(completableFuture.get());
        System.out.println("---main thread got future result---");

        System.out.println();
        System.out.println("------");
        // 异步任务，runAsync() 没有返回值
        CompletableFuture<Void> runAsyncFuture = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("runAsyncFuture over");
        }, POOL_EXECUTOR);
        System.out.println("runAsyncFuture get: " + runAsyncFuture.get());

        System.out.println();
        System.out.println("------");
        // 异步任务，supplyAsync() 有返回值
        CompletableFuture<String> supplyAsyncFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "hello, hochen";
        }, POOL_EXECUTOR);
        System.out.println("supplyAsyncFuture get: " + supplyAsyncFuture.get());

        System.out.println();
        System.out.println("------");
        // 基于 thenRun 实现异步任务 A，执行完毕后执行任务 B
        CompletableFuture<String> taskAFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "task A: hello";
        }, POOL_EXECUTOR);
        CompletableFuture<Void> taskBFuture = taskAFuture.thenRun(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName());
            System.out.println("---after taskA over doSomething---");
        });
        // 同步等待两个任务执行完毕，taskB 没有返回值，所以返回结果为 null
        System.out.println("taskBFuture get: " + taskBFuture.get());

        // thenAccept 实现异步任务 A，执行完毕后，执行任务 B 时，B 可以拿到任务 A 的执行结果
        // thenApple 则在 thenAccept 的基础上，可以拿到任务 B 的结果
        // 这两个就不示范了

        // 基于 whenComplete 设置回调函数
        // 当异步任务执行完毕后进行回调，不会阻塞调用线程
        System.out.println();
        System.out.println("------");
        CompletableFuture<String> whenCompeteFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "hello, hochen";
        });
        whenCompeteFuture.whenComplete((s, t) -> {
            // 如果没有异常，打印结果
            if (t == null) {
                System.out.println(s);
            } else {
                System.out.println(t.getLocalizedMessage());
            }
        });
        // 主线程等待一下，默认执行的 ForkJoinPool 的 commonPool 线程池，线程都是 Daemon 线程
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        POOL_EXECUTOR.shutdown();
    }
}
