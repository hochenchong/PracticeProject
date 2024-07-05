package pattern11;

/**
 * 抽象成绩操作类
 *
 * @author hochenchong
 * @date 2024/7/5
 */
public interface ScoreOperation {
    // 成绩排序
    int[] sort(int[] array);
    // 成绩查找
    int search(int[] array, int key);
}
