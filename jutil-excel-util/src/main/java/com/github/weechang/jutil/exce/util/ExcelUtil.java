package com.github.weechang.jutil.exce.util;

import com.alibaba.fastjson.JSONObject;
import jodd.datetime.JDateTime;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import com.github.weechang.jutil.exce.util.annotation.Excel;
import com.github.weechang.jutil.exce.util.annotation.ExcelColumn;

import java.io.*;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * excel 工具类
 *
 * @author zhangwei
 * date 2018/7/18
 * time 17:22
 */
public class ExcelUtil {

    private static ExcelUtil util = null;

    public static synchronized ExcelUtil getInstance() {
        if (util == null) {
            util = new ExcelUtil();
        }
        return util;
    }

    /**
     * 将Excel文件导入到list对象
     *
     * @param file  导入的数据源
     * @param clazz 保存当前数据的对象
     * @return List excel文件内容
     */
    public List importToObjectList(File file, Class clazz) throws IOException, InvalidFormatException {
        List contents = null;
        Excel excel = (Excel) clazz.getAnnotation(Excel.class);
        if (excel != null) {
            String sheetName = excel.sheetName();
            List<Map> rows = this.excelFileConvertToList(file, sheetName);
            if (CollectionUtils.isNotEmpty(rows)) {
                rows.remove(0);
                contents = this.buildDataObject(rows, clazz);
            }
        }
        return contents;
    }

