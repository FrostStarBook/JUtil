package io.github.weechang.jutil.common.util;

import io.github.weechang.jutil.common.util.domain.Student;
import io.github.weechang.jutil.common.util.pojo.StudentAddPojo;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author zhangwei
 * date 2018/7/24
 * time 15:49
 */
public class BeanUtilTest {

    @Test
    public void beanToJsonString(){
        System.out.println(BeanUtil.beanToJsonString(getSudentPojo()));
    }

    @Test
    public void beanToBean() {
        Student student = BeanUtil.beanToBean(getSudentPojo(), Student.class);
        System.out.println(student.toString());
    }

    @Test
    public void mapToBean() {
        Student student = BeanUtil.mapToBean(getStudentMap(), Student.class);
        System.out.println(student.toString());
    }

    @Test
    public void beanToMap() {
        Map<String, Object> map = BeanUtil.beanToMap(getSudentPojo());
        System.out.println(map);
    }

    private Map<String, Object> getStudentMap(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "张三");
        map.put("sex", "1");
        List<String> others = new ArrayList<String>();
        others.add("1");
        others.add("测试");
        others.add("test");
        map.put("others", others);
        return map;
    }

    private StudentAddPojo getSudentPojo(){
        StudentAddPojo studentAddPojo = new StudentAddPojo();
        studentAddPojo.setName("张三");
        studentAddPojo.setSex(1);
        List<String> others = new ArrayList<String>();
        others.add("1");
        others.add("测试");
        others.add("test");
        studentAddPojo.setOthers(others);
        return studentAddPojo;
    }
}