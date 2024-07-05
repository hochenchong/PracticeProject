package pattern11;

import utils.XMLTagNameConstant;
import utils.XMLUtil;

import java.util.Arrays;

/**
 * @author hochenchong
 * @date 2024/7/5
 */
public class AdapterClient {
    public static void main(String[] args) {
        ScoreOperation operation = (ScoreOperation) XMLUtil.getBean(XMLTagNameConstant.OPERATION_ADAPTER);
        assert operation != null;
        // 定义成绩
        int[] scores = {84, 76, 50, 69, 90, 91, 88, 96};
        int[] result;
        int score;

        System.out.println("成绩排序结果：");
        result = operation.sort(scores);
        // 遍历结果
        for (int i : result) {
            System.out.print(i + ", ");
        }

        System.out.println();
        System.out.println("查找成绩 90：");
        score = operation.search(result, 90);
        if (score != -1) {
            System.out.println("找到了");
        } else {
            System.out.println("没有找到了");
        }

        System.out.println("查找成绩 92：");
        score = operation.search(result, 92);
        if (score != -1) {
            System.out.println("找到了");
        } else {
            System.out.println("没有找到了");
        }
    }
}
