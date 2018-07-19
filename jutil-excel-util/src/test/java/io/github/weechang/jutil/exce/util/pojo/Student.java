package io.github.weechang.jutil.exce.util.pojo;

import lombok.Data;
import org.apache.poi.ss.usermodel.CellType;
import io.github.weechang.jutil.exce.util.annotation.Excel;
import io.github.weechang.jutil.exce.util.annotation.ExcelColumn;

import java.util.Date;

/**
 * @author zhangwei
 * date 2018/7/18
 * time 18:06
 */
@Excel(sheetName = "学生花名册")
@Data
public class Student {

    private Long id;

    @ExcelColumn(index = 0, displayName = "姓名")
    private String name;

    @ExcelColumn(index = 1, displayName = "性别", cellType = CellType.NUMERIC)
    private Integer sex;

    @ExcelColumn(index = 2, displayName = "身份证号码")
    private String idCard;

    @ExcelColumn(index = 3, displayName = "出生日期")
    private Date birthDay;

    @ExcelColumn(index = 4, displayName = "家庭住址", required = false)
    private String address;

    @ExcelColumn(index = 5, displayName = "是否贫困生")
    private Boolean isPoor;
}
