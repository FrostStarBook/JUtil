package io.github.weechang.jutil.exce.util;


import io.github.weechang.jutil.exce.util.pojo.Student;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 表格工具测试类
 * @author zhangwei
 * date 2018/7/18
 * time 18:05
 */
public class ExcelUtilTest {

    /**
     * 导出表格模板
     */
    @Test
    public void exportExcelTemp() throws IOException{
        File tempFile = new File("E:\\temp.xlsx");
        if (!tempFile.exists()){
            tempFile.createNewFile();
        }
        // 1.导出到文件
        ExcelUtil.getInstance().exportExcelTemp(tempFile, Student.class);
        // 2.导出为二进制流
        byte[] bytes = ExcelUtil.getInstance().exportExcelTemp(Student.class);
    }

    /**
     * 导出表格
     */
    @Test
    public void exportExcel() throws IOException {
        File file = new File("E:\\data.xlsx");
        if (!file.exists()){
            file.createNewFile();
        }
        // 1.导出到文件
        ExcelUtil.getInstance().exportExcelData(file, getStudentList());

        // 2.导出为二进制流
        byte[] bytes = ExcelUtil.getInstance().exportExcelData(getStudentList());
    }

    /**
     * 表格导入
     *
     * @throws IOException
     * @throws InvalidFormatException
     */
    @Test
    public void importExcel() throws IOException, InvalidFormatException {
        File file = new File("E:\\data.xlsx");
        List<Student> students = ExcelUtil.getInstance().importToObjectList(file, Student.class);
        for (Student student : students) {
            System.out.println(student.toString());
        }
    }


    private List<Student> getStudentList(){
        List<Student> students = new ArrayList<>();
        Student s1 = new Student();
        s1.setName("李雷");
        s1.setSex(1);
        s1.setBirthDay(new Date(System.currentTimeMillis()));
        s1.setIdCard("51021220122123102");
        s1.setAddress("四川省成都市武侯区天府广场");
        s1.setIsPoor(true);
        students.add(s1);
        Student s2 = new Student();
        s2.setName("韩梅梅");
        s2.setSex(0);
        s2.setBirthDay(new Date(System.currentTimeMillis()));
        s2.setIdCard("51021199312190102");
        s2.setAddress("四川省成都市武侯区天府广场A");
        s2.setIsPoor(false);
        students.add(s2);
        return students;
    }
}