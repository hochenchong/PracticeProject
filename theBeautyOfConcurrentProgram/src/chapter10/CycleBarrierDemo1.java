package chapter10;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 10.2 回环屏障 CyclicBarrier 原理探究
 *
 * @author hochenchong
 * @date 2024/6/4
 */
public class CycleBarrierDemo1 {
    private static final CyclicBarrier cyclicBarrier = new CyclicBarrier(2, () -> {
        System.out.println(Thread.currentThread() + " task1 merge result");
    });

    public static void main(String[] args) {
        Runnable runnable = () -> {
            System.out.println(Thread.currentThread() + " task1");
            System.out.println(Thread.currentThread() + " enter in barrier");
            try {
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread() + " enter out barrier");
        };

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(runnable);
        executorService.submit(runnable);
        executorService.shutdown();
    }
}
