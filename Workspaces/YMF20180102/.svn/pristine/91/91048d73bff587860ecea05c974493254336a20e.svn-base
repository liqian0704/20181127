package com.yeepay.g3.core.ymf.utils.qrCodeUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @Description:
 * @Author: xiaobin.liu
 * @Date: 17/9/14
 * @Time: 上午11:40
 */
public class ZipCompress {
    static final int BUFFER = 8192;
    private File zipFile;

    public ZipCompress(String pathName) {
        this.zipFile = new File(pathName);
    }

    public void compress(List<String> souceFiles) {
        ZipOutputStream out = null;

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(this.zipFile);
            CheckedOutputStream cos = new CheckedOutputStream(fileOutputStream, new CRC32());
            out = new ZipOutputStream(cos);
            String basedir = "";
            Iterator var6 = souceFiles.iterator();

            while(var6.hasNext()) {
                String value = (String)var6.next();
                this.compress(new File(value), out, basedir);
            }

            out.close();
        } catch (Exception var8) {
            throw new RuntimeException(var8);
        }
    }

    public void compress(String srcPathName) {
        File file = new File(srcPathName);
        if (!file.exists()) {
            throw new RuntimeException(srcPathName + "不存在！");
        } else {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(this.zipFile);
                CheckedOutputStream cos = new CheckedOutputStream(fileOutputStream, new CRC32());
                ZipOutputStream out = new ZipOutputStream(cos);
                String basedir = "";
                this.compress(file, out, basedir);
                out.close();
            } catch (Exception var7) {
                throw new RuntimeException(var7);
            }
        }
    }

    private void compress(File file, ZipOutputStream out, String basedir) {
        if (file.isDirectory()) {
            this.compressDirectory(file, out, basedir);
        } else {
            this.compressFile(file, out, basedir);
        }

    }

    private void compressDirectory(File dir, ZipOutputStream out, String basedir) {
        if (dir.exists()) {
            File[] files = dir.listFiles();

            for(int i = 0; i < files.length; ++i) {
                this.compress(files[i], out, basedir + dir.getName() + "/");
            }

        }
    }

    private void compressFile(File file, ZipOutputStream out, String basedir) {
        if (file.exists()) {
            try {
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
                ZipEntry entry = new ZipEntry(basedir + file.getName());
                out.putNextEntry(entry);
                byte[] data = new byte[8192];

                int count;
                while((count = bis.read(data, 0, 8192)) != -1) {
                    out.write(data, 0, count);
                }

                bis.close();
            } catch (Exception var8) {
                throw new RuntimeException(var8);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        List<String> list = new ArrayList();
        list.add("/Users/yp-tc-m-2889/tmp/ymf.png");
        list.add("/Users/yp-tc-m-2889/tmp/ymf.png2");
        (new ZipCompress("/Users/yp-tc-m-2889/Desktop/a.zip")).compress((List)list);
    }
}
