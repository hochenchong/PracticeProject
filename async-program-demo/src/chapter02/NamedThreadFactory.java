package chapter02;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义 ThreadFactory
 *
 * @author hochenchong
 * @date 2024/6/6
 */
public class NamedThreadFactory implements ThreadFactory {
    private final String baseName;
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final ThreadFactory defaultFactory = Executors.defaultThreadFactory();

    public NamedThreadFactory(String baseName) {
        this.baseName = baseName;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = defaultFactory.newThread(r);
        thread.setName(baseName + "-thread-" + threadNumber.getAndIncrement());
        return thread;
    }
}
