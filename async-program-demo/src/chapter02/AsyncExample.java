package chapter02;

/**
 * 异步执行两个任务
 *     主线程被阻塞，需要等待都执行完毕
 *     需要创建多个线程来执行，线程的创建和销毁有开销
 *
 * @author hochenchong
 * @date 2024/6/5
 */
public class AsyncExample {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Thread thread = new Thread(() -> {
            try {
                SyncExample.doSomethingA();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
        SyncExample.doSomethingB();
        // 同步等待线程 A 执行完毕
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(System.currentTimeMillis() - start);
    }
}
