/**
 * 
 */
package com.yeepay.g3.core.ymf.utils.qrCodeUtil;

import jp.sourceforge.qrcode.data.QRCodeImage;

import java.awt.image.BufferedImage;


/**
 * @Description 二维码图片对象
 * @author zhenping.zhou
 * @CreateTime 2015-1-30 下午2:16:02
 * @version 1.0
 */
public class TwoDimensionCodeImage implements QRCodeImage {

	BufferedImage bufImg;

	public TwoDimensionCodeImage(BufferedImage bufImg) {
		this.bufImg = bufImg;
	}

	@Override
	public int getHeight() {
		return bufImg.getHeight();
	}

	@Override
	public int getPixel(int x, int y) {
		return bufImg.getRGB(x, y);
	}

	@Override
	public int getWidth() {
		return bufImg.getWidth();
	}

}