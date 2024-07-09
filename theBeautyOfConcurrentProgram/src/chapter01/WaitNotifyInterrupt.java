package chapter01;

/**
 * 1.3 线程通知与等待
 *
 * @author hochenchong
 * @date 2024/5/31
 */
public class WaitNotifyInterrupt {
    static final Object obj = new Object();

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            try {
                System.out.println("---begin---");
                // 阻塞当前对象
                synchronized(obj) {
                    obj.wait();
                }
                System.out.println("---end---");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("---begin interrupt thread---");
        thread.interrupt();
        System.out.println("---end interrupt thread---");
    }
}
