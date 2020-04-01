package hochenchong.examples.chapter5;

/**
 * 《敏捷软件开发》中程序 5.3 PrimeGenerator 版本 2
 */

public class PrimeGeneratorV2 {
    // 书中应该是印刷错误了，将 s 印刷为 a
    private static int s;
    private static boolean[] f;
    private static int[] primes;

    public static int[] generatePrimes(int maxValue) {
        if (maxValue < 2) {
            return new int[0];
        } else {
            initializeSieve(maxValue);
            sieve();
            loadPrimes();
            // return the primes
            return primes;
        }
    }

    private static void loadPrimes() {
        int i;
        int j;

        // how many primes are there?
        int count = 0;
        for (i = 0; i < s; i++) {
            if (f[i]) {
                // bump count.
                count++;
            }
        }

        primes = new int[count];

        // move the primes into the result
        for (i = 0, j = 0; i < s; i++) {
            // if prime
            if (f[i]) {
                primes[j++] = i;
            }
        }
    }

    private static void sieve() {
        int i;
        int j;
        for (i = 2; i < Math.sqrt(s) + 1; i++) {
            // if i is uncrossed, cross out its multiples.
            if (f[i]) {
                for (j = 2 * i; j < s; j += i) {
                    // multiple is not prime
                    f[j] = false;
                }
            }
        }
    }

    private static void initializeSieve(int maxValue) {
        // declarations
        // size of array
        s = maxValue + 1;
        f = new boolean[s];
        int i;

        // initialize array to true
        for (i = 0; i < s; i++) {
            f[i] = true;
        }

        // get rid of known non-primes
        f[0] = f[1] = false;
    }
}
