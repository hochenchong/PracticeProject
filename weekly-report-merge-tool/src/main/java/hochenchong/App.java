package hochenchong;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.util.StringUtils;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import java.io.File;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class App {
    // Excel Sheet 的名称列表
    private final static List<String> EXCEL_NAME_LIST;
    static {
        EXCEL_NAME_LIST = new ArrayList<>(3);
        // 获取当前周的周一与周五
        LocalDate thisMonday = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate thisFriday = LocalDate.now().with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));
        EXCEL_NAME_LIST.add(String.format("%s--%s 周工作内容", thisMonday, thisFriday));

        // 获取下周的周一与周五
        LocalDate nextMonday = thisMonday.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        LocalDate nextFriday = thisFriday.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        EXCEL_NAME_LIST.add(String.format("%s--%s 周工作内容", nextMonday, nextFriday));

        EXCEL_NAME_LIST.add(String.format("开发团队工作计划周报汇总-%s--%s", thisMonday, thisFriday));
    }

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
        System.out.println("------- 周报合并工具 v1.0 By HochenChong 2020/01/13 -------");

        // 获取要处理的 excel 列表
        List<String> excelFilePaths = getExcelFilePaths(directoryPath);

        String newFilePATH = System.getProperty("user.dir") + File.separator + EXCEL_NAME_LIST.get(2) + ".xlsx";
        ExcelWriter excelWriter = EasyExcel.write(newFilePATH, DataEntity.class).build();

        for (String filePath : excelFilePaths) {
            System.out.println("--- 正在处理：" + filePath + " ----");

            for (int i = 0; i < 2; i++) {
                List<DataEntity> list = EasyExcel.read(filePath).head(DataEntity.class).sheet(i).doReadSync();
                // 这里注意 如果同一个 sheet 只要创建一次
                WriteSheet writeSheet = EasyExcel.writerSheet(i, EXCEL_NAME_LIST.get(i))
                        .registerWriteHandler(getHorizontalCellStyleStrategy())
                        .build();
                excelWriter.write(list, writeSheet);
            }
        }
        /// 千万别忘记 finish 会帮忙关闭流
        excelWriter.finish();
        System.out.println("处理完成，生成的周报路径为：" + newFilePATH);
    }

    // 读取某个目录下的所有 xlsx 格式的文件
    private static List<String> getExcelFilePaths(String directoryPath) {
        List<String> filePath = new ArrayList<>();
        File file = new File(directoryPath);

        String [] names = file.list();
        if (names != null) {
            filePath.addAll(Arrays.asList(names));
            filePath = filePath.stream().filter(s -> s.endsWith(".xlsx") || s.endsWith(".xls"))
                    .map(s -> directoryPath + File.separator + s)
                    .collect(Collectors.toList()); }

        return filePath;
    }

    // 设置 Excel 的样式
    private static HorizontalCellStyleStrategy getHorizontalCellStyleStrategy() {
        // 头的策略
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        // 设置头部字体
        WriteFont headWriteFont = new WriteFont();
        headWriteFont.setFontHeightInPoints((short)12);
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
        contentWriteFont.setFontHeightInPoints((short)12);
        contentWriteFont.setFontName("宋体");
        contentWriteCellStyle.setWriteFont(contentWriteFont);
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