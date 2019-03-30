package org.tyrant.dance.convert;

import java.awt.image.BufferedImage;

import org.fusesource.jansi.Ansi.Color;
import org.tyrant.dance.data.ColorChar;
import org.tyrant.dance.data.Frame;
import org.tyrant.dance.utils.ColorUtils;

public class ImageConvert {
	private static char[] CHAR_MAP = new char[] {
			'.',//0-24
			'-',//25-49
			'+',//50-74
			'=',//75-99
			'&',//100-124
			'o',//125-149
			'q',//150-174
			'$',//175-199
			'W',//200-224
			'%',//225-249
			'@'//250-255
	};
	
	public static Frame convert(BufferedImage img, int width, int height) {
		double dWidth = width;
		double dHeight = height;
		int accuracy = (int) Math.ceil(Math.max(img.getWidth()/dWidth, img.getHeight()/dHeight));
		ColorChar[][] datas = imageToString(img, accuracy);
		return new Frame(width, height, datas);
	}
	
	private static ColorChar[][] imageToString(BufferedImage img, int accuracy) {
		int width = (int) Math.ceil(img.getWidth() / (double)accuracy);
		int height = (int) Math.ceil(img.getHeight() / (double)accuracy);
		ColorChar[][] datas = new ColorChar[height][width];
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				datas[j][i] = getChar(i, j, img, accuracy);
			}
		}
		return datas;
		
	}
	
	private static ColorChar getChar(int x, int y, BufferedImage img, int accuracy) {
		int yBegin = accuracy * y;
		int yEnd = Math.min(accuracy * (y+1), img.getHeight());
		int xBegin = accuracy * x;
		int xEnd = Math.min(accuracy * (x+1), img.getWidth());
		int sum = 0;
		int count = 0;
		int sumR = 0;
		int sumG = 0;
		int sumB = 0;
		for (int j = yBegin; j < yEnd; j++) {
			for (int i = xBegin; i < xEnd; i++) {
				sum += argbToGray(img.getRGB(i, j));
				sumR += getRed(img.getRGB(i, j));
				sumG += getGreen(img.getRGB(i, j));
				sumB += getBlue(img.getRGB(i, j));
				count++;
			}
		}
		int index = CHAR_MAP.length - 1 - (sum / count) / 25;
		Color color = ColorUtils.getNearColor(sumR/count, sumG/count, sumB/count);
		return new ColorChar(CHAR_MAP[index], color);
	}
	

	private static int getRed(int argb) {
		int mask = 0X00FF;
		return (argb >> 16) & mask;
	}
	
	private static int getGreen(int argb) {
		int mask = 0X00FF;
		return (argb >> 8) & mask;
	}
	
	private static int getBlue(int argb) {
		int mask = 0X00FF;
		return argb & mask;
	}
	
	private static int argbToGray(int argb) {
		int mask = 0X00FF;
		int a = (argb >> 24) & mask;
		int r = (argb >> 16) & mask;
		int g = (argb >> 8) & mask;
		int b = argb & mask;
		double alpha = a/255.0;
		int gray = (r*30 + g*59 + b*11 + 50) / 100;
		return (int) (255 - ((255 - gray)*alpha));
	}
}
