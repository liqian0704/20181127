package com.yeepay.g3.core.ymf.utils.web;

import com.yeepay.g3.core.ymf.utils.dateutils.DateUtil;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author jiwei.lv
 * @create 2016-08-16/8/18
 */
public class POIUtils {
    private static HSSFCellStyle bodyStyle;

    private static HSSFFont bodyFont;

    private static HSSFCellStyle headStyle;

    private static HSSFFont headFont;

    /**
     * 导出Excle文档，当导出模板时objList,objClass,strBody均为null
     *
     * @param objList   :   Excel数据源
     * @param objClass  :   Excel数据源中的数据类型
     * @param title     :   新建Sheet的名称
     *              ex: title = "员工表";
     * @param strTitle  :   Sheet各列的标题（第一行各列的名称）
     *              ex: strTitle = "员工代码,员工姓名,性别,出生日期,籍贯,所属机构,联系电话,电子邮件,助记码";
     * @param strBody   :   Sheet各列的取值方法名（各列的值在objClass中get方法名称）
     *              ex: strBody = "getCode,getName,getSex,getBirthday,getHomeplace.getName,getOrg.getShortName,getContactTel,getEmail,getZjm";
     * @param outputPath:   Excel文档保存路径
     */
    public static void exportExcelByObject(List objList, Class objClass, String title, String strTitle, String strBody, String outputPath) {
        // 初始化工作簿
        HSSFWorkbook workbook = initWorkbook(objList, objClass, title, strTitle, strBody);
        // 保存Excel文件
        saveExcelFile(workbook, outputPath);
    }
    
    public static void exportExcelByObject(List objList, Class objClass, String title, String strTitle, String strBody, HttpServletResponse response) {
        // 初始化工作簿
        HSSFWorkbook workbook = initWorkbook(objList, objClass, title, strTitle, strBody);
        try{
        	response.setHeader("Content-disposition", "attachment; filename=" + new String(URLEncoder.encode(title+".xls", "utf-8")));
            response.setHeader("Content-Type", "application/octet-stream");
          //直接刷到response中
            workbook.write(response.getOutputStream());
            response.flushBuffer();
        }catch(Exception e){
        	e.printStackTrace();
        }
    }

    /**
     * 初始化工作簿
     *
     * @param objList   :   Excel数据源
     * @param objClass  :   Excel数据源中的数据类型
     * @param title     :   新建Sheet的名称
     * @param strTitle  :   Sheet各列的标题（第一行各列的名称）
     * @param strBody   :   Sheet各列的取值方法名（各列的值在objClass中get方法名称）
     */
    private static HSSFWorkbook initWorkbook(List objList, Class objClass, String title, String strTitle, String strBody){
        // 创建工作簿（Excel文件）
        HSSFWorkbook workbook = new HSSFWorkbook();
        setbodyStyle(workbook);
        setHeadStyle(workbook);

        // 创建Excel工作簿的第一个Sheet页
        HSSFSheet sheet = workbook.createSheet(title);
        //设置表格默认的列宽度为15个字节
        sheet.setDefaultColumnWidth(15);
        //设置默认的行高像素点
        sheet.setDefaultRowHeightInPoints(16);
        // 创建Sheet页的文件头（第一行）
        createTitle(sheet, strTitle, workbook);

        // 创建Sheet页的文件体（后续行）
        if(objList != null&&objList.size()>0 && objClass != null && strBody != null) {
        	if(objList.get(0) instanceof Map)
        		createBody4Map(objList,sheet,strBody);
        	else
        		createBody(objList, objClass, sheet, strBody , workbook);
        }

        return workbook;
    }
    /**
     * 创建Excel当前sheet页的头信息
     *
     * @param sheet     :   Excel工作簿的一个sheet
     * @param strTitle  :   sheet头信息列表(sheet第一行各列值)
     */
    private static void createTitle(HSSFSheet sheet, String strTitle ,HSSFWorkbook workBook){
        HSSFRow row = sheet.createRow(  0); // 创建该页的一行
        HSSFCell cell = null;

        String[] strArray = strTitle.split(",");

        for(int i =   0; i < strArray.length; i++) {
            cell = row.createCell(i); // 创建该行的一列
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(strArray[i]);
            cell.setCellStyle(headStyle);

            //自适应宽度
            if((sheet.getColumnWidth(i)) < cell.getStringCellValue().getBytes().length * 400)
            {
                sheet.setColumnWidth(i, cell.getStringCellValue().getBytes().length * 400);
            }
        }

    }
    
