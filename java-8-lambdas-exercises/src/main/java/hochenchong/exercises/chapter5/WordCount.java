package hochenchong.exercises.chapter5;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by hochenchong on 2019/12/09
 */

public class WordCount {
    public static Map<String, Long> getWordCount(List<String> list) {
        return list.stream().collect(Collectors.groupingBy(word -> word, Collectors.counting()));
    }
}
