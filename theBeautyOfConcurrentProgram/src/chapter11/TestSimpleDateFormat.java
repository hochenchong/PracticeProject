package chapter11;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 11.4 SimpleDateFormat 是线程不安全的
 *     使用 synchronized 来避免
 *
 * @author hochenchong
 * @date 2024/6/4
 */
public class TestSimpleDateFormat {
    static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        //  以下代码可能会报：java.lang.NumberFormatException
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                try {
                    System.out.println(sdf.parse("2024-06-04 11:35:35"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            });
            thread.start();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("--------");

        // 不报错的方式，使用 synchronized
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                try {
                    synchronized (sdf) {
                        System.out.println(sdf.parse("2024-06-04 11:35:35"));
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            });
            thread.start();
        }
    }
}
