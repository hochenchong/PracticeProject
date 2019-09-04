package hochenchong.fizzbuzz;

/**
 * @author HochenChong
 * @date 2019/09/04
 */
public class FizzBuzz {
    private int num;

    public FizzBuzz(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        if (isDivision(3) && isDivision(5)) {
            return "FizzBuzz";
        }

        if (isDivision(3)) {
            return "Fizz";
        }

        if (isDivision(5)) {
            return "Buzz";
        }
        return Integer.toString(this.num);
    }

    // 是否能被 i 整除
    private boolean isDivision(int i) {
        return this.num % i == 0;
    }

    // 被 i 整除，或者包含 i 的也输出的话
    private boolean isRelated(int i) {
        return this.num % i == 0 || Integer.toString(this.num).contains(Integer.toString(i));
    }
}
