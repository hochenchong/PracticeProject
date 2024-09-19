package jvm.chapter02;

/**
 * 创建线程导致内存溢出异常
 * VM Args：-Xss（在 32 位系统下运行）
 *
 * @author hochenchong
 * @date 2024/09/19
 */
public class JavaVMStackOOM {
    private void dontStop() {
        while (true) {
        }
    }

    public void stackLeakByThread() {
        while (true) {
            new Thread(this::dontStop).start();
        }
    }

    public static void main(String[] args) {
        JavaVMStackOOM oom = new JavaVMStackOOM();
        oom.stackLeakByThread();
    }
}
