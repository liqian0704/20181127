package com.yeepay.g3.core.ymf.utils.web;

import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Title: POI帮助类
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 17/3/13.
 */
public class POIHelper {

    private static final Logger log = LoggerFactory.getLogger(POIHelper.class);

    /**
     * 读取文件
     * @param in EXCEL流
     * @param sheetName 表单名称
     * @param clazz 实体类
     * @return 有序集合
     */
    public static <T extends BatchExcelBean> List<T> read(InputStream in, String sheetName, Class<T> clazz) {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(in);
            XSSFSheet sheet = workbook.getSheet(sheetName);
            List<T> result = new LinkedList<T>();
            for (Iterator<Row> it = sheet.rowIterator(); it.hasNext(); ) {
                Row row = it.next();
                if (row.getRowNum() == 0) { // 跳过第一行
                    continue;
                }
                T bean = clazz.newInstance();
                bean.setIndex(row.getRowNum()); // 行号
                Field[] fields = bean.getOrderedFields();
                // 从1开始,跳过index字段
                for (int i = 0, j = fields.length; i < j; i++) {
                    Field field = fields[i];
                    Cell cell = row.getCell(i);
                    field.setAccessible(true);
                    if (null != cell) {
                        field.set(bean, getCellValue(cell, field.getType()));
                    } else {
                        field.set(bean, null);
                    }
                }
                result.add(bean);
            }
            return result;
        } catch (Exception e) {
            log.error("读取EXCEL文件失败", e);
            return null;
        }
    }

    /**
     * 写EXCEL
     * @param beanList 最好是LinkedList
     * @param out 输出流
     * @param <T> 泛型
     */
    public static <T extends BatchExcelBean> void write(List<T> beanList, OutputStream out) {
        if (null == beanList || beanList.size() == 0) {
            throw new IllegalArgumentException("数据源不能是空");
        }
        if (null == out) {
            throw new IllegalArgumentException("输出流不能是空");
        }
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("data");
        try {
            // title
            Row titleRow = sheet.createRow(0);
            Field[] titleFields = beanList.get(0).getOrderedFields();
            for (int i = 0, j = titleFields.length; i < j; i ++) {
                Cell titleCell = titleRow.createCell(i);
                Field titleField = titleFields[i];
                ExcelField excelField = titleField.getAnnotation(ExcelField.class);
                if (null != excelField) {
                    titleCell.setCellValue(excelField.name());
                }
            }
            // data
            data(beanList, sheet);
            workbook.write(out);
        } catch (Exception e) {
            log.error("生成EXCEL文件失败", e);
        }
    }

    /**
     * 写EXCEL
     * @param titleList 表头
     * @param beanList 最好是LinkedList
     * @param out 输出流
     * @param <T> 泛型
     */
    public static <T extends BatchExcelBean> void write(List<String> titleList, List<T> beanList, OutputStream out) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("data");
        try {
            // title
            Row titleRow = sheet.createRow(0);
            for (int i = 0, j = titleList.size(); i < j; i ++) {
                Cell titleCell = titleRow.createCell(i);
                titleCell.setCellValue(titleList.get(i));
            }
            // data
            data(beanList, sheet);
            workbook.write(out);
        } catch (Exception e) {
            log.error("生成EXCEL文件失败", e);
        }
    }

    /**
     * 序列化
     * @param beanList
     * @param sheet
     * @param <T>
     * @throws IllegalAccessException
     */
    private static <T extends BatchExcelBean> void data(List<T> beanList, Sheet sheet) throws IllegalAccessException {
        for (int i = 0, j = beanList.size(); i < j; i ++) {
            Row row = sheet.createRow(i+1);
            T t = beanList.get(i);
            Field[] fields = t.getOrderedFields();
            for (int m = 0, n = fields.length; m < n; m ++) {
                Cell cell = row.createCell(m);
                Field field = fields[m];
                field.setAccessible(true);
                Object value = field.get(t);
                setCellValue(cell, field.getType(), value);
            }
        }
    }

    /**
     * 写EXCEL
     * @param titleList
     * @param beanList
     * @param out
     */
    public static void writeNormal(List<String> titleList, List<List<Object>> beanList, OutputStream out) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("data");
        try {
            // title
            Row titleRow = sheet.createRow(0);
            for (int i = 0, j = titleList.size(); i < j; i ++) {
                Cell titleCell = titleRow.createCell(i);
                titleCell.setCellValue(titleList.get(i));
            }
            // data
            for (int i = 0, j = beanList.size(); i < j; i ++) {
                Row row = sheet.createRow(i+1);
                List<Object> valueList = beanList.get(i);
                for (int m = 0, n = valueList.size(); m < n; m ++) {
                    Cell cell = row.createCell(m);
                    Object value = valueList.get(m);
                    setCellValue(cell, value.getClass(), value);
                }
            }
            workbook.write(out);
        } catch (Exception e) {
            log.error("生成EXCEL文件失败", e);
        }
    }

    /**
     * 获取CELL值
     * @see Cell#CELL_TYPE_BLANK
     * @see Cell#CELL_TYPE_NUMERIC
     * @see Cell#CELL_TYPE_STRING
     * @see Cell#CELL_TYPE_FORMULA
     * @see Cell#CELL_TYPE_BOOLEAN
     * @see Cell#CELL_TYPE_ERROR
     * @param cell
     * @return
     */
    private static void setCellValue(Cell cell, Class clazz, Object value) {
        if (String.class == clazz) {
            cell.setCellValue(value.toString());
        } else if (Double.class == clazz || double.class == clazz) {
            cell.setCellValue((Double) value);
        } else if (Boolean.class == clazz || boolean.class == clazz) {
            cell.setCellValue(Boolean.valueOf(value.toString()));
        } else if (Integer.class == clazz || int.class == clazz) {
            cell.setCellValue((Integer)value);
        } else {
            throw new IllegalArgumentException("不支持Bean属性类型[" + clazz + "]value");
        }
    }

    /**
     * 获取CELL值
     * @see Cell#CELL_TYPE_BLANK
     * @see Cell#CELL_TYPE_NUMERIC
     * @see Cell#CELL_TYPE_STRING
     * @see Cell#CELL_TYPE_FORMULA
     * @see Cell#CELL_TYPE_BOOLEAN
     * @see Cell#CELL_TYPE_ERROR
     * @param cell
     * @return
     */
    private static Object getCellValue(Cell cell, Class clazz) {
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_BLANK:
                return null;
            case Cell.CELL_TYPE_NUMERIC:
                if (String.class == clazz) {
                    NumberFormat nb = new DecimalFormat("#");
                    return nb.format(cell.getNumericCellValue());
                } else {
                    return cell.getNumericCellValue();
                }
            case Cell.CELL_TYPE_STRING:
                return cell.getStringCellValue();
            case Cell.CELL_TYPE_FORMULA:
                throw new IllegalArgumentException("不支持CELL_TYPE_FORMULA类型");
            case Cell.CELL_TYPE_BOOLEAN:
                return cell.getBooleanCellValue();
            case Cell.CELL_TYPE_ERROR:
                return cell.getErrorCellValue();
            default:
                return null;
        }
    }
}
