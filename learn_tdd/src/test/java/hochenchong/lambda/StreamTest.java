package hochenchong.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author HochenChong
 * @date 2019/09/28
 */
public class StreamTest {
    @Test
    public void testStream() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);

        System.out.println(list.stream().map(i -> i * 2).reduce(0, Integer::sum));

        System.out.println("-------------------");
        IntStream intStream = IntStream.of(1, 2, 3, 4, 5, 6);
        System.out.println(intStream.map(i -> i * 2).sum());


        // 如果没有 collect 终止操作，其它操作则不会运行
        List<String> stringList = Arrays.asList("hello", "world", "!");
        System.out.println(stringList.stream().map(value -> {
            String concat = value.substring(0, 1).toUpperCase() + value.substring(1);
            System.out.println("Test");
            return concat;
        }).collect(Collectors.joining(" ")));

        System.out.println("--------------------");
        // 程序不会结束，distinct 会一直等待着
        // IntStream.iterate(0, i -> (i + 1) % 2).distinct().limit(6).forEach(System.out::println);
        IntStream.iterate(0, i -> (i + 1) % 2).limit(6).distinct().forEach(System.out::println);
    }

    @Test
    public void testStreamSum() {
        /*
            Stream.iterate(1, value -> value + 2).limit(6)
                找出该流中大于 2 的元素，然后将每个元素乘以 2，然后忽略掉流中的前两个元素
                然后再取流中的前两个元素，最后求出流中元素的总和
        */

        /*
        AtomicInteger sum = new AtomicInteger();
        Stream.iterate(1, value -> value + 2).limit(6).filter(value -> value > 2).map(value -> value * 2)
                .skip(2).limit(2).forEach(value -> sum.addAndGet(value));
        */

        /*
        final int[] sum = {0};
        Stream.iterate(1, value -> value + 2).limit(6).filter(value -> value > 2).map(value -> value * 2)
                .skip(2).limit(2).forEach(value -> sum[0] += value);
        System.out.println(sum[0]);
        */

        /*
        int sum = Stream.iterate(1, value -> value + 2).limit(6).filter(value -> value > 2).mapToInt(value -> value * 2)
                .skip(2).limit(2).sum();
        System.out.println(sum);
        */

        IntSummaryStatistics intSummaryStatistics = Stream.iterate(1, value -> value + 2).limit(6).filter(value -> value > 2).mapToInt(value -> value * 2)
                .skip(2).limit(2).summaryStatistics();
        // System.out.println(intSummaryStatistics.getMin());
        // System.out.println(intSummaryStatistics.getMax());
        System.out.println(intSummaryStatistics.getSum());
    }
    
    /*
    List 分批处理
     */
    @Test
    public void testListBatch() {
        List<String> list = Arrays.asList("a", "b", "c", "d", "e", "f", "i", "a", "y");

        int batchSize = 3;
        int batchNum = (list.size() + batchSize - 1) / batchSize;
        Stream.iterate(0, i -> i + 1).limit(batchNum).forEach(i -> {
            String collect = list.stream().distinct().skip(i * batchSize).limit(batchSize).collect(Collectors.joining(", "));
            System.out.println(collect);
        });
    }

}
