//package com.yeepay.g3.core.ymf.generator;
//
//import com.yeepay.g3.common.Amount;
//import com.yeepay.g3.core.ymf.entity.order.Order;
//import com.yeepay.g3.core.ymf.utils.FileUtils;
//import com.yeepay.g3.facade.ymf.dto.order.OrderDTO;
//import org.junit.Test;
//
//import java.io.*;
//import java.lang.reflect.Field;
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
///**
// * Title:
// * Description:
// * Copyright: Copyright (c)2015
// * Company: YeePay
// *
// * @author chen.liu on 16/8/16.
// */
//public class GenrateMybatisXML {
//
//    String enter = "\r\n";
//    String tableName = "YMF_ORDER";
//    Class clz = Order.class;
//    String namespace = "com.yeepay.g3.core.ymf.dao.order.OrderDao";
//
//    @Test
//    public void execute() {
//
//        StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>"+enter
//                +"<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\" >"+enter
//                +"<mapper namespace=\""+namespace+"\" >"+enter);
//
//        Field[] fieldlist = clz.getDeclaredFields();
//        xml.append(enter);
//        generateResultXml(clz, xml, fieldlist);
//        xml.append(enter);
//        generateSqlColumn(clz, xml, fieldlist);
//        xml.append(enter);
//        generateInsertSql(clz, xml, fieldlist);
//        xml.append(enter);
//        generateDeleteSql(clz, xml);
//        xml.append(enter);
//        generateUpdateSql(clz, xml, fieldlist);
//        xml.append(enter);
//        generateSelectSql(clz, xml);
//        xml.append(enter);
//        xml.append("</mapper>");
//
//        System.out.println(xml.toString());
//
//        try {
//            RandomAccessFile file = new RandomAccessFile("src/main/resources/ymf-mapper/order/OrderDao.xml", "rw");
//            file.write(xml.toString().getBytes());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    private void generateSelectSql(Class clz, StringBuffer xml) {
//
//        xml.append("  <select id=\"get\" resultMap=\""+clz.getSimpleName()+"Map\" parameterType=\"java.lang.Long\" >"+enter+
//                "    select "+enter+
//                "    <include refid=\""+clz.getSimpleName()+"Columns\" />"+enter+
//                "    from "+enter+
//                "       " + tableName +enter+
//                "    where "+enter+
//                "       ID = #{id,jdbcType=BIGINT}"+enter+
//                "  </select>"+enter);
//    }
//
//    private void generateUpdateSql(Class clz, StringBuffer xml,
//                                   Field[] fieldlist) {
//        xml.append("  <update id=\"update\" parameterType=\""+clz.getSimpleName()+"\" >"+enter+
//                "    update" + tableName +enter+
//                "    <set>"+enter+
//                "        VERSION =VERSION+1,"+enter);
//
//        for (int i = 0; i < fieldlist.length; i++) {
//            Field fld = fieldlist[i];
//            String fldName = fld.getName();
//            String dbName = getDBName(fldName);
//
//            if ("serialVersionUID".equals(fldName)){
//                continue;
//            }else if ("version".equals(fldName.toLowerCase())){
//                continue;
//            }else if ("id".equals(fldName.toLowerCase())){
//                continue;
//            }
//
//            xml.append("      <if test=\""+fldName+" != null\" >"+enter);
//            if (fld.getType() == Long.class || fld.getType() == long.class) {
//                xml.append("        "+dbName+" = #{"+fldName+",jdbcType=BIGINT}");
//            }else if(fld.getType() == Integer.class || fld.getType() == int.class){
//                xml.append("        "+dbName+" = #{"+fldName+",jdbcType=INTEGER}");
//            }else if(fld.getType() == String.class){
//                xml.append("        "+dbName+" = #{"+fldName+",jdbcType=VARCHAR}");
//            }else if(fld.getType() == Amount.class|| fld.getType()==Double.class
//                    || fld.getType()==double.class || fld.getType()==BigDecimal.class
//                    || fld.getType()==Float.class || fld.getType()==float.class){
//                xml.append("        "+dbName+" = #{"+fldName+",jdbcType=DECIMAL}");
//            }else if(fld.getType() == Date.class){
//                if(fldName.contains("Date") || fldName.contains("Date".toLowerCase())
//                        || fldName.contains("Date".toUpperCase()) ){
//                    xml.append("        "+dbName+" =  #{"+fldName+",jdbcType=DATE}");
//                }else{
//                    xml.append("        "+dbName+" = #{"+fldName+",jdbcType=TIMESTAMP}");
//                }
//            }else{
//                xml.append("        "+dbName+" = #{"+fldName+",jdbcType=VARCHAR,javaType="
//                        +fld.getType().getName()+"}");
//            }
//
//            if(i!=fieldlist.length-1){
//                xml.append(",");
//            }
//            xml.append(enter);
//
//            xml.append("      </if>"+enter);
//
//        }
//        xml.append("    </set>"+enter);
//        xml.append("    where"+enter);
//        xml.append("      ID = #{id,jdbcType=BIGINT}"+enter);
//        xml.append("      and VERSION = #{version,jdbcType=BIGINT}"+enter);
//        xml.append("  </update>"+enter);
//    }
//
//    private void generateDeleteSql(Class clz, StringBuffer xml) {
//
//        String tbName = clz.getSimpleName().replace("Entity", "").toUpperCase();
//
//        xml.append("  <delete id=\"delete\" parameterType=\"java.lang.Long\" >"+enter+
//                "    delete from "+enter+
//                "      " + tableName +enter+
//                "    where "+enter+
//                "      ID = #{id,jdbcType=BIGINT}"+enter+
//                "  </delete>"+enter);
//
//    }
//
//    private void generateInsertSql(Class clz, StringBuffer xml,
//                                   Field[] fieldlist) {
//        String tbName = clz.getSimpleName().replace("Entity", "").toUpperCase();
//        xml.append("  <insert id=\"insert\"  parameterType=\""+clz.getSimpleName()+"\" >"+enter+
//                "    <selectKey resultType=\"long\" order=\"BEFORE\" keyProperty=\"id\">"+enter+
//                "      values nextval for SEQ_MPAY_"+tbName+"_ID"+enter+
//                "    </selectKey>"+enter+
//                "    insert into " + tableName +enter+"    ("+enter+
//                "      <include refid=\""+clz.getSimpleName()+"Columns\" />"+enter+"    )"+enter+
//                "    values"+enter+"    ("+enter);
//
//        for (int i = 0; i < fieldlist.length; i++) {
//            Field fld = fieldlist[i];
//            String fldName = fld.getName();
//            if ("serialVersionUID".equals(fldName)){
//                continue;
//            }else if ("version".equals(fldName.toLowerCase())){
//                xml.append("      0");
//            }else if("createdate".equals(fldName.toLowerCase())){
//                xml.append("      current_date");
//            }else if("lastmodifytime".equals(fldName.toLowerCase())){
//                xml.append("      current_timestamp");
//            }else if (fld.getType() == Long.class || fld.getType() == long.class) {
//                xml.append("      #{"+fldName+",jdbcType=BIGINT}");
//            }else if(fld.getType() == Integer.class || fld.getType() == int.class){
//                xml.append("      #{"+fldName+",jdbcType=INTEGER}");
//            }else if(fld.getType() == String.class){
//                xml.append("      #{"+fldName+",jdbcType=VARCHAR}");
//            }else if(fld.getType() == Amount.class|| fld.getType()==Double.class
//                    || fld.getType()==double.class || fld.getType()==BigDecimal.class
//                    || fld.getType()==Float.class || fld.getType()==float.class){
//                xml.append("      #{"+fldName+",jdbcType=DECIMAL}");
//            }else if(fld.getType() == Date.class){
//                if(fldName.contains("Date") || fldName.contains("Date".toLowerCase())
//                        || fldName.contains("Date".toUpperCase()) ){
//                    xml.append("      #{"+fldName+",jdbcType=DATE}");
//                }else{
//                    xml.append("      #{"+fldName+",jdbcType=TIMESTAMP}");
//                }
//            }else{
//                xml.append("      #{"+fldName+",jdbcType=VARCHAR,javaType="
//                        +fld.getType().getName()+"}");
//            }
//
//            if(i!=fieldlist.length-1){
//                xml.append(",");
//            }
//            xml.append(enter);
//
//        }
//
//        xml.append("    )"+enter+"  </insert>"+enter);
//
//    }
//
//    private void generateSqlColumn(Class clz, StringBuffer xml,
//                                   Field[] fieldlist) {
//        xml.append("  <sql id=\""+clz.getSimpleName()+"Columns\" >"+enter);
//
//        for (int i = 0; i < fieldlist.length; i++) {
//            Field fld = fieldlist[i];
//            String fldName = fld.getName();
//
//            if ("serialVersionUID".equals(fldName))	{
//                if(i==0){
//                    xml.append("    ");
//                    continue;
//                }
//            }
//
//            if(i==0){
//                xml.append("    "+getDBName(fldName)+",");
//            }else if(i==fieldlist.length-1){
//                xml.append(getDBName(fldName)+enter);
//            }else if(i%5==0){
//                xml.append(getDBName(fldName)+","+enter+"    ");
//            }else{
//                xml.append(getDBName(fldName)+",");
//            }
//
//        }
//
//        xml.append("  </sql>"+enter);
//
//    }
//
//    private void generateResultXml(Class clz, StringBuffer xml,
//                                   Field[] fieldlist) {
//        xml.append("  <resultMap id=\""+clz.getSimpleName()+"Map\" type=\""+clz.getCanonicalName()+"\" >"+enter);
//        xml.append("    <id column=\"ID\" property=\"id\" jdbcType=\"BIGINT\" />"+enter);
//
//        for (int i = 0; i < fieldlist.length; i++) {
//            Field fld = fieldlist[i];
//            String fldName = fld.getName();
//            String dbName = getDBName(fldName);
//
//            if ("serialVersionUID".equals(fld.getName()))
//                continue;
//            if ("id".equals(fld.getName().toLowerCase()))
//                continue;
//
//            if (fld.getType() == Long.class || fld.getType() == long.class) {
//                xml.append("    <result column=\""+dbName+"\" property=\""+fldName+"\" jdbcType=\"BIGINT\" />"+enter);
//            }else if(fld.getType() == Integer.class || fld.getType() == int.class){
//                xml.append("    <result column=\""+dbName+"\" property=\""+fldName+"\" jdbcType=\"INTEGER\" />"+enter);
//            }else if(fld.getType() == String.class){
//                xml.append("    <result column=\""+dbName+"\" property=\""+fldName+"\" jdbcType=\"VARCHAR\" />"+enter);
//            }else if(fld.getType() == Amount.class|| fld.getType()==Double.class
//                    || fld.getType()==double.class || fld.getType()==BigDecimal.class
//                    || fld.getType()==Float.class || fld.getType()==float.class){
//                xml.append("    <result column=\""+dbName+"\" property=\""+fldName+"\" jdbcType=\"DECIMAL\" />"+enter);
//            }else if(fld.getType() == Date.class){
//                if(fldName.contains("Date") || fldName.contains("Date".toLowerCase())
//                        || fldName.contains("Date".toUpperCase()) ){
//                    xml.append("    <result column=\""+dbName+"\" property=\""+fldName+"\" jdbcType=\"DATE\" />"+enter);
//                }else{
//                    xml.append("    <result column=\""+dbName+"\" property=\""+fldName+"\" jdbcType=\"TIMESTAMP\" />"+enter);
//                }
//            }else{
//                xml.append("    <result column=\""+dbName+"\" property=\""
//                        +fldName+"\" jdbcType=\"VARCHAR\""
//                        + " javaType=\""+fld.getType().getName()+"\" />"+enter);
//            }
//
//        }
//        xml.append("  </resultMap>"+enter);
//    }
//
//    public String getDBName(String fldName) {
//        List<Integer> upperList = new ArrayList<Integer>();
//        char[] chars = fldName.toCharArray();
//        for (int i = 0; i < chars.length; i++) {
//            char c = fldName.charAt(i);
//            if (Character.isUpperCase(c)){
//                upperList.add(i);
//            }
//        }
//        //去掉0
//        if(upperList.size()>0 && upperList.contains(0)){
//            upperList.remove(0);
//        }else if(upperList.size()==0){
//            return fldName.toUpperCase();
//        }
//
//        StringBuffer result = new StringBuffer();
//        int oldInd = 0;
//        for (int i = 0; i < upperList.size(); i++) {
//            int upperIndex = upperList.get(i);
//            if(i==0){
//                result.append(fldName.substring(0, upperIndex).toUpperCase()).append("_");
//            }
//            if(i==upperList.size()-1){
//                //防止size为1 截取两次
//                if(upperList.size()!=1){
//                    //连续出现大写的时候不至于每个大写字母都断开
//                    if(upperIndex-upperList.get(i-1)==1){
//                        return result.append(fldName.substring(oldInd).toUpperCase()).toString();
//                    }else{
//                        result.append(fldName.substring(oldInd, upperIndex).toUpperCase()).append("_");
//                    }
//                }
//                result.append(fldName.substring(upperIndex).toUpperCase());
//            }
//            if(i!=0 && i!=upperList.size()-1){
//                //连续出现大写的时候不至于每个大写字母都断开
//                if(upperIndex-upperList.get(i-1)==1)	continue;
//                result.append(fldName.substring(oldInd, upperIndex).toUpperCase()).append("_");
//            }
//            oldInd = upperIndex;
//        }
//        return result.toString();
//    }
//
//}
