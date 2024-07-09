package chapter10;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 10.2 回环屏障 CyclicBarrier 原理探究
 *     假设一个任务由阶段 1、阶段 2 和阶段 3 组成
 *     每个线程要串行地执行阶段 1、阶段 2 和阶段 3
 *     当多个线程执行该任务时，必须要保证所有线程的阶段 1 全部完成后才能进入阶段 2 执行
 *     当所有线程的阶段 2 全部完成后才能进入阶段 3 执行
 * @author hochenchong
 * @date 2024/6/4
 */
public class CycleBarrierDemo2 {
    private static final CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

    public static void main(String[] args) {
        Runnable runnable = () -> {
            try {
                System.out.println(Thread.currentThread() + " step1");
                cyclicBarrier.await();
                System.out.println(Thread.currentThread() + " step2");
                cyclicBarrier.await();
                System.out.println(Thread.currentThread() + " step3");
            } catch (InterruptedException | BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
        };
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(runnable);
        executorService.submit(runnable);
        executorService.shutdown();
    }
}
