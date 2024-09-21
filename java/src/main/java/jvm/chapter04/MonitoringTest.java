package jvm.chapter04;

import java.util.ArrayList;
import java.util.List;

/**
 * 代码清单 4-7 JConsole 监视代码
 * VM Args：-Xms100m -Xmx100m -XX:+UseSerialGC
 *
 * @author hochenchong
 * @date 2024/09/20
 */
public class MonitoringTest {
    /**
     * 内存占位符对象，一个 OOMObject 大约占 64 KB
     */
    static class OOMObject {
        public byte[] placeholder = new byte[64 * 1024];
    }

    public static void fillHead(int num) throws InterruptedException {
        List<OOMObject> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            // 稍作延时，令监视曲线的变化更加明显
            Thread.sleep(50);
            list.add(new OOMObject());
        }
        System.gc();
    }

    public static void main(String[] args) throws Exception {
        fillHead(1000);
    }
}