    /**
     * 创建Excel当前sheet页的体信息，Excel数据源中的数据类型为Map
     * 暂不支持多级引用
     * 
     * @param objList : Excel数据源
     * @param sheet: Excel工作簿的sheet页
     * @param strBody: Sheet各列对应至数据源Map中的键
     */
    private static void createBody4Map( List objList, HSSFSheet sheet, String strBody) {
      //  String[] targetKeys = strBody.split(",");
        // 循环objList对象列表（生成sheet的行）
        for (int objIndex = 0; objIndex < objList.size(); objIndex++) {
            Map obj = (Map) objList.get(objIndex);
            HSSFRow row = sheet.createRow(objIndex +   1);
            // 循环strBody属性数组（生成sheet的列）
            int strIndex = 0;
            for (String targetKey:strBody.split(",")) {
                Cell cell = row.createCell(strIndex);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                Object value = obj.get(targetKey);
                if (value == null)
                    cell.setCellValue("");
                else
                    cell.setCellValue((value).toString());
                // 自适应宽度
                if ((sheet.getColumnWidth(strIndex)) < cell.getStringCellValue().getBytes().length * 400) {
                    sheet.setColumnWidth(strIndex, cell.getStringCellValue().getBytes().length * 400);
                }
                strIndex++;
            }
        }
    }
    
