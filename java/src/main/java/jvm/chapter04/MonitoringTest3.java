package jvm.chapter04;

/**
 * 代码清单 4-9 死锁代码样例
 *
 * @author hochenchong
 * @date 2024/09/20
 */
public class MonitoringTest3 {
    /**
     * 线程死锁等待演示
     */
    static class SynAddRunnable implements Runnable {
        int a, b;
        public SynAddRunnable(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public void run() {
            synchronized (Integer.valueOf(a)) {
                synchronized (Integer.valueOf(b)) {
                    System.out.println(a + b);
                }
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(new SynAddRunnable(1, 2)).start();
            new Thread(new SynAddRunnable(2, 1)).start();
        }
    }
}
