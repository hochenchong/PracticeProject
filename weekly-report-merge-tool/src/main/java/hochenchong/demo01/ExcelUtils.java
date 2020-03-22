package hochenchong.demo01;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.builder.ExcelWriterTableBuilder;
import com.alibaba.excel.write.merge.LoopMergeStrategy;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.WriteTable;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import hochenchong.DataEntity;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import java.io.File;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author HochenChong
 * @date 2020-03-22
 * @description 周报合并工具 v1.1 版本使用的 Excel 写入数据工具类
 */

public class ExcelUtils {

    private ExcelWriter excelWriter;
    // 生成的文件路径
    private String newFilePath;
    // Excel Sheet 的名称列表
    private List<String> excelNameList;

    public ExcelUtils() {
        setExcelName();

        excelWriter = EasyExcel.write(newFilePath, DataEntity.class).build();
    }

    /**
     * 设置生成 Excel 相关名字与路径
     */
    private void setExcelName() {
        excelNameList = new ArrayList<>(3);
        // 获取当前周的周一与周五
        LocalDate thisMonday = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate thisFriday = thisMonday.with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));
        excelNameList.add(String.format("%s--%s 周工作内容", thisMonday, thisFriday));

        // 获取下周的周一与周五
        LocalDate nextMonday = thisMonday.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        LocalDate nextFriday = thisFriday.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        excelNameList.add(String.format("%s--%s 周工作内容", nextMonday, nextFriday));

        excelNameList.add(String.format("开发团队工作计划周报汇总-%s--%s", thisMonday, thisFriday));

        newFilePath = System.getProperty("user.dir") + File.separator + excelNameList.get(2) + ".xlsx";
    }

    /**
     * 往表中写入数据
     *
     * @param sheetNo Sheet 序号，以 0 开始
     * @param list    要写入的数据
     */
    public void writerSheet(Integer sheetNo, List<DataEntity> list) {
        // 把 sheet设置为不需要头部。不然会输出 sheet 的头部，这样看起来第一个 table 就有 2 个头部
        WriteSheet writeSheet = EasyExcel.writerSheet(sheetNo, excelNameList.get(sheetNo)).needHead(Boolean.FALSE).registerWriteHandler(getHorizontalCellStyleStrategy()).build();

        // 根据 project 属性的值进行分组
        Map<String, List<DataEntity>> map = list.stream().collect(Collectors.groupingBy(DataEntity::getProject));
        Comparator comparator = Comparator.comparing((String s) -> s, String.CASE_INSENSITIVE_ORDER);
        List<String> keyList = (List<String>) map.keySet().stream().sorted(comparator).collect(Collectors.toList());

        int[] num = new int[1];
        num[0] = 0;
        keyList.forEach(s -> {
            // 这里根据需要指定头部，table 会继承 sheet 的配置，sheet 配置了不需要，table 默认也是不需要
            WriteTable writeTable;
            ExcelWriterTableBuilder excelWriterTableBuilder = EasyExcel.writerTable(num[0]);
            if (num[0] == 0) {
                // 第一次写入会创建头部
                excelWriterTableBuilder.needHead(Boolean.TRUE);
            } else {
                excelWriterTableBuilder.needHead(Boolean.FALSE);
            }
            num[0]++;

            List<DataEntity> dataEntities = map.get(s);
            if (dataEntities.size() > 1) {
                // 自定义合并单元格，将相同的 project 这一列合并单元格
                LoopMergeStrategy loopMergeStrategy = new LoopMergeStrategy(dataEntities.size(), 0);
                excelWriterTableBuilder.registerWriteHandler(loopMergeStrategy);
            }
            writeTable = excelWriterTableBuilder.build();

            excelWriter.write(dataEntities, writeSheet, writeTable);
        });
    }

    public void close() {
        // 千万别忘记 finish 会帮忙关闭流
        if (excelWriter != null) {
            excelWriter.finish();
            System.out.println("处理完成，生成的周报路径为：");
            System.out.println(newFilePath);
            System.out.println("------- 欢迎使用，下次再回！ -------");
        }
    }

    // 设置 Excel 的样式
    private static HorizontalCellStyleStrategy getHorizontalCellStyleStrategy() {
        // 头的策略
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        // 设置头部字体
        WriteFont headWriteFont = new WriteFont();
        headWriteFont.setFontHeightInPoints((short) 12);
        headWriteCellStyle.setWriteFont(headWriteFont);
        // 背景设置为灰色，以及设置边框
        headWriteCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headWriteCellStyle.setBorderTop(BorderStyle.THIN);
        headWriteCellStyle.setBorderBottom(BorderStyle.THIN);
        headWriteCellStyle.setBorderLeft(BorderStyle.THIN);
        headWriteCellStyle.setBorderRight(BorderStyle.THIN);

        // 内容的策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        WriteFont contentWriteFont = new WriteFont();
        // 字体大小，字体
        contentWriteFont.setFontHeightInPoints((short) 12);
        contentWriteFont.setFontName("宋体");
        contentWriteCellStyle.setWriteFont(contentWriteFont);
        // 设置自动换行
        contentWriteCellStyle.setWrapped(true);
        // 设置垂直居中
        contentWriteCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        // 设置边框
        contentWriteCellStyle.setBorderTop(BorderStyle.THIN);
        contentWriteCellStyle.setBorderBottom(BorderStyle.THIN);
        contentWriteCellStyle.setBorderLeft(BorderStyle.THIN);
        contentWriteCellStyle.setBorderRight(BorderStyle.THIN);

        // 这个策略是 头是头的样式 内容是内容的样式 其他的策略可以自己实现
        HorizontalCellStyleStrategy horizontalCellStyleStrategy =
                new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
        return horizontalCellStyleStrategy;
    }
}
