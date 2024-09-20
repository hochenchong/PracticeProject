package jvm.chapter03;

/**
 * 大对象直接进入老年代
 * VM Args：-verbose:gc -Xms20M -Xmx20M -Xmn10M -Xlog:gc* -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728
 * 注意：-XX:PretenureSizeThreshold 只对 Serial 和 ParNew 两款新生代收集器有效
 *
 * @author hochenchong
 * @date 2024/09/20
 */
public class BigObjectTest {
    private static final int _1MB = 1024 * 1024;

    public static void testPretenureSizeThreshold() {
        byte[] allocation;
        // 直接分配在老年代中
        allocation = new byte[4 * _1MB];
    }

    public static void main(String[] args) {
        testPretenureSizeThreshold();
    }
}
