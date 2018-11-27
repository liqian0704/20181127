package com.yeepay.g3.core.ymf.utils.common;


import com.yeepay.g3.utils.common.StringUtils;

import java.io.File;

/**
 * @author jiwei.lv
 * @create 2016-08-16/8/23
 */
public class FileManageUtil {

    /**
     * 删除文件夹和子文件
     *
     * @param file 文件夹
     */
    public static int deleteFiles(File file) {
        return deleteFileChild(file);
    }


    /**
     * 删除该目录filePath下的所有文件
     *
     * @param filePath 文件目录路径
     */
    public static int deleteFiles(String filePath) {
        return deleteFiles(new File(filePath));
    }

    /**
     * 合并路径
     * eg:
     * subs[0] = /apps/data
     * subs[1] = /tomcat/
     * subs[2] = nohup.out
     * 输出: /apps/data/tomcat/nohup.out
     *
     * @param paths 根目录
     * @return
     */
    public static String mergePath(String...paths) {
        StringBuilder sb = new StringBuilder();
        for (String path : paths) {
            if (null == path) {
                continue;
            }
            if (!path.startsWith(File.separator)) { // 子路径统一以separator开头
                path = File.separator + path;
            }
            if (path.endsWith(File.separator)) {
                path = path.substring(0, path.length() - 1); // 去除末尾的separator
            }
            sb.append(path);
        }
        return sb.toString();
    }

    /**
     * 递归删除
     * @param file
     * @return 删除文件数
     */
    public static int deleteFileChild(File file) {
        int count = 0;
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] children = file.listFiles();
                if (null != children) {
                    for (File child : children) {
                        if (child.exists()) {
                            if (child.isDirectory()) {
                                count += deleteFileChild(child);
                            } else if (child.isFile()) {
                                if (child.delete()) count++;
                            }

                        }
                    }
                }
            }
            if (file.delete()) count ++;
        }
        return count;
    }

    /**
     * 删除由POI产生的临时文件
     * @param root
     * @return
     */
    public static int deletePoiFile(String root) {
        int count = 0;
        if(StringUtils.isBlank(root)){
            return 0;
        }
        File file = new File(root);
        if(file.exists()){
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++){
                if (files[i].isFile()) {
                    if (files[i].getName().startsWith("poi-sxssf") && files[i].getName().endsWith(".xml")) {
                        files[i].delete();
                        count++;
                    }
                }
            }
            return count;
        }
        return 0;
    }
}
