package chapter01;

/**
 * 1.10 守护线程与用户线程
 *     守护线程是否结束不影响 JVM 退出
 *     只有有一个用户线程没结束，正常情况下 JVM 就不会退出
 *
 * @author hochenchong
 * @date 2024/5/31
 */
public class DaemonThreadTest {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            for (; ; ) {

            }
        });
        // 设置为守护线程
        thread.setDaemon(true);
        thread.start();
        System.out.println("main thread is over");
    }
}
