package hochenchong.exercises.chapter3;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by hochenchong on 2019/11/13
 */
public class Answer3_10Test {

    /*
        使用 reduce 实现 map 功能
     */
    @Test
    public void testMapUsingReduce() {
        List<String> stringList = Arrays.asList("zhangsan", "lisi", "wangwu");
        Function<String, Integer> mapper = value -> value.length();

        Stream<Integer> integerStream = Answer3_10.mapUsingReduce(stringList.stream(), mapper);

        Assert.assertEquals(stringList.stream().map(mapper).collect(Collectors.toList()), integerStream.collect(Collectors.toList()));
    }

    /*
        使用 reduce 实现 filter 功能
     */
    @Test
    public void filterUsingReduce() {
        List<String> stringList = Arrays.asList("zhangsan", "lisi", "wangwu");
        Predicate<String> filter = value -> value.length() < 7;

        Stream<String> stream = Answer3_10.filterUsingReduce(stringList.stream(), filter);

        Assert.assertEquals(stringList.stream().filter(filter).collect(Collectors.toList()), stream.collect(Collectors.toList()));
    }
}