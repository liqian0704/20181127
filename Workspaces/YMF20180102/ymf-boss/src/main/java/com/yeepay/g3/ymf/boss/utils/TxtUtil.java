package com.yeepay.g3.ymf.boss.utils;/**
 * Created by jiwei.lv on 16/8/23.
 */


import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author jiwei.lv
 * @create 2016-08-16/8/23
 */
public class TxtUtil {
    private static Logger logger = LoggerFactory.getLogger(TxtUtil.class);
    public static File writeTxtTitle(Map<String,String> map,String filePath,String readStr,String titlesplit,String fileName)throws IOException{
        File filePa = new File(filePath);

        if (!filePa.exists()) {
            filePa.mkdir();
            logger.info("文件夹不存在");
        }
        File file = new File(filePath+fileName);
        file.createNewFile();
        //定义文件名格式并创建
        FileWriter writer = new FileWriter(file);
        if (!map.isEmpty()) {
            //写入头信息
            String filein = "";
            for (Iterator tileIterator = map.entrySet().iterator(); tileIterator.hasNext();) {
                Map.Entry propertyEntry = (Map.Entry) tileIterator.next();
                filein +=  propertyEntry.getValue() == null ? "" :  String.valueOf(propertyEntry.getValue());
                if (tileIterator.hasNext()) {
                    filein = filein + titlesplit;
                }
            }
            try {
                writer.write(filein);
                writer.write("\r\n");
            } catch (IOException e1) {
                logger.error("创建Txt文件头报错!",e1);
            } finally {
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (IOException e2) {
                        logger.error("创建Txt文件头报错!",e2);
                    }
                }
            }
        }
        return file;
    }
    public static void writeTxtFile(File file,List contentList, Map<String,String> map,List<Format>  formatList,String readStr, int i,int totalcount) throws Exception {


        FileWriter writer = null;
        //写入详细信息
        int num = i+1;
        for (Iterator iterator = contentList.iterator(); iterator.hasNext();) {
            Map<String,String> txtMap = (Map<String,String>) iterator.next();
            String filein1 = "";
            int sum=1;
            for(String key:map.keySet()){
                for (int k=0;k<formatList.size();k++){
                    String field= formatList.get(k).getField();
                    if(key.equals(field)){
                        if (formatList.get(k).getFieldType() == null) {
                            filein1+= txtMap.get(key)==null ?"":String.valueOf(txtMap.get(key));
                        } else if (formatList.get(k).getFieldType().equals("date")) {
                            SimpleDateFormat format = new SimpleDateFormat(formatList.get(k).getFormat());
                            String strdate = String.valueOf(txtMap.get(key));
                            if (null != strdate && !"NULL".equals(strdate) && !"null".equals(strdate)) {
                                Date date = null;
                                date = format.parse(strdate);
                                String resultdate = format.format(date);
                                filein1+= resultdate;
                            }

                        } else if (formatList.get(k).getFieldType().equals("amount")) {
                            filein1 += AmountUtil.formatAmount(txtMap.get(key), formatList.get(k).getFormat());
                        }else if(formatList.get(k).getFieldType().equals("dict")){
                            DictUtils utils=new DictUtils(String.valueOf(formatList.get(k).getFormat()),String.valueOf(txtMap.get(key)));
                            filein1+= utils.getDictValue();
                        }
                        break;
                    }
                }

                if(sum!=map.keySet().size()){
                    filein1 = filein1 + readStr;
                }
                sum++;
            }
            try {
                writer = new FileWriter(file, true);
                writer.write(filein1);
                if (num < totalcount) {
                    writer.write("\r\n");
                }
                num++;
            } catch (IOException e1) {
                logger.error("创建Txt文件报错!",e1);
            } finally {
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (IOException e2) {
                        logger.error("创建Txt文件报错!",e2);
                    }
                }
            }
        }
    }
}
