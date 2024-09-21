package jvm.chapter04;

/**
 * 代码清单 4-6：JHSDB 测试代码
 * VM Args：-Xmx10m -XX:+UseSerialGC -XX:-UseCompressedOops
 *
 * @author hochenchong
 * @date 2024/09/18
 */
public class JHSDB_TestCase {
    private static class ObjectHolder {
    }

    static class Test {
        // 随着 Test 的类型信息存放在方法区
        static ObjectHolder staticObj = new ObjectHolder();
        // 随着 Test 对象实例存放在 Java 堆
        ObjectHolder instanceObj = new ObjectHolder();

        void foo() {
            // 存在在 foo() 方法栈帧的局部变量表中
            ObjectHolder localObj = new ObjectHolder();
            // 设置断点
            System.out.println("done");
        }
    }

    public static void main(String[] args) {
        Test test = new JHSDB_TestCase.Test();
        test.foo();
    }
}