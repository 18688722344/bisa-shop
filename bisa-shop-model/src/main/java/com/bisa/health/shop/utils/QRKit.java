package com.bisa.health.shop.utils;

import java.awt.image.BufferedImage;

import com.google.zxing.common.BitMatrix;

/**
 * 获取微信支付二维码图片 当前用户ID，用来当作商户订单号，防止重复支付
 */

public class QRKit {

	private static final int BLACK = 0xFF000000;
	private static final int WHITE = 0xFFFFFFFF;

	/**
	 * 转换
	 */
	public static BufferedImage toBufferedImage(BitMatrix matrix) {
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
			}
		}
		return image;
	}

}
