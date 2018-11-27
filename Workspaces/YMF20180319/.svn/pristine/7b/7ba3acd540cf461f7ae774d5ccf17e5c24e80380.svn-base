package com.yeepay.g3.ymf.boss.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author jiwei.lv
 * @create 2016-08-16/8/23
 */
public class SimpleExcelWriter {

    private Sheet sheet;
    private Workbook wb;
    private File file;
    private String fileName;
    private String[] heads;
    private final static int sheetSize = 100000;
    private static final Log logger = LogFactory.getLog(SimpleExcelWriter.class);

    public SimpleExcelWriter(File file, String fileName, String[] heads) {
        this(file, fileName, sheetSize, heads);
    }

    private SimpleExcelWriter(File file, String fileName, int sheetSize, String[] heads) {
        this.file = file;
        if (!file.exists()) {
            file.mkdirs();
        }
        this.fileName = fileName;
        this.heads = heads;
        // 第一步，创建一个webbook，对应一个Excel文件
        this.wb = new SXSSFWorkbook();
        createSheet();
    }

    public void createSheet() {

        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        sheet = this.wb.createSheet();
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        Row row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        // XSSFCellStyle style = wb.createCellStyle();
        // style.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        Cell cell = null;
        for (int i = 0; i < heads.length; i++) {
            cell = row.createCell((short) i);
            cell.setCellValue(heads[i]);
            cell.setCellType(Cell.CELL_TYPE_STRING);
            if (null == heads[i] || "".equals(heads[i])) {
                sheet.setColumnWidth(i, 3000);
            } else {
                sheet.setColumnWidth(i, heads[i].getBytes().length * 300);
            }

        }
    }


    public void writeRow(int rowCount, String[] contents) {
        if (rowCount > 0 && rowCount % sheetSize == 0) {
            createSheet();
        }
        Row row = sheet.createRow((rowCount % sheetSize));
        Cell cell = null;
        for (int i = 0; i < contents.length; i++) {
            String content = contents[i];
            cell = row.createCell((short) i);
            cell.setCellValue(content);
            if (null == content || "".equals(content)) {
                sheet.setColumnWidth(i, 3000);
            } else {
                sheet.setColumnWidth(i, content.getBytes().length * 500);
            }

        }
    }

    public void finishWrite() {
        FileOutputStream fout = null;
        try {
            File dirfile = new File(this.file, this.fileName);
            fout = new FileOutputStream(dirfile);
            this.wb.write(fout);
            fout.flush();
            fout.close();
        } catch (Exception e) {
            logger.error("文件写入异常", e);
        } finally {
            try {
                if (null != fout) {
                    fout.close();
                }
            } catch (IOException e) {
                logger.error("文件关闭异常", e);
            }
        }
    }

}
