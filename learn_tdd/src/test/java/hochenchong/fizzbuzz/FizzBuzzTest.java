package hochenchong.fizzbuzz;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author HochenChong
 * @date 2019/09/04
 */

public class FizzBuzzTest {
    // 题目：打印出从 1 到 100 的数字，将其中 3 的倍数替换成 “Fizz”，5 的倍数替换成 “Buzz”。既能被 3 整除、又能被 5 整除的数则替换成 “FizzBuzz”
    private void checkFizzBuzz(int num, String output) {
        FizzBuzz fizzBuzz = new FizzBuzz(num);
        assertThat(fizzBuzz.toString(), is(output));
    }

    @Test
    public void test_output_num() {
        checkFizzBuzz(1, "1");
    }

    @Test
    public void test_input_3_output_fizz() {
        checkFizzBuzz(3, "Fizz");
    }

    @Test
    public void test_input_5_output_buzz() {
        checkFizzBuzz(5, "Buzz");
    }

    @Test
    public void test_input_15_output_FizzBuzz() {
        checkFizzBuzz(15, "FizzBuzz");
    }

    @Test
    public void test_input_All() {
        for (int i = 1; i <= 100; i++) {
            System.out.println(new FizzBuzz(i));
        }
    }
}
