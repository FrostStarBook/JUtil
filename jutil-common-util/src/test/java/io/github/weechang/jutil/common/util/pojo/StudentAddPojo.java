package io.github.weechang.jutil.common.util.pojo;

import lombok.Data;

import java.util.List;

/**
 * @author zhangwei
 * date 2018/7/24
 * time 15:51
 */
@Data
public class StudentAddPojo {

    private String name;

    private Integer sex;

    private List<String> others;
}
