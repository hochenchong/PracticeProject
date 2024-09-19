package jvm.chapter02;

import java.util.HashSet;
import java.util.Set;

/**
 * 运行时常量池导致的内存溢出异常
 * VM Args：-XX:PermSize=6M -XX:MaxPermSize=6M
 * 在 JDK 6 环境下运行
 *
 * @author hochenchong
 * @date 2024/09/19
 */
public class RuntimeConstantPoolOOM {
    public static void main(String[] args) {
        // 使用 Set 保持着常量池引用，避免 Full GC 回收常量池行为
        Set<String> set = new HashSet<>();
        // 在 short 范围内足以让 6MB 的 PermSize 产生 OOM 了
        short i = 0;
        while (true) {
            set.add(String.valueOf(i++).intern());
        }
    }
}
