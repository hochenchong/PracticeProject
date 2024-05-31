package chapter01;

/**
 * 1.6 让出CPU执行权的yield方法
 *
 * @author hochenchong
 * @date 2024/5/31
 */
public class YieldTest {
    public static void main(String[] args) {
        Runnable yieldRunnable = () -> {
            for (int i = 0; i < 5; i++) {
                if (i % 5 == 0) {
                    System.out.println(Thread.currentThread() + "yield cpu..");
                    // 当前线程让出 CPU 执行权
                    Thread.yield();
                }
            }
            System.out.println(Thread.currentThread() + " is over");
        };

        new Thread(yieldRunnable).start();
        new Thread(yieldRunnable).start();
    }
}
