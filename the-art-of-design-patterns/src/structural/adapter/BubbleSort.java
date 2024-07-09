package structural.adapter;

/**
 * 冒泡排序类：被适配者
 *     直接拿以前写好的排序算法即可
 *
 * @author hochenchong
 * @date 2024/7/5
 */
public class BubbleSort {
    /**
     * 冒泡排序 - 从小到大排序
     *
     * @param array
     */
    public void bubbleSortOrderByAsc(int[] array) {
        // 数组为 null 或者小于等于 1 时直接返回
        if (null == array || array.length <= 1) {
            return;
        }

        for (int i = 0; i < array.length; i++) {
            // 用来标识是否有数据交换，false 表示这一轮没有数据交换，true 反之
            boolean flag = false;
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j+1]) {
                    int tmp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = tmp;
                    // 有数据交换则设置为 true
                    flag = true;
                }
            }

            // 如果没有数据交换，则提前退出
            if (!flag) {
                break;
            }
        }
    }
}
