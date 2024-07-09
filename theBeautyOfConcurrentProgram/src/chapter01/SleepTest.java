package chapter01;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 1.5 让线程睡眠的 sleep 方法
 *     sleep，只是暂时让出指定时间的执行权，该线程所拥有的监视器资源，比如锁还是持有的
 *
 * @author hochenchong
 * @date 2024/5/31
 */
public class SleepTest {
    // 创建一个独占锁
    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        Runnable runnable = () -> {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " is in sleep");
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " is in awake");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        };

        Thread threadA = new Thread(runnable, "threadA");
        Thread threadB = new Thread(runnable, "threadB");
        threadA.start();
        threadB.start();
    }
}
