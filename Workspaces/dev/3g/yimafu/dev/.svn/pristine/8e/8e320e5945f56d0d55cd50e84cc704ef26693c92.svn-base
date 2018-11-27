package com.yeepay.g3.core.ymf.utils.common;

import com.yeepay.g3.facade.ymf.dto.common.ElectImageDto;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * 类名称: ElectronicVoucherUtil <br>
 * 类描述: <br>
 *
 * @author: dongxu.lu
 * @since: 16/10/11 下午2:44
 * @version: 1.0.0
 */
public class ElectronicVoucherUtil {
    private static Logger logger = LoggerFactory.getLogger(ElectronicVoucherUtil.class);
    private static final int WIDTH = 350;//小票的宽度
    private static final int HEIGHT = 450;//磁条卡的长度
    private static final int HEIGHTIC = 450;//IC小票的长度
    private static final int XXWIDTH = 325;//虚线长度
    private static final int DQWIDTH = 15;//对齐像素

    private ElectronicVoucherUtil(){

    }
    public static ElectronicVoucherUtil getInstance(){

     return new ElectronicVoucherUtil();
    }

    void createImage(String fileLocation, BufferedImage image,String customerOrderId) throws Exception {
        try {
//            String fileName = new Random().nextInt(99999999) + ".jpg";
            String fileName=customerOrderId+".jpg";
            File file= new File(fileLocation + "/" + fileName);
//            FileOutputStream fos = new FileOutputStream(file);
//            ImageOutputStream fos = new ImageOutputStream(file);
//            BufferedOutputStream bos = new BufferedOutputStream(fos);
//            ImageOutputStream ios = ImageIO.read(fos);
//            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(bos);
//            encoder.encode(image);
            ImageIO.write(image,"JPG",file);
//            bos.close();
        } catch (Exception e) {
            logger.error("createImage error!",e);
            throw e;
        }
    }

    /**
     *  生成电子凭证图片
     * @param dto 凭证参数
     * @param imgurl 图片保存路径
     * @param imageIO 背景图片路径
     * 注: 图片大小  350X450
     */
    public void voucherGeneration(ElectImageDto dto,InputStream imageIO, String imgurl) throws Exception{
        if(null==dto){
            logger.error("ElectImageDto is null!");
            throw new Exception("电子凭证参数为空");
        }
        logger.info("voucherGeneration begin!ElectImageDto :"+dto.toString());
        BufferedImage image = new BufferedImage(WIDTH, HEIGHTIC, BufferedImage.TYPE_INT_RGB);//获得画布
        Graphics graphics = image.getGraphics();//通过画布获得画笔
        float[] colors = Color.RGBtoHSB(144,144,144,null);
        graphics.setColor(Color.getHSBColor(colors[0],colors[1],colors[2]));//给画笔填充颜色 日期填充灰色
        graphics.fillRect(0, 0, WIDTH, HEIGHT);//设置矩形大小
        try {
//            BufferedImage small = ImageIO.read(new File(bgImgUrl));
            String filePath=System.getProperty("java.io.tmpdir");
            File file=new File(filePath);
            ImageIO.setCacheDirectory(file);
            BufferedImage small = ImageIO.read(imageIO);
            graphics.drawImage(small, 0, 0, WIDTH, HEIGHT, null);
        } catch (IOException e) {
            logger.error("##ImageIO.read error",e);
            throw e;
        }
        int num = 70;
//        drawDashedLine(graphics, DQWIDTH, num, XXWIDTH, num);//画出第一条虚线
//        num+=45;
//        Font font=graphics.getFont();
//        String name=font.getFontName();
        String name="黑体";
        logger.info(name);
        graphics.setFont(new Font(name,Font.BOLD, 14));
        graphics.drawString("日 期 : " + dto.getTrxDate(), DQWIDTH, num);
        graphics.setColor(Color.black);
        num+=35;
        graphics.setFont(new Font(name, Font.BOLD, 24));
        graphics.drawString("" + dto.getStatus(), DQWIDTH, num);
        num+=25;
        graphics.setFont(new Font(name, Font.BOLD, 15));
        drawDashedLine(graphics, DQWIDTH, num, XXWIDTH, num);//画出第一条虚线
        num+=25;
        if(dto.getProductName().length()>16){
            graphics.drawString("商    品 : " + dto.getProductName().substring(0,15), DQWIDTH, num);
            num+=25;
            graphics.drawString("          " + dto.getProductName().substring(15), DQWIDTH, num);

        }else{
            graphics.drawString("商    品 : " + dto.getProductName(), DQWIDTH, num);
        }
        num+=25;
        if(dto.getCustomerName().length()>16){
            graphics.drawString("商户名称 : " + dto.getCustomerName().substring(0,15), DQWIDTH,num);
            num+=25;
            graphics.drawString("          " + dto.getCustomerName().substring(15), DQWIDTH,num);
        }else{
            graphics.drawString("商户名称 : " + dto.getCustomerName(), DQWIDTH,num);
            num+=25;
        }
        num+=25;
        drawDashedLine(graphics, DQWIDTH, num, XXWIDTH, num);//画出第一条虚线
        num+=25;
        if(null !=dto.getUserName()){
            int gh =dto.getUserName().length();
            if(dto.getUserName().length()>15){
                graphics.drawString("姓    名 : " + dto.getUserName().substring(0,14), DQWIDTH, num);
                num+=25;
                graphics.drawString("           " + dto.getUserName().substring(14), DQWIDTH, num);

            }else{
                graphics.drawString("姓    名 : " + dto.getUserName(), DQWIDTH, num);
            }
        }else{
            graphics.drawString("姓    名 : ", DQWIDTH, num);
        }
        num+=25;
            graphics.drawString("金    额 : " + dto.getTrxAmount(), DQWIDTH, num);
        num+=25;
        if(dto.getCustomerOrderId().length()>20){
            graphics.drawString("缴费单号 : " + dto.getCustomerOrderId().substring(0,19), DQWIDTH, num);
            num+=25;
            graphics.drawString("          " + dto.getCustomerOrderId().substring(19), DQWIDTH, num);
        }else{
            graphics.drawString("缴费单号 : " + dto.getCustomerOrderId(), DQWIDTH, num);
            num+=25;
        }
        num+=25;
            graphics.drawString("交易时间 : " + dto.getTradeTime(), DQWIDTH, num);
        graphics.setColor(Color.getHSBColor(colors[0],colors[1],colors[2]));//给画笔填充颜色 提醒填充灰色
        drawDashedLine(graphics, DQWIDTH, 410, XXWIDTH, 410);//画出第一条虚线
        graphics.setFont(new Font(name, Font.PLAIN, 14));
        graphics.drawString("*此电子凭证仅供参考,请以交易信息为准 ", DQWIDTH, 430);
        createImage(imgurl, image,dto.getCustomerOrderId());
    }

    /***
     * 画虚线
     *
     * @param graphics
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     */
    public static void drawDashedLine(Graphics graphics, int x1, int y1, int x2, int y2) {
        //从x2开始每隔1个像素 画一个长度为4个像素的线
        for (int i = x1; i < x2; i++) {
            graphics.drawLine(i, y1, i + 3, y2);
            i = i + 5;
        }
    }


}
