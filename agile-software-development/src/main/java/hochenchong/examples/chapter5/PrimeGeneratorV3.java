package hochenchong.examples.chapter5;

/**
 * 《敏捷软件开发》中程序 5.4 PrimeGenerator 版本 3
 */

public class PrimeGeneratorV3 {
    private static boolean[] f;
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

        // how many primes are there?
        int count = 0;
        for (i = 0; i < f.length; i++) {
            if (f[i]) {
                // bump count.
                count++;
            }
        }

        result = new int[count];

        // move the primes into the result
        for (i = 0, j = 0; i < f.length; i++) {
            // if prime
            if (f[i]) {
                result[j++] = i;
            }
        }
    }

    private static void crossOutMultiples() {
        int i;
        int j;
        for (i = 2; i < Math.sqrt(f.length) + 1; i++) {
            // if i is uncrossed, cross out its multiples.
            if (f[i]) {
                for (j = 2 * i; j < f.length; j += i) {
                    // multiple is not prime
                    f[j] = false;
                }
            }
        }
    }

    private static void initializeArrayOfIntegers(int maxValue) {
        f = new boolean[maxValue + 1];
        f[0] = f[1] = false;
        // initialize array to true
        for (int i = 2; i < f.length; i++) {
            f[i] = true;
        }
    }
}
