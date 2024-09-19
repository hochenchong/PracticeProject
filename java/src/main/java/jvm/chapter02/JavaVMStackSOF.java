package jvm.chapter02;

/**
 * 虚拟机栈和本地方法栈溢出
 * VM Args: -Xss128k
 *
 * @author hochenchong
 * @date 2024/09/19
 */
public class JavaVMStackSOF {
    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        JavaVMStackSOF oom = new JavaVMStackSOF();
        try {
            oom.stackLeak();
        } catch (Throwable throwable) {
            System.out.println("stack length: " + oom.stackLength);
            throw throwable;
        }
    }
}
