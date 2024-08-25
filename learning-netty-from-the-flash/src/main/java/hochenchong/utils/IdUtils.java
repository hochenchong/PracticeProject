package hochenchong.utils;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author hochenchong
 * @date 2024/08/24
 */
public class IdUtils {
    private static final AtomicInteger USER_ID = new AtomicInteger(1);

    private static final AtomicInteger GROUP_ID = new AtomicInteger(10000);

    public static String getUserId() {
        return String.valueOf(USER_ID.getAndIncrement());
    }

    public static String getGroupId() {
        return String.valueOf(GROUP_ID.getAndIncrement());
    }
}
