package io.github.weechang.jutil.validate.util;

import io.github.weechang.jutil.validate.util.enums.ValidateEnum;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import static io.github.weechang.jutil.validate.util.enums.ValidateEnum.IPV4;
import static io.github.weechang.jutil.validate.util.enums.ValidateEnum.IPV6;

/**
 * 验证工具
 *
 * @author zhangwei
 * date 2018/7/20
 * time 10:32
 */
public class ValidateUtil {

    /**
     * 校验字符串是否符合规则
     *
     * @param input        需校验字符串
     * @param validateEnum 校验规则
     * @return 校验结果
     */
    public static boolean validate(String input, ValidateEnum validateEnum) {
        String[] regex;
        switch (validateEnum) {
            case IP:
                regex = new String[]{IPV4.getRegex(), IPV6.getRegex()};
                break;
            default:
                regex = new String[]{validateEnum.getRegex()};
                break;
        }
        return validate(input, regex);
    }

    /**
     * 自由校验
     *
     * @param input 输入内容
     * @param regex 正则表达式,可输入多项
     * @return 校验结果
     */
    public static boolean validate(String input, String... regex) {
        boolean valid = false;
        if (regex != null) {
            val:
            {
                for (String item : regex) {
                    if (StringUtils.isNotEmpty(item) && Pattern.matches(item, input)) {
                        valid = true;
                        break val;
                    }
                }
            }
        }
        return valid;
    }

}
