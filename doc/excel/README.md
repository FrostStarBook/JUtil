# Excel 导入导出工具
<p>支持简单的excel导入、导出、模板下载</p>
<p><b>一行代码</b> 一行代码即可满足excel的导入、导出、模板下载</p>
<p><b>两种方式</b> excel导出支持两种方式：导出为二进制流、直接导出文件</p>
<p><b>两个步骤</b> 只需两个步骤，即可使用：添加maven依赖、添加注解</p>

## step1
添加maven依赖
<pre>
    &lt;dependency&gt;
        &lt;groupId&gt;io.github.weechang&lt;/groupId&gt;
        &lt;artifactId&gt;jutil-excel-util&lt;/artifactId&gt;
        &lt;version&gt;1.0.0&lt;/version&gt;
    &lt;/dependency&gt;
</pre>

## step2
添加类注解

<pre>
    @Excel(sheetIndex = 1)
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
</pre>

## 开始浪
### 1.表格模板导出
<pre>
    File tempFile = new File("E:\\temp.xlsx");
    if (!tempFile.exists()){
        tempFile.createNewFile();
    }
    // 1.导出到文件
    ExcelUtil.getInstance().exportExcelTemp(tempFile, Student.class);
    // 2.导出为二进制流
    byte[] bytes = ExcelUtil.getInstance().exportExcelTemp(Student.class);
</pre>

### 2.表格导出
<pre>
    File file = new File("E:\\data.xlsx");
    if (!file.exists()){
        file.createNewFile();
    }
    // 1.导出到文件
    ExcelUtil.getInstance().exportExcelData(file, getStudentList());

    // 2.导出为二进制流
    byte[] bytes = ExcelUtil.getInstance().exportExcelData(getStudentList());
</pre>

### 3.表格导入
<pre>
     File file = new File("E:\\data.xlsx");
     List<Student> students = ExcelUtil.getInstance().importToObjectList(file, Student.class);
</pre>

