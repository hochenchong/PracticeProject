package hochenchong.exercises.chapter6;

import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by hochenchong on 2019/12/10
 */

public class Answer6_9 {
    /*
    1. 例 6-10 中的代码顺序求流中元素的平方和，将其改为并行处理。

    例 6-10　顺序求列表中数字的平方和
    public static int sequentialSumOfSquares(IntStream range) {
        return range.map(x -> x * x).sum();
    }
     */
    public static int parallelSumOfSquares(IntStream range) {
        return range.parallel().map(x -> x * x).sum();
    }

    /*
    2. 例 6-11 中的代码把列表中的数字相乘，然后再将所得结果乘以 5。顺序执行这段程序没
        有问题，但并行执行时有一个缺陷，使用流并行化执行该段代码，并修复缺陷。

        例 6-11　把列表中的数字相乘，然后再将所得结果乘以 5，该实现有一个缺陷
        public static int multiplyThrough(List<Integer> linkedListOfNumbers) {
            return linkedListOfNumbers.stream().reduce(5, (acc, x) -> x * acc);
        }
     */
    // identity 为了让其在并行化时能工作正常，初值必须为组合函数的恒等值。拿恒等值和其他值做 reduce 操作时，其他值保持不变
    public static int multiplyThrough(List<Integer> numbers) {
        return 5 * numbers.parallelStream().reduce(1, (acc, x) -> x * acc);
    }

    /*
    3. 例 6-12 中的代码计算列表中数字的平方和。尝试改进代码性能，但不得牺牲代码质量。只需要一些简单的改动即可。

    例 6-12　求列表元素的平方和，该实现方式性能不高
    public int slowSumOfSquares() {
        return linkedListOfNumbers.parallelStream()
        .map(x -> x * x)
        .reduce(0, (acc, x) -> acc + x);
    }
     */

    public int sumOfSquares(List<Integer> linkedListOfNumbers) {
        return linkedListOfNumbers.parallelStream()
                .mapToInt(x -> x * x).sum();
    }
}
