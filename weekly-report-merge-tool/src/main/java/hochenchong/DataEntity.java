package hochenchong;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.util.Date;

@Data
@HeadRowHeight(30)
@ContentRowHeight(30)
@ColumnWidth(25)
public class DataEntity {
    // 开发人员
    @ExcelProperty(value = "姓名", index = 0)
    private String developer;
    // 项目工作
    @ColumnWidth(70)
    @ExcelProperty(value = "项目工作", index = 1)
    private String projectWork;
    // 需求提出人
    @ExcelProperty(value = "需求提出人", index = 2)
    private String demander;
    // 计划开始时间
    @DateTimeFormat("yyyy/MM/dd")
    @ExcelProperty(value = "计划开始时间", index = 3)
    private Date startDate;
    // 计划结束时间
    @DateTimeFormat("yyyy/MM/dd")
    @ExcelProperty(value = "计划完成时间", index = 4)
    private Date endDate;
    // 完成状态
    @ExcelProperty(value = "完成状态", index = 5)
    private String status;
    // 问题反馈
    @ExcelProperty(value = "问题反馈", index = 6)
    private String feedback;
}

