package io.github.weechang.jutil.exce.util.annotation;

import org.apache.poi.ss.usermodel.CellType;

import java.lang.annotation.*;

import static org.apache.poi.ss.usermodel.CellType.STRING;

/**
 * 表格列设置
 *
 * @author zhangwei
 *
 * date 2018/7/18
 * time 17:51
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelColumn {

    /**
     * 索引
     */
    int index() default 0;

    /**
     * 字段显示名称
     */
    String displayName() default "";

    /**
     * 字段类型
     */
    CellType cellType() default STRING;

    /**
     * 是否必须导入
     */
    boolean required() default true;
}
