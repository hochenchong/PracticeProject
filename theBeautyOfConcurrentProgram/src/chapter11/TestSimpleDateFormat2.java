package chapter11;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 11.4 SimpleDateFormat 是线程不安全的
 *     使用 ThreadLocal 来避免
 *     ThreadLocal 使用完毕后，要及时 remove，避免内存泄漏问题
 *         ThreadLocalMap 的 key 是弱引用，被回收后，变为 null，而 value 仍然存在，并且无法被访问到，从而造成内存泄漏。
 *
 * @author hochenchong
 * @date 2024/6/4
 */
public class TestSimpleDateFormat2 {
    static ThreadLocal<SimpleDateFormat> safeSdf = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    public static void main(String[] args) {
        //  以下代码可能会报：java.lang.NumberFormatException
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                try {
                    System.out.println(safeSdf.get().parse("2024-06-04 11:35:35"));
                } catch (ParseException e) {
                    e.printStackTrace();
                } finally {
                    // 使用完毕后及时清除，避免内存泄漏
                    safeSdf.remove();
                }
            });
            thread.start();
        }
    }
}
