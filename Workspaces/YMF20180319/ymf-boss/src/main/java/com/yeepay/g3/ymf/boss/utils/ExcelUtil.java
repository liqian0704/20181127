package com.yeepay.g3.ymf.boss.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Author: wei.li
 * Date: 17/3/8
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class ExcelUtil {

    // 总行数
    private int totalRows = 0;

    // 总列数
    private int totalCells = 0;

    public List<List<String>> read(MultipartFile file) {
        List<List<String>> dataList = new ArrayList<List<String>>();
        InputStream is = null;
        try {
            // 判断文件的类型，是2003还是2007
            boolean isExcel2003 = true;
            if (isXls(file.getOriginalFilename())) {
                isExcel2003 = true;
            } else if (isXlsx(file.getOriginalFilename())) {
                isExcel2003 = false;
            } else {
                // 文件格式错误
                return null;
            }
            // 调用本类提供的根据流读取的方法
            is = file.getInputStream();
            dataList = read(is, isExcel2003);
            is.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    is = null;
                    e.printStackTrace();
                }
            }
        }
        // 返回最后读取的结果
        return dataList;
    }

    private List<List<String>> read (InputStream inputStream, boolean isExcel2003) {
        List<List<String>> dataList = null;
        try {
            // 根据版本选择创建Workbook的方式
            Workbook wb = null;
            if (isExcel2003) {
                wb = new HSSFWorkbook(inputStream);
            } else {
                wb = new XSSFWorkbook(inputStream);
            }
            dataList = read(wb);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataList;
    }

    private List<List<String>> read(Workbook wb) {

        List<List<String>> dataList = new ArrayList<List<String>>();
        // 得到第一个shell
        Sheet sheet = wb.getSheetAt(0);
        // 得到Excel的行数
        this.totalRows = sheet.getPhysicalNumberOfRows();
        // 得到Excel的列数
        if (this.totalRows >= 1 && sheet.getRow(0) != null) {
            this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        }
        // 循环Excel的行 第0行为标题 不参与取值
        for (int r = 1; r < this.totalRows; r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }
            List<String> rowList = new ArrayList<String>();
            DecimalFormat df = new DecimalFormat("0");
            // 循环Excel的列
            // cell自动匹配类型的功能不完善，需要逐条按指定类型取出数据，不能使用循环方式
            Cell phoneNoCell = row.getCell(0);
            String phoneNo = phoneNoCell.getStringCellValue();

            Cell merchantNoCell = row.getCell(1);
            String merchantNo = merchantNoCell.getStringCellValue();

            rowList.add(phoneNo);
            rowList.add(merchantNo);

            dataList.add(rowList);
        }
        return dataList;
    }

    private static boolean isXls(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    private static boolean isXlsx(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }


}
