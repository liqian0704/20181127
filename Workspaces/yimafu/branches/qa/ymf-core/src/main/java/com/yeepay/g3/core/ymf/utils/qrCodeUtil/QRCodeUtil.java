package com.yeepay.g3.core.ymf.utils.qrCodeUtil;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.yeepay.g3.core.ymf.constants.Constants;
import com.yeepay.g3.core.ymf.entity.qrcode.QRCode;
import com.yeepay.g3.core.ymf.utils.ConfigureSetting;
import com.yeepay.g3.core.ymf.utils.security.SpaySignUtil;
import com.yeepay.g3.facade.ymf.enumtype.MaterialStatus;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.utils.ftp.FtpFileStoreHelper;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;

/**
 * 二维码生成工具类
 */
public class QRCodeUtil {

    private static final Logger log = LoggerFactory.getLogger(QRCodeUtil.class);

    private static final String CHARSET = "utf-8";
    private static final String FORMAT_NAME = "JPG";
    // 二维码尺寸
    private static final int QRCODE_SIZE = 300;
    // LOGO宽度
    private static final int WIDTH = 70;
    // LOGO高度
    private static final int HEIGHT = 70;

    private static BufferedImage createImage(String content, String imgPath,
                                             boolean needCompress) throws Exception {
        Hashtable<EncodeHintType,Object> hints = new Hashtable<EncodeHintType,Object>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        hints.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content,
                BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE, hints);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000
                        : 0xFFFFFFFF);
            }
        }
        if (imgPath == null || "".equals(imgPath)) {
            return image;
        }
        // 插入图片
        QRCodeUtil.insertImage(image, imgPath, needCompress);
        return image;
    }

    private static BufferedImage createImage(String content, InputStream imgIs,
                                             boolean needCompress) throws Exception {
        Hashtable<EncodeHintType,Object> hints = new Hashtable<EncodeHintType,Object>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        hints.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content,
                BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE, hints);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000
                        : 0xFFFFFFFF);
            }
        }
        // 插入图片
        QRCodeUtil.insertImage(image, imgIs, needCompress);
        return image;
    }

    private static void insertImage(BufferedImage source, InputStream imgIs,
                                    boolean needCompress) throws Exception {
        if (null == imgIs) {
            log.info("传入的图片流是空");
            return;
        }
        Image src = ImageIO.read(imgIs);
        int width = src.getWidth(null);
        int height = src.getHeight(null);
        if (needCompress) { // 压缩LOGO
            if (width > WIDTH) {
                width = WIDTH;
            }
            if (height > HEIGHT) {
                height = HEIGHT;
            }
            Image image = src.getScaledInstance(width, height,
                    Image.SCALE_SMOOTH);
            BufferedImage tag = new BufferedImage(width, height,
                    BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            g.drawImage(image, 0, 0, null); // 绘制缩小后的图
            g.dispose();
            src = image;
        }
        // 插入LOGO
        Graphics2D graph = source.createGraphics();
        int x = (QRCODE_SIZE - width) / 2;
        int y = (QRCODE_SIZE - height) / 2;
        graph.drawImage(src, x, y, width, height, null);
        Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
        graph.setStroke(new BasicStroke(3f));
        graph.draw(shape);
        graph.dispose();
    }

    private static void insertImage(BufferedImage source, String imgPath,
                                    boolean needCompress) throws Exception {
        File file = new File(imgPath);
        if (!file.exists()) {
            log.info("" + imgPath + "   该文件不存在！");
            return;
        }
        insertImage(source, new FileInputStream(file), needCompress);
    }


    public static String encode(String content, String imgPath, String destPath,
                                boolean needCompress) throws Exception {
        BufferedImage image = QRCodeUtil.createImage(content, imgPath,
                needCompress);
        mkdirs(destPath);
        String file = new Random().nextInt(99999999) + ".jpg";
        ImageIO.write(image, FORMAT_NAME, new File(destPath + "/" + file));
        return destPath + "/" + file;
    }

    public static String encodeIS(String content, InputStream imgIs, String destPath,
                                boolean needCompress) throws Exception {
        BufferedImage image = QRCodeUtil.createImage(content, imgIs,
                needCompress);
        mkdirs(destPath);
        String file = new Random().nextInt(99999999) + ".jpg";
        ImageIO.write(image, FORMAT_NAME, new File(destPath + "/" + file));
        return destPath + "/" + file;
    }


    public static void mkdirs(String destPath) {
        File file = new File(destPath);
        //当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir．(mkdir如果父目录不存在则会抛出异常)
        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
        }
    }


    public static void encode(String content, String imgPath, String destPath)
            throws Exception {
        encode(content, imgPath, destPath, false);
    }


    public static void encode(String content, String destPath,
                              boolean needCompress) throws Exception {
        encode(content, null, destPath, needCompress);
    }


    public static void encode(String content, String destPath) throws Exception {
        encode(content, null, destPath, false);
    }


    public static void encode(String content, String imgPath,
                              OutputStream output, boolean needCompress) throws Exception {
        BufferedImage image = QRCodeUtil.createImage(content, imgPath,
                needCompress);
        ImageIO.write(image, FORMAT_NAME, output);
    }


    public static void encode(String content, OutputStream output)
            throws Exception {
        QRCodeUtil.encode(content, null, output, false);
    }


    public static String decode(File file) throws Exception {
        BufferedImage image;
        image = ImageIO.read(file);
        if (image == null) {
            return null;
        }
        BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(
                image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Result result;
        Hashtable hints = new Hashtable();
        hints.put(DecodeHintType.CHARACTER_SET, CHARSET);
        result = new MultiFormatReader().decode(bitmap, hints);
        String resultStr = result.getText();
        return resultStr;
    }


    public static String decode(String path) throws Exception {
        return QRCodeUtil.decode(new File(path));
    }

    public static QRCode createQrCode(QRCode qrCode,String appType,String customerLogo) {
        qrCode.setCreateTime(new Date());
        qrCode.setStatus(MaterialStatus.ACTIVE);
        qrCode.setOptimisitc(0L);
        String qrId = qrCode.getQrId();
        String destPath = System.getProperty("java.io.tmpdir");
        Map<String,String> data = new HashMap<String,String>() ;
        data.put("qrCode",qrId) ;
        String newSign = SpaySignUtil.sign(data) ;
        String prefix= ConfigureSetting.getValue(Constants.YMF_PAY_HOST,  "");
        if(prefix==null||"".equals(prefix)){
            log.info("prefix is null");
            return null;
        }
        String text = prefix+"/"+appType+"/index?qrCode="+qrId+"&sign="+newSign;
        String fileName = null;
        FtpFileStoreHelper helper = FtpFileStoreHelper.getHelper("ucm");
        try {
            if(customerLogo!=null&&!"".equals(customerLogo)) {
                int begin = customerLogo.indexOf("ucm/");
                String str = customerLogo.substring(begin+4,customerLogo.length());
                log.info(str);
                InputStream is = helper.getStream(str);
                fileName = QRCodeUtil.encodeIS(text, is, destPath, true);
            }else{
                fileName = QRCodeUtil.encode(text, "", destPath, true);
            }
            if(fileName!=null&&!"".equals(fileName)) {
                File file = new File(fileName);
                String fileLink = null;
                fileLink = helper.upload(file, "jpg");
                qrCode.setFtpUrl(fileLink);
                return qrCode;
            }else{
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
