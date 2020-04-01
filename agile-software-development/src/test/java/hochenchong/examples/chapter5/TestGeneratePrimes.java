package hochenchong.examples.chapter5;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * 《敏捷软件开发》中程序 5.2
 */
public class TestGeneratePrimes {

    @Test
    public void testGeneratePrimesV2() {
        int[] nullArray = PrimeGeneratorV2.generatePrimes(0);
        assertEquals(nullArray.length, 0);

        int[] minArray = PrimeGeneratorV2.generatePrimes(2);
        assertEquals(minArray.length, 1);
        assertEquals(minArray[0], 2);

        int[] threeArray = PrimeGeneratorV2.generatePrimes(3);
        assertEquals(threeArray.length, 2);
        assertEquals(threeArray[0], 2);
        assertEquals(threeArray[1], 3);

        int[] centArray = PrimeGeneratorV2.generatePrimes(100);
        assertEquals(centArray.length, 25);
        assertEquals(centArray[24], 97);
    }

    @Test
    public void testGeneratePrimesV3() {
        int[] nullArray = PrimeGeneratorV3.generatePrimes(0);
        assertEquals(nullArray.length, 0);

        int[] minArray = PrimeGeneratorV3.generatePrimes(2);
        assertEquals(minArray.length, 1);
        assertEquals(minArray[0], 2);

        int[] threeArray = PrimeGeneratorV3.generatePrimes(3);
        assertEquals(threeArray.length, 2);
        assertEquals(threeArray[0], 2);
        assertEquals(threeArray[1], 3);

        int[] centArray = PrimeGeneratorV3.generatePrimes(100);
        assertEquals(centArray.length, 25);
        assertEquals(centArray[24], 97);
    }

    @Test
    public void testGeneratePrimesV4() {
        int[] nullArray = PrimeGeneratorV4.generatePrimes(0);
        assertEquals(nullArray.length, 0);

        int[] minArray = PrimeGeneratorV4.generatePrimes(2);
        assertEquals(minArray.length, 1);
        assertEquals(minArray[0], 2);

        int[] threeArray = PrimeGeneratorV4.generatePrimes(3);
        assertEquals(threeArray.length, 2);
        assertEquals(threeArray[0], 2);
        assertEquals(threeArray[1], 3);

        int[] centArray = PrimeGeneratorV4.generatePrimes(100);
        assertEquals(centArray.length, 25);
        assertEquals(centArray[24], 97);
    }

    @Test
    public void testGeneratePrimesV5() {
        int[] nullArray = PrimeGeneratorV5.generatePrimes(0);
        assertEquals(nullArray.length, 0);

        int[] minArray = PrimeGeneratorV5.generatePrimes(2);
        assertEquals(minArray.length, 1);
        assertEquals(minArray[0], 2);

        int[] threeArray = PrimeGeneratorV5.generatePrimes(3);
        assertEquals(threeArray.length, 2);
        assertEquals(threeArray[0], 2);
        assertEquals(threeArray[1], 3);

        int[] centArray = PrimeGeneratorV5.generatePrimes(100);
        assertEquals(centArray.length, 25);
        assertEquals(centArray[24], 97);
    }

    @Test
    public void testGeneratePrimes() {
        int[] nullArray = PrimeGenerator.generatePrimes(0);
        assertEquals(nullArray.length, 0);

        int[] minArray = PrimeGenerator.generatePrimes(2);
        assertEquals(minArray.length, 1);
        assertEquals(minArray[0], 2);

        int[] threeArray = PrimeGenerator.generatePrimes(3);
        assertEquals(threeArray.length, 2);
        assertEquals(threeArray[0], 2);
        assertEquals(threeArray[1], 3);

        int[] centArray = PrimeGenerator.generatePrimes(100);
        assertEquals(centArray.length, 25);
        assertEquals(centArray[24], 97);
    }

    // 程序 5.8 的测试，检查在 2~500 之间所产生的素数列表中没有倍数存在
    @Test
    public void testExhaustive() {
        for (int i = 2; i < 500; i++) {
            verifyPrimeList(PrimeGenerator.generatePrimes(i));
        }
    }

    private void verifyPrimeList(int[] list) {
        for (int i = 0; i < list.length; i++) {
            verifyPrime(list[i]);
        }
    }

    private void verifyPrime(int n) {
        for (int factor = 2; factor < n; factor++) {
            assert(n % factor != 0);
        }
    }
}
