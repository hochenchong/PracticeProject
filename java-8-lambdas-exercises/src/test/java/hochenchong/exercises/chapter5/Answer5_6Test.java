package hochenchong.exercises.chapter5;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

/**
 * Created by hochenchong on 2019/12/06
 */

public class Answer5_6Test {

    /*
    1. 方法引用，回顾第三章中的例子，使用方法引用改写以下方法
        a. 转换大写的 map 方法
        例子：3-9
     */
    @Test
    public void toUpperCase() {
        List<String> list = Arrays.asList("a", "b", "hello").stream().map(String::toUpperCase).collect(Collectors.toList());
        assertEquals(Arrays.asList("A", "B", "HELLO"), list);
    }

    /*
    1. b. 使用 reduce 实现 count 方法
    例子 3-16
     */
    @Test
    public void testCount() {
        int count = Stream.of(1, 2, 3).reduce(0, Integer::sum);
        Assert.assertEquals(6, count);
    }

    /*
    1. c. 使用 flatMap 连接列表
    例子 3-12
     */
    @Test
    public void testFlatMap() {
        List<Integer> together = Stream.of(Arrays.asList(1, 2), Arrays.asList(3, 4))
                .flatMap(List::stream)
                .collect(Collectors.toList());
        assertEquals(Arrays.asList(1, 2, 3, 4), together);
    }

    /*
    2.收集器
        a. 找出名字最长的艺术家，分别使用收集器和 reduce 高阶函数
     */
    @Test
    public void testGetLongestNameByCollecting() {
        List<String> list = Arrays.asList("John Lennon", "Paul McCartney",
                "George Harrison", "Ringo Starr", "Pete Best", "Stuart Sutcliffe");
        assertEquals("Stuart Sutcliffe", LongestName.getLongestNameByCollecting(list));
        assertEquals(null, LongestName.getLongestNameByCollecting(new ArrayList<>()));
    }
    
    @Test
    public void testGetLongestNameByReduce() {
        List<String> list = Arrays.asList("John Lennon", "Paul McCartney",
                "George Harrison", "Ringo Starr", "Pete Best", "Stuart Sutcliffe");
        assertEquals("Stuart Sutcliffe", LongestName.getLongestNameByReduce(list));
        assertEquals(null, LongestName.getLongestNameByReduce(new ArrayList<>()));
    }

    /*
    2.b 假设一个元素为单词的流，计算每个单词出现的次数
     */
    @Test
    public void testWordCount() {
        List<String> list = Arrays.asList("John", "Paul", "George", "John", "Paul", "John");
        Map<String, Long> map = WordCount.getWordCount(list);
        assertEquals("{George=1, John=3, Paul=2}", map.toString());
    }

    /*
    2.c 用一个定制的收集器实现 Collectors.groupingBy 方法，不需要提供一个下游收集器，只需实现一个最简单的即可
    提示：可从下面这行代码开始：
        public class GroupingBy<T, K> implements Collector<T, Map<K, List<T>>, Map<K, List<T>>>
     */
    @Test
    public void testGroupingBy() {
        List<String> list = Arrays.asList("John", "Paul", "George", "John", "Paul", "John");
        Map<String, List<String>> collect = list.stream().collect(new GroupingBy<String, String>(string -> string));
        assertEquals("{George=[George], John=[John, John, John], Paul=[Paul, Paul]}", collect.toString());
    }

    /*
    3. 改进 Map
    使用 Map 的 computeIfAbsent 方法高效计算斐波那契数列。这里的 “高效” 是指避免将那些较小的序列重复计算多次。

    斐波那契数列，又称黄金分割数列，指的是这样一个数列： 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946, 17711, 28657, 46368, ...
    在数学上，斐波纳契数列以如下被以递归的方法定义：F（0）=0，F（1）=1，F（n）=F(n-1)+F(n-2)（n≥2，n∈N*）
        特别指出：第 0 项是 0，第 1项是第一个 1。
     */
    @Test
    public void testFibonacci() {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            string.append(Fibonacci.fibonacci(i)).append(", ");
        }
        assertEquals("0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, ", string.toString());
    }
}