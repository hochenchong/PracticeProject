package hochenchong.demo01;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.util.StringUtils;
import hochenchong.DataEntity;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author HochenChong
 * @date 2020-03-22
 * @description 周报合并工具 v1.1 版本的主类
 * <p>
 * 更新说明：
 * 1）在 Excel 的第一列新增一列：项目
 * 2）根据项目进行分组排列显示，并将相同的项目名合并单元格
 * 3）使用了 EasyExcel 的 WriteTable 来实现分组合并单元格的功能
 */

public class Demo01App {

    // 存放全部 Sheet1 的数据
    private static List<DataEntity> list1 = new ArrayList<>();
    // 存放全部 Sheet2 的数据
    private static List<DataEntity> list2 = new ArrayList<>();

    public static void main(String[] args) {
        if (args.length < 1 || StringUtils.isEmpty(args[0])) {
            System.out.println("请在运行命令后加上要处理的周报目录！");
            return;
        }

        String directoryPath = args[0];
        // 判断是否是个目录
        File file = new File(directoryPath);
        if (!file.exists() || !file.isDirectory()) {
            System.out.println("请输入正确的要处理的周报目录！");
            return;
        }

        // 版权信息
        System.out.println("------- 周报合并工具 v1.1 By HochenChong 2020/03/22 -------");

        // 获取要处理的 excel 列表
        List<String> excelFilePaths = getExcelFilePaths(directoryPath);

        // 读取要处理的 Excel 列表，读取表一和表二的数据，并存起来
        for (String filePath : excelFilePaths) {
            System.out.println("--- 正在处理：" + filePath + " ----");

            for (int i = 0; i < 2; i++) {
                List<DataEntity> list = EasyExcel.read(filePath).head(DataEntity.class).sheet(i).doReadSync();
                getList(i).addAll(list);
            }
        }

        // 对数据进行处理，将 project 为空的数据补全
        list1 = processListData(list1);
        list2 = processListData(list2);

        // 写入新建的 Excel 中
        ExcelUtils excelUtils = new ExcelUtils();
        for (int i = 0; i < 2; i++) {
            excelUtils.writerSheet(i, getList(i));
        }
        // 关闭资源
        excelUtils.close();
    }

    private static List getList(int i) {
        if (i == 0) {
            return list1;
        } else {
            return list2;
        }
    }

    /**
     * 处理数据，当某一个 DataEntity 中的 project 属性为空时，则设置为上一个 DataEntity 的 project 属性的值
     * 为空的情况：读取 Excel 时，project 为合并的单元格时，第一个有值，后面的没有
     *
     * @param list
     * @return
     */
    private static List<DataEntity> processListData(List<DataEntity> list) {
        final String[] string = new String[1];

        return list.stream().map(dataEntity -> {
            if (dataEntity.getProject() != null && !"".equals(dataEntity.getProject().trim())) {
                string[0] = dataEntity.getProject().trim();
            }
            dataEntity.setProject(string[0]);
            return dataEntity;
        }).collect(Collectors.toList());
    }

    /**
     * 获取传入的文件夹路径下所有 xlsx 格式的文件路径
     *
     * @param directoryPath 文件夹路径
     * @return
     */
    private static List<String> getExcelFilePaths(String directoryPath) {
        List<String> filePath = new ArrayList<>();
        File file = new File(directoryPath);

        String[] names = file.list();
        if (names != null) {
            filePath.addAll(Arrays.asList(names));
            filePath = filePath.stream().filter(s -> s.endsWith(".xlsx") || s.endsWith(".xls"))
                    .map(s -> directoryPath + File.separator + s)
                    .collect(Collectors.toList());
        }

        return filePath;
    }
}