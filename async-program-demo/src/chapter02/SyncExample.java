package chapter02;

/**
 * 同步执行两个任务
 *
 * @author hochenchong
 * @date 2024/6/5
 */
public class SyncExample {
    public static void doSomethingA() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("---doSomethingA---");
    }

    public static void doSomethingB() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("---doSomethingB---");
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        doSomethingA();
        doSomethingB();
        System.out.println(System.currentTimeMillis() - start);
    }
}
