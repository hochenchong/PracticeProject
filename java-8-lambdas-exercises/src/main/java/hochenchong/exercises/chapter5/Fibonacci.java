package hochenchong.exercises.chapter5;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hochenchong on 2019/12/10
 */

public class Fibonacci {
    private static final Map<Integer, Long> FIBONACCIMap = new HashMap<>();
    static {
        FIBONACCIMap.put(0, 0L);
        FIBONACCIMap.put(1, 1L);
    }

    public static Long fibonacci(int i) {
        return FIBONACCIMap.computeIfAbsent(i, n -> fibonacci(n - 1) + fibonacci(n - 2));
    }
}
