package com.yeepay.g3.ymf.boss.utils;/**
 * Created by jiwei.lv on 16/8/23.
 */


import org.dom4j.Attribute;
import org.dom4j.Element;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jiwei.lv
 * @create 2016-08-16/8/23
 */

public class XmlReader {

    private List<String> fileds = new ArrayList<String>();
    private List<String> fileNames = new ArrayList<String>();
    private static Map<String, FileFormat> map;

    public static List<Format> readFile(String stringReader, String path) {
        String name = stringReader.replaceAll(".xml", "").trim();
        if (map == null || map.entrySet().size() <= 0) {
            initMap(path);
        }
        return map.get(name).getFormat();
    }

    private static void initMap(String path) {
        try {
            File file = new File(path);
            File[] files = file.listFiles();
            JAXBContext jc = JAXBContext.newInstance(FileFormat.class);
            Unmarshaller u = jc.createUnmarshaller();
            map = new HashMap<String, FileFormat>();
            for (File file1 : files) {
                if (file1.getName().endsWith(".xml")) {
                    String name = file1.getName().replaceAll(".xml", "");
                    FileFormat o = (FileFormat) u.unmarshal(file1);
                    map.put(name, o);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void getFiledList(Element node) {
        List<Attribute> list = node.attributes();
        for (Attribute attribute : list) {
            fileds.add(attribute.getValue());
        }

    }


    //遍历当前节点下的所有节点
    public void listNodes(Element node) {
        // System.out.println("当前节点的名称：" + node.getName());
        //首先获取当前节点的所有属性节点
        List<Attribute> list = node.attributes();
        //遍历属性节点
        for (Attribute attribute : list) {
            System.out.println("属性" + attribute.getName() + ":" + attribute.getValue());
            fileds.add(attribute.getValue());
        }
        //如果当前节点内容不为空，则输出
//        if(!(node.getTextTrim().equals(""))){
//
//             System.out.println( node.getName() + "：" + node.getText());
//        }
//        //同时迭代当前节点下面的所有子节点
//        //使用递归
//        Iterator<Element> iterator = node.elementIterator();
//        while(iterator.hasNext()){
//            Element e = iterator.next();
//            listNodes(e);
//        }


    }


}
