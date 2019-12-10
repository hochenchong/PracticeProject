package hochenchong.exercises.chapter5;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * Created by hochenchong on 2019/12/09
 */

public class GroupingBy<T, K> implements Collector<T, Map<K, List<T>>, Map<K, List<T>>> {
    private final Function<? super T, ? extends K> classifier;

    public GroupingBy(Function<? super T, ? extends K> classifier) {
        this.classifier = classifier;
    }

    private static final Set<Characteristics> CHARACTERISTICS = new HashSet<>();
    static {
        CHARACTERISTICS.add(Characteristics.IDENTITY_FINISH);
    }

    // 一个创建和返回一个新的可变结果容器的函数
    @Override
    public Supplier<Map<K, List<T>>> supplier() {
        return HashMap::new;
    }

    // 将值折叠到可变结果容器中的函数
    @Override
    public BiConsumer<Map<K, List<T>>, T> accumulator() {
        return (map, value) -> {
            K key = classifier.apply(value);
            List<T> list = map.computeIfAbsent(key, k -> new ArrayList<>());
            list.add(value);
        };
    }

    // 合并两个结果集为一个
    @Override
    public BinaryOperator<Map<K, List<T>>> combiner() {
        /*
         Java 8 为 java.util.Map 接口新增了 merge() 函数
            default V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction)
            key：给定的 key
            value：如果 map 中，对该指定的 key 没有关联的值或者关联的值为 null，则将该指定的 key 与 value 关联
            remappingFunction：重映射函数，如果对该指定的 key，在 map 中已有关联的值，则使用该重映射函数（对原本的关联值与新的 value 值进行处理）的结果替换原本的值
         */

        return (left, right) -> {
            right.forEach((key, value) -> {
                left.merge(key, value, (leftValue, rightValue) -> {
                    leftValue.addAll(rightValue);
                    return leftValue;
                });
            });
            return left;
        };
    }

    // 将中间结果转换为最终结果的函数
    @Override
    public Function<Map<K, List<T>>, Map<K, List<T>>> finisher() {
        return map -> map;
    }

    /*
    如果 finisher 方法其实是 identity 函数：它返回传入参数的值。如果这样，收集器就展现出 IDENTITY_FINISH 的特征，需要使用 characteristics 方法声明。
     */
    @Override
    public Set<Characteristics> characteristics() {
        return CHARACTERISTICS;
    }
}
