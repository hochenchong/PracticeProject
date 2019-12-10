package hochenchong.exercises.chapter5;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

/**
 * Created by hochenchong on 2019/12/06
 */

public class LongestName {
    public static String getLongestNameByCollecting(List<String> names) {
        Function<String, Integer> getCount = s -> s.length();
        // names.stream().collect(Collectors.maxBy(Comparator.comparing(getCount)));
        return names.stream().max(Comparator.comparing(getCount)).orElse(null);
    }

    public static String getLongestNameByReduce(List<String> names) {
        return names.stream().reduce((a, b) -> a.length() - b.length() > 0 ? a : b).orElse(null);
    }
}
