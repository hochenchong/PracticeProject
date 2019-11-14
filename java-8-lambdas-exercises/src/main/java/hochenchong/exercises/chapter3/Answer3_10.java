package hochenchong.exercises.chapter3;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Created by hochenchong on 2019/11/13
 *
 * 《Java 8 函数式编程》第三章 3.10 进阶练习
 *      书中网站答案注释：
 *          将数据从 acc 复制到新的列表实例。效率很低
 *          但 Stream.reduce 方法的协定要求累加器函数执行
 *          不更改其参数。
 *          Stream.collect 方法可以实现更有效的可变 reduction，
 *          但此练习要求显式使用 reduce 方法。
 */
public class Answer3_10 {
    /*
    1. 只用 reduce 和 Lambda 表达式写出实现 Stream 上的 map 操作的代码
        如果不想返回 Stream ，可以返回一个 List

        U result = identity;
        for (T element : this stream)
            result = accumulator.apply(result, element)
        return result;

        <U> U reduce(U identity,
             BiFunction<U, ? super T, U> accumulator,
             BinaryOperator<U> combiner);
        identity 初始值
        accumulator 累加器
        combiner 合并方式
     */
    public static <T, R> Stream<R> mapUsingReduce(Stream<T> stream, Function<T, R> function) {
        List<R> reduce = stream.reduce(new ArrayList<>(), (list, value) -> {
            list.add(function.apply(value));
            return list;
        }, (List<R> left, List<R> right) -> {
            left.addAll(right);
            return left;
        });
        return reduce.stream();
    }
    // 书中网站的答案：https://github.com/RichardWarburton/java-8-lambdas-exercises/blob/master/src/main/java/com/insightfullogic/java8/answers/chapter3/MapUsingReduce.java
    /*
public class MapUsingReduce {

    public static <I, O> List<O> map(Stream<I> stream, Function<I, O> mapper) {
        return stream.reduce(new ArrayList<O>(), (acc, x) -> {
        	// We are copying data from acc to new list instance. It is very inefficient,
        	// but contract of Stream.reduce method requires that accumulator function does
        	// not mutate its arguments.
        	// Stream.collect method could be used to implement more efficient mutable reduction,
        	// but this exercise asks to use reduce method.
        	List<O> newAcc = new ArrayList<>(acc);
        	newAcc.add(mapper.apply(x));
            return newAcc;
        }, (List<O> left, List<O> right) -> {
        	// We are copying left to new list to avoid mutating it.
        	List<O> newLeft = new ArrayList<>(left);
        	newLeft.addAll(right);
            return newLeft;
        });
    }

}
     */

    /*
    2. 只用 reduce 和 Lambda 表达式写出实现 Stream 上的 filter操作的代码
        如果不想返回 Stream ，可以返回一个 List 。
     */
    public static <T> Stream<T> filterUsingReduce(Stream<T> stream, Predicate<T> filter) {
        List<T> reduce = stream.reduce(new ArrayList<>(), (list, value) -> {
            if (filter.test(value)) {
                list.add(value);
            }
            return list;
        }, (List<T> left, List<T> right) -> {
            left.addAll(right);
            return left;
        });
        return reduce.stream();
    }
    // 书中网站的答案：https://github.com/RichardWarburton/java-8-lambdas-exercises/blob/master/src/main/java/com/insightfullogic/java8/answers/chapter3/FilterUsingReduce.java
    /*
public class FilterUsingReduce {

    public static <I> List<I> filter(Stream<I> stream, Predicate<I> predicate) {
        List<I> initial = new ArrayList<>();
        return stream.reduce(initial,
                             (List<I> acc, I x) -> {
                                if (predicate.test(x)) {
                                	// We are copying data from acc to new list instance. It is very inefficient,
                                	// but contract of Stream.reduce method requires that accumulator function does
                                	// not mutate its arguments.
                                	// Stream.collect method could be used to implement more efficient mutable reduction,
                                	// but this exercise asks to use reduce method explicitly.
                                	List<I> newAcc = new ArrayList<>(acc);
                                    newAcc.add(x);
                                    return newAcc;
                                } else {
                                	return acc;
                                }
                             },
                             FilterUsingReduce::combineLists);
    }

    private static <I> List<I> combineLists(List<I> left, List<I> right) {
    	// We are copying left to new list to avoid mutating it.
    	List<I> newLeft = new ArrayList<>(left);
    	newLeft.addAll(right);
        return newLeft;
    }
}
     */
}
