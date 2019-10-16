package hochenchong.exercises.chapter2;

import javax.swing.text.DateFormatter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author HochenChong
 * @date 2019/10/16
 *
 * 输出结果（不一定与以下结果相同，不过 5 个是 dd-MMM-yyyy 格式的，5 个是 dd-MM-yyyy 格式的）：
 *      16-10-2019
 *      16-10-2019
 *      16-10-2019
 *      16-十月-2019
 *      16-10-2019
 *      16-十月-2019
 *      16-十月-2019
 *      16-十月-2019
 *      16-10-2019
 *      16-十月-2019
 */

public class Question2 {
    public final static ThreadLocal<DateFormatter> dateFormatterThreadLocal = ThreadLocal.withInitial(() -> new DateFormatter(new SimpleDateFormat("dd-MMM-yyyy")));

    public static void main(String[] args) {
        Date date = new Date();
        ThreadLocal<DateFormatter> dateFormatterThreadLocal = Question2.dateFormatterThreadLocal;
        for (int i = 0; i < 10; i++) {
            int j = i;
            new Thread(() -> {
                if (j % 2 == 0) {
                    dateFormatterThreadLocal.get().setFormat(new SimpleDateFormat("dd-MM-yyyy"));
                }
                String format = dateFormatterThreadLocal.get().getFormat().format(date);
                System.out.println(format);
            }).start();
        }
    }
}
