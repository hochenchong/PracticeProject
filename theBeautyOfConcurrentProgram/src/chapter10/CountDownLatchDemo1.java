package chapter10;

import java.util.concurrent.CountDownLatch;

/**
 * 10.1 CountDownLatch 原理剖析
 *
 * @author hochenchong
 * @date 2024/6/3
 */
public class CountDownLatchDemo1 {
    private static final CountDownLatch countDownLatch = new CountDownLatch(2);

    public static void main(String[] args) {
        Runnable runnable = () -> {
            try {
                Thread.sleep(1000);
                // do something
                System.out.println(Thread.currentThread().getName() + " over!");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                countDownLatch.countDown();
            }
        };

        Thread childThreadOne = new Thread(runnable, "child ThreadOne");
        Thread childThreadTwo = new Thread(runnable, "child ThreadTwo");
        childThreadOne.start();
        childThreadTwo.start();
        System.out.println("wait all child thread over!");
        // 等待子线程执行完毕
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("all child thread over!");
    }
}
