package chapter01;

/**
 * 1.9 线程死锁
 *     让两个线程分别以不同的顺序锁住 A、B 两个资源，从而造成死锁
 *
 * @author hochenchong
 * @date 2024/5/31
 */
public class DeadLockTest {
    private static final Object resourceA = new Object();
    private static final Object resourceB = new Object();
    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (resourceA) {
                System.out.println(Thread.currentThread() + " get resourceA");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread() + " waiting get resourceB");
                synchronized (resourceB) {
                    System.out.println(Thread.currentThread() + " get resourceB");
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (resourceB) {
                System.out.println(Thread.currentThread() + " get resourceB");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread() + " waiting get resourceA");
                synchronized (resourceA) {
                    System.out.println(Thread.currentThread() + " get resourceA");
                }
            }
        }).start();

    }
}
