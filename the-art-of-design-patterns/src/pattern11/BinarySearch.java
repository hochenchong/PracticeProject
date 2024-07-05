package pattern11;

/**
 * 二分查找类：被适配者
 *
 * @author hochenchong
 * @date 2024/7/5
 */
public class BinarySearch {
    public int binarySearch(int[] a, int num) {
        if (a.length == 0) return -1;

        int startPos = 0;
        int endPos = a.length - 1;
        int m = (startPos + endPos) / 2;

        while (startPos <= endPos) {
            if (a[m] == num) return m + 1; // 该数排在第几个位置
            if (a[m] < num) {
                startPos = m + 1;
            }
            if (a[m] > num) {
                endPos = m - 1;
            }

            m = (startPos + endPos) / 2;
        }

        return -1; // 如果没有此数，则返回 -1
    }
}