    /**
     * 导出excel模板
     *
     * @param clazz 导出源类
     */
    public byte[] exportExcelTemp(Class clazz){
        ByteArrayOutputStream arrayOutputStream = null;
        byte[] bytes = null;
        Workbook workbook = null;
        Excel excel = (Excel) clazz.getAnnotation(Excel.class);
        if (excel == null) {
            return bytes;
        }
        try {
            arrayOutputStream = new ByteArrayOutputStream();
            workbook = new SXSSFWorkbook(1);
            List<Map> excelData = buildExcelData(clazz, null);
            String sheetName = excel.sheetName();
            Sheet sheet = workbook.createSheet(sheetName);
            this.buildExcel(workbook, sheet, excelData);
            workbook.write(arrayOutputStream);
            bytes = arrayOutputStream.toByteArray();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IOException e) {
            if (workbook != null){
                try {
                    workbook.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (arrayOutputStream != null) {
                try {
                    arrayOutputStream.flush();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

        }
        return bytes;
    }

    /**
     * 导出excel模板到指定文件
     *
     * @param file 指定文件
     * @param clazz 源类
     */
    public void exportExcelTemp(File file, Class clazz){
        InputStream ins = null;
        OutputStream outs = null;
        try {
            byte[] bytes = this.exportExcelTemp(clazz);
            ins = new ByteArrayInputStream(bytes);
            outs = new FileOutputStream(file);
            byte[] buff = new byte[1024];
            int len = 0;
            while ((len = ins.read(buff)) != -1) {
                outs.write(buff, 0, len);
            }
            ins.close();
            outs.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ins != null){
                try {
                    ins.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outs != null){
                try {
                    outs.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 导出含数据的excel
     *
     * @param dataList 数据源
     */
    public byte[] exportExcelData(List<?> dataList){
        ByteArrayOutputStream arrayOutputStream = null;
        byte[] bytes = null;
        Workbook workbook = null;
        if (CollectionUtils.isEmpty(dataList)) {
            return bytes;
        }
        Class clazz = dataList.get(0).getClass();
        Excel excel = (Excel) clazz.getAnnotation(Excel.class);
        if (excel == null) {
            return bytes;
        }
        try {
            arrayOutputStream = new ByteArrayOutputStream();
            workbook = new SXSSFWorkbook(dataList.size());
            List<Map> excelData = buildExcelData(clazz, dataList);
            String sheetName = excel.sheetName();
            Sheet sheet = workbook.createSheet(sheetName);
            this.buildExcel(workbook, sheet, excelData);
            workbook.write(arrayOutputStream);
            bytes = arrayOutputStream.toByteArray();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IOException e) {
            if (workbook != null){
                try {
                    workbook.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (arrayOutputStream != null) {
                try {
                    arrayOutputStream.flush();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

        }
        return bytes;
    }

    /**
     * 导出含数据的excel到指定文件
     *
     * @param file     目标文件
     * @param dataList 数据源
     */
    public void exportExcelData(File file, List<?> dataList){
        InputStream ins = null;
        OutputStream outs = null;
        try {
            byte[] bytes = this.exportExcelData(dataList);
            ins = new ByteArrayInputStream(bytes);
            outs = new FileOutputStream(file);
            byte[] buff = new byte[1024];
            int len = 0;
            while ((len = ins.read(buff)) != -1) {
                outs.write(buff, 0, len);
            }
            ins.close();
            outs.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ins != null){
                try {
                    ins.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outs != null){
                try {
                    outs.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 将excel单元格转换为List
     *
     * @param file       文件
     * @param sheetName sheet 索引
     * @return 数据list
     * @throws IOException
     * @throws InvalidFormatException
     */
    private List<Map> excelFileConvertToList(File file, String sheetName) throws IOException, InvalidFormatException {
        List<Map> rows = new ArrayList<>();
        Workbook workbook = WorkbookFactory.create(file);
        Sheet sheet = workbook.getSheet(sheetName);
        for (Row row : sheet) {
            int totalCell = row.getPhysicalNumberOfCells();
            int nullCell = 0;
            Map<Integer, Object> cells = new HashMap<>();
            for (Cell cell : row) {
                Object obj;
                switch (cell.getCellTypeEnum()) {
                    case STRING:
                        obj = cell.getRichStringCellValue().getString();
                        break;
                    case NUMERIC:
                        if (DateUtil.isCellDateFormatted(cell)) {
                            obj = new JDateTime(cell.getDateCellValue());
                        } else {
                            obj = cell.getNumericCellValue();
                        }
                        break;
                    case BOOLEAN:
                        obj = cell.getBooleanCellValue();
                        break;
                    case FORMULA:
                        obj = cell.getNumericCellValue();
                        break;
                    default:
                        obj = null;
                        nullCell++;
                        break;
                }
                cells.put(cell.getColumnIndex(), obj);
            }
            if (totalCell == nullCell) {
                break;
            }
            rows.add(cells);
        }
        return rows;
    }

    /**
     * 构建类数据
     *
     * @param rows  excel内容数据
     * @param clazz 目标类
     * @return 目标类数据
     */
    private List buildDataObject(List<Map> rows, Class clazz) {
        List data = new ArrayList();
        Field[] fields = clazz.getDeclaredFields();
        for (Map row : rows) {
            JSONObject jsonObject = new JSONObject();
            for (Field field : fields) {
                ExcelColumn excelColumn = field.getAnnotation(ExcelColumn.class);
                if (excelColumn != null) {
                    int index = excelColumn.index();
                    Object value = row.get(index);
                    String name = field.getName();
                    jsonObject.put(name, value);
                }
            }
            Object rowObj = jsonObject.toJavaObject(clazz);
            data.add(rowObj);
        }
        return data;
    }

    /**
     * 将类数据转换为适合excel的数据
     *
     * @param clazz    类
     * @param dataList 类数据
     * @return excel数据
     * @throws IllegalAccessException
     */
    private List<Map> buildExcelData(Class clazz, List<?> dataList) throws IllegalAccessException {
        Field[] fields = clazz.getDeclaredFields();
        List<Map> dataMap = new ArrayList<>();
        // 构建表头
        Map headRow = new HashMap();
        for (Field field : fields) {
            ExcelColumn excelColumn = field.getAnnotation(ExcelColumn.class);
            if (excelColumn != null) {
                int index = excelColumn.index();
                String displayName = excelColumn.displayName();
                boolean required = excelColumn.required();
                displayName = required ? displayName + "*" : displayName;
                headRow.put(index, displayName);
            }
        }
        dataMap.add(headRow);
        // 构建数据列
        if (CollectionUtils.isNotEmpty(dataList)) {
            for (Object data : dataList) {
                Map row = new HashMap();
                for (Field field : fields) {
                    ExcelColumn excelColumn = field.getAnnotation(ExcelColumn.class);
                    if (excelColumn != null) {
                        int index = excelColumn.index();
                        field.setAccessible(true);
                        Object value = field.get(data);
                        row.put(index, value);
                    }

                }
                dataMap.add(row);
            }
        }
        return dataMap;
    }

    /**
     * 将数据写入excel
     *
     * @param sheet     目标sheet
     * @param excelData excel数据
     */
    private void buildExcel(Workbook workbook, Sheet sheet, List<Map> excelData) {
        if (CollectionUtils.isEmpty(excelData)) {
            return;
        }
        sheet.setDefaultColumnWidth(18);
        // 设置表头字体
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontName("黑体"); //字体
        // 设置样式
        CellStyle style = workbook.createCellStyle();
        style.setBorderBottom(BorderStyle.THIN); //下边框
        style.setBorderLeft(BorderStyle.THIN);//左边框
        style.setBorderTop(BorderStyle.THIN);//上边框
        style.setBorderRight(BorderStyle.THIN);//右边框
        style.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
        style.setFont(font);

        int rowCounter = 0;
        for (Map rowData : excelData) {
            Row row = sheet.createRow(rowCounter++);
            for (int i = 0; i < rowData.size(); i++) {
                Cell cell = row.createCell(i);
                if (rowCounter == 1){
                    cell.setCellStyle(style);
                }
                Object valueObject = rowData.get(i);
                if (valueObject == null) {
                    cell.setCellValue("");
                } else if (valueObject instanceof Integer) {
                    cell.setCellValue((Integer) valueObject);
                } else if (valueObject instanceof String) {
                    cell.setCellValue((String) valueObject);
                } else if (valueObject instanceof Date) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    cell.setCellValue(sdf.format((Date)valueObject));
                } else {
                    cell.setCellValue(valueObject.toString());
                }
            }
        }
    }
}