    /**
     * 创建Excel当前sheet页的体信息
     *
     * @param objList   :   Excel数据源
     * @param objClass  :   Excel数据源中的数据类型
     * @param sheet     :   Excel工作簿的sheet页
     * @param strBody   :   Sheet各列的取值方法名（各列的值在objClass中get方法名称）
     */
    private static void createBody(List objList, Class objClass, HSSFSheet sheet, String strBody, HSSFWorkbook workBook){
        String[] targetMethod = strBody.split(",");
        Method[] ms = objClass.getMethods();

        // 循环objList对象列表（生成sheet的行）
        for(int objIndex =   0; objIndex < objList.size(); objIndex++){
            Object obj = objList.get(objIndex);
            HSSFRow row = sheet.createRow(objIndex +   1);
            // 循环strBody目标方法数组（生成sheet的列）
            for(int strIndex =   0; strIndex < targetMethod.length; strIndex++) {
                String targetMethodName = targetMethod[strIndex];
                // 循环ms方法数组，找到目标方法（strBody中指定的方法）并调用
                for(int i =   0; i < ms.length; i++) {
                    Method srcMethod = ms[i];
                    int len = targetMethodName.indexOf(".") <   0 ? targetMethodName.length() : targetMethodName.indexOf(".");
                    if (srcMethod.getName().equals(targetMethodName.substring(  0, len))) {
                        HSSFCell cell = row.createCell(strIndex);
                        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                        cell.setCellStyle(bodyStyle);
                        try {
                            // 如果方法返回一个引用类型的值
                            if (targetMethodName.contains(".")) {
                                cell.setCellValue(referenceInvoke(targetMethodName, obj));
                                // 如果方法返回一个普通属性
                            } else {
                                Object object = srcMethod.invoke(obj);

                                if(object != null)
                                {
                                    cell.setCellValue((object).toString());
                                    try{
                                        if(object.getClass() == Date.class)
                                        {
                                            cell.setCellValue(DateUtil.parseToDate((object).toString(),"yyyy-MM-dd hh:mm:ss"));
                                        }
                                    }
                                    catch(Exception e) {
                                        e.printStackTrace();
                                    }
                                }

                                else
                                    cell.setCellValue("");
                                //自适应宽度
                                if((sheet.getColumnWidth(strIndex)) < cell.getStringCellValue().getBytes().length * 400)
                                {
                                    sheet.setColumnWidth(strIndex, cell.getStringCellValue().getBytes().length * 400);
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

    }
    /**
     * 方法返回的是一个对象的引用（如：getHomeplace.getName类型的方法序列）
     *      按方法序列逐层调用直到最后放回基本类型的值
     *
     * @param targetMethod  :   obj对象所包含的方法列
     * @param obj           :   待处理的对象
     * @return
     */
    private static String referenceInvoke(String targetMethod, Object obj) {
        // 截取方法序列的第一个方法(即截取属于obj对象的方法：getHomeplace())
        String refMethod = targetMethod.substring(0, targetMethod.indexOf("."));
        // 获得后续方法序列(getName())
        targetMethod = targetMethod.substring(targetMethod.indexOf(".") + 1);
        try {
            // 获得第一个方法的执行结果(即obj方法执行的结果：obj.getHomeplace())
            obj = obj.getClass().getMethod(refMethod).invoke(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 如果方法序列没到最后一节
        if (targetMethod.contains(".")) {
            return referenceInvoke(targetMethod, obj);
            // 如果方法序列到达最后一节
        } else {
            try {
                if(obj == null)
                    return "";
                // 通过obj对象获得该方法链的最后一个方法并调用
                Method tarMethod = obj.getClass().getMethod(targetMethod);
                return tarMethod.invoke(obj).toString();
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

    }
    /**
     * 保存Excel文件
     *
     * @param workbook  :   Excel工作簿
     * @param outputPath:   Excel文件保存路径
     */
    private static void saveExcelFile(HSSFWorkbook workbook, String outputPath) {
        try {
            FileOutputStream fos = new FileOutputStream(outputPath);
            workbook.write(fos);

            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 下载设置
     */
    public static void downLoadData(HttpServletResponse response,
                                    String path, String uploadedFileName) {
        try
        {
            response.setHeader("Content-disposition", "attachment; filename="
                    + new String(URLEncoder
                    .encode(uploadedFileName, "utf-8")));
        } catch (UnsupportedEncodingException e1)
        {
            e1.printStackTrace();
        }
        response.setHeader("Content-Type", "application/octet-stream");

        File file = new File(path);
        BufferedInputStream bis = null;// 读excel
        BufferedOutputStream bos = null;// 输出

        try {
            // 读取excel文件
            bis = new BufferedInputStream(new FileInputStream(file));
            // 写入response的输出流中
            bos = new BufferedOutputStream(response.getOutputStream());
            byte[] buff = new byte[2048];/* 设置缓存 */
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
            bos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bis != null)
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if (bos != null)
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
    public static HSSFCellStyle setHeadStyle(HSSFWorkbook workBook) {
        headStyle = workBook.createCellStyle();

        headStyle.setFillForegroundColor(HSSFColor.WHITE.index);
        headStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        headStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        headStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        headStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        headStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 生成字体
        if(headFont == null) {
            headFont = workBook.createFont();
        }
        headFont.setColor(HSSFColor.BLACK.index);
        headFont.setFontHeightInPoints((short) 12);
        headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样样式
        headStyle.setFont(headFont);
        return headStyle;

    }



    public static HSSFCellStyle setbodyStyle(HSSFWorkbook workBook) {
        bodyStyle = workBook.createCellStyle();
        bodyStyle.setFillForegroundColor(HSSFColor.WHITE.index);
        bodyStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        bodyStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        bodyStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        bodyStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        bodyStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        bodyStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        bodyStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 生成字体
        if(bodyFont == null) {
            bodyFont = workBook.createFont();
        }
        bodyFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        // 把字体应用到当前的样样式
        bodyStyle.setFont(bodyFont);
        return bodyStyle;
    }
}
