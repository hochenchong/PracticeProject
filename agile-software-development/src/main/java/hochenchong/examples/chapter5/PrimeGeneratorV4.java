package hochenchong.examples.chapter5;

/**
 * 《敏捷软件开发》中程序 5.5 PrimeGenerator 版本 4
 *
 * 相比与前几个版本，该版本中，数组 isCrossed 里的 true 表示该下标为不是素数
 */

public class PrimeGeneratorV4 {
    private static boolean[] isCrossed;
    private static int[] result;

    public static int[] generatePrimes(int maxValue) {
        if (maxValue < 2) {
            return new int[0];
        } else {
            initializeArrayOfIntegers(maxValue);
            crossOutMultiples();
            putUncrossedIntegerIntoResult();
            // return the primes
            return result;
        }
    }

    private static void putUncrossedIntegerIntoResult() {
        int i;
        int j;

        int count = 0;
        for (i = 2; i < isCrossed.length; i++) {
            // 没有被筛选过的值为素数
            if (notCrossed(i)) {
                count++;
            }
        }

        result = new int[count];

        for (i = 2, j = 0; i < isCrossed.length; i++) {
            if (notCrossed(i)) {
                result[j++] = i;
            }
        }
    }

    private static void initializeArrayOfIntegers(int maxValue) {
        isCrossed = new boolean[maxValue + 1];
        // isCrossed[0] = isCrossed[1] = false;
        // 这里的初始化为 false 的循环其实可以省略，默认值即为 false，不过为了保持和书中的例子一致，不做修改
        for (int i = 2; i < isCrossed.length; i++) {
            isCrossed[i] = false;
        }
    }

    private static void crossOutMultiples() {
        int maxPrimeFactor = calcMaxPrimeFactor();
        for (int i = 2; i <= maxPrimeFactor; i++) {
            if (notCrossed(i)) {
                crossOutMultiplesOf(i);
            }
        }
    }

    private static int calcMaxPrimeFactor() {
        // We cross out all multiples of p; where p is prime.
        // Thus, all crossed out multiples have p and q for
        // factors. If p > sqrt of the size of the array, then
        // q will never be greater than 1. Thus p is the
        // largest prime factor in the array, and is also
        // the iteration limit.
        // 数组长度的平方根是数组中任意整数的最大素因子（这里其实没必要 + 1，不过为了保持和书中的一致）
        double maxPrimeFactor = Math.sqrt(isCrossed.length) + 1;
        return (int) maxPrimeFactor;
    }

    private static void crossOutMultiplesOf(int i) {
        // 过滤掉 i 的倍数值
        for (int multiple = 2 * i; multiple < isCrossed.length; multiple += i) {
            isCrossed[multiple] = true;
        }
    }

    private static boolean notCrossed(int i) {
        return isCrossed[i] == false;
    }
}
