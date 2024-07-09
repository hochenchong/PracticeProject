package chapter11;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 11.10 使用 ThreadLocal 不当可能会导致内存泄漏
 *     没有调用线程池的 shutdown，线程池的用户线程不回退出，进而 JVM 进行也不退出
 *     使用 jconsole 监控堆内存变化
 *
 * @author hochenchong
 * @date 2024/6/4
 */
public class ThreadPoolTest {
    static class LocalVariable {
        private Long[] a = new Long[1024 * 1024];
    }

    final static ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5, 5, 1, TimeUnit.MINUTES,
            new LinkedBlockingQueue<>());
    final static ThreadLocal<LocalVariable> localVariable = new ThreadLocal<>();

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            poolExecutor.execute(() -> {
                localVariable.set(new LocalVariable());
                System.out.println("user local variable");
                // 使用后，应该移除掉
                // localVariable.remove();
            });
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("pool execute over");
        // 关闭线程池
        poolExecutor.shutdown();
    }
}
