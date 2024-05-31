package chapter01;

/**
 * 1.11 ThreadLocal
 *     获取的数据，实际上存放在 Thread.threadLocals 里
 *
 * @author hochenchong
 * @date 2024/5/31
 */
public class ThreadLocalTest {
    static ThreadLocal<String> localVal = new ThreadLocal<>();
    public static void main(String[] args) {
        Runnable runnable = () -> {
            // 设置线程里的本地变量
            localVal.set(Thread.currentThread() + " local val");
            // 打印该线程的本地变量
            System.out.println(Thread.currentThread() + ": " + localVal.get());
        };

        new Thread(runnable).start();
        new Thread(runnable).start();
    }
}
