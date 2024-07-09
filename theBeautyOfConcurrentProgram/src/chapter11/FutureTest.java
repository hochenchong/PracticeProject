package chapter11;

import java.util.concurrent.*;

/**
 * 11.9 线程池使用FutureTask时需要注意的事情
 *     线程池使用 FutureTask 时如果把拒绝策略设置为 DiscardPolicy 和 DiscardOldestPolicy，并且在被拒绝的任务的 Future 对象上调用了无参 get 方法，那么调用线程会一直被阻塞。
 *     调用 threadPoolExecutor.submit() 后会返回 FutureTask 对象，FutureTask 是有状态的，默认状态是 NEW
 *     FutureTask 的 get 方法会去判断状态，如果状态 <= COMPLETING 则阻塞，而 NEW 状态就小于 COMPLETING 状态
 *     默认的 AbortPolicy 策略不会有问题的原因：会直接抛出异常，此时 submit 方法并不会返回 future 对象
 *
 * @author hochenchong
 * @date 2024/6/4
 */
public class FutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 1L, TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(1), new ThreadPoolExecutor.DiscardPolicy());

        Future<?> startOne = threadPoolExecutor.submit(() -> {
            System.out.println("start one");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Future<?> startTwo = threadPoolExecutor.submit(() -> {
            System.out.println("start two");
        });

        // 任务 1 sleep，任务 2 在队列里，任务 3 则
        Future<?> startThree = null;
        try {
            startThree = threadPoolExecutor.submit(() -> {
                System.out.println("start three");
            });
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

        System.out.println("task one " + startOne.get());
        System.out.println("task two " + startTwo.get());
        // 使用 DiscardPolicy 策略，startThree 则会返回一个状态为 NEW 的 Future，此时的 get 一直阻塞不会返回
        // FutureTask 的 get 方法会去判断状态，如果状态 <= COMPLETING 则阻塞，而 NEW 状态就小于 COMPLETING 状态
        System.out.println("task three " + (startThree == null ? null : startThree.get()));
        threadPoolExecutor.shutdown();
    }
}
