package chapter02;

/**
 * 2.11 伪共享
 *     缓存局部性原理
 *
 * @author hochenchong
 * @date 2024/5/31
 */
public class TestForContent {
    static final int LINE_NUM = 1024;
    static final int COLUM_NUM = 1024;

    public static void main(String[] args) {
        long[][] array = new long[LINE_NUM][COLUM_NUM];
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < LINE_NUM; i++) {
            for (int j = 0; j < COLUM_NUM; j++) {
                array[i][j] = i * 2 + j;
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("cache time:" + (endTime - startTime));

        long[][] array2 = new long[LINE_NUM][COLUM_NUM];
        startTime = System.currentTimeMillis();
        for (int i = 0; i < LINE_NUM; i++) {
            for (int j = 0; j < COLUM_NUM; j++) {
                array2[j][i] = i * 2 + j;
            }
        }
        endTime = System.currentTimeMillis();
        System.out.println("no cache time:" + (endTime - startTime));
    }
}
