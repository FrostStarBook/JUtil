package io.github.weechang.jutil.common.util.domain;

import lombok.Data;

import java.util.List;

/**
 * @author zhangwei
 * date 2018/7/24
 * time 15:49
 */
@Data
public class Student {

    private String name;

    private Integer sex;

    private List<String> others;

    private Boolean vaild;
}
