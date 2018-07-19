package io.github.weechang.jutil.exce.util.annotation;

import java.lang.annotation.*;

/**
 * excel 注解
 *
 * @author zhangwei
 * date 2018/7/19
 * time 11:04
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Excel {

    int sheetIndex() default 0;

    String sheetName() default "Sheet1";
}
