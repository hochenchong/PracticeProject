package hochenchong.examples.chapter5;

/**
 * This class Generates prime numbers up to a user specified maximum.
 * the algorithm used is the Sieve of Eratosthenes.
 * <p>
 * Eratosthenes of Cyrene, b. c. 276 BC, Cyrene, Libya --
 * d. c. 194, Alexandria. The first man to calculate the circumference
 * of the Earth. Also known for working on calendars with leap
 * years and ran the library at Alexandria.
 * <p>
 * The algorithm is quite simple. Given an array of integers starting
 * at 2. Cross out all multiples of 2. Find the next uncrossed
 * integer, and cross out all of its multiples. Repeat until
 * you have passed the square root of the maximum value.
 *
 * @author Robert C. Martin
 * @version 9 Dec 1999 rcm
 */

public class GeneratePrimes {
    public static int[] generatePrimes(int maxValue) {
        // the only valid case
        if (maxValue >= 2) {
            // declarations
            // size of array
            int s = maxValue + 1;
            boolean[] f = new boolean[s];
            int i;

            // initialize array to true
            for (i = 0; i < s; i++) {
                f[i] = true;
            }

            // get rid of known non-primes
            f[0] = f[1] = false;

            //sieve
            int j;
            for (i = 2; i < Math.sqrt(s) + 1; i++) {
                for (j = 2 * i; j < s; j += i) {
                    // multiple is not prime
                    f[j] = false;
                }
            }

            // how many primes are there?
            int count = 0;
            for (i = 0; i < s; i++) {
                if (f[i]) {
                    // bump count.
                    count++;
                }
            }

            int[] primes = new int[count];

            // move the primes into the result
            for (i = 0, j = 0; i < s; i++) {
                // if prime
                if (f[i]) {
                    primes[j++] = i;
                }
            }

            // return the primes
            return primes;
        } else {
            // maxValue < 2
            // return null array if bad input
            return new int[0];
        }
    }
}
