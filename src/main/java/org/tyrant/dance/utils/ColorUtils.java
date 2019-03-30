package org.tyrant.dance.utils;

import org.fusesource.jansi.Ansi.Color;

public class ColorUtils {
	
	private static Color[] COLOR_ARR = new Color[] {
			Color.BLACK,
			Color.RED,
			Color.GREEN,
			Color.YELLOW,
			Color.BLUE,
			Color.MAGENTA,
			Color.CYAN,
			Color.WHITE,
			Color.DEFAULT
	};
	
	private static java.awt.Color[] AWT_COLOR_ARR = new java.awt.Color[] {
			java.awt.Color.BLACK,
			java.awt.Color.RED,
			java.awt.Color.GREEN,
			java.awt.Color.YELLOW,
			java.awt.Color.BLUE,
			java.awt.Color.MAGENTA,
			java.awt.Color.CYAN,
			java.awt.Color.WHITE,
	};
	
	public static Color getColor(String name) {
		switch(name) {
		case "black": return COLOR_ARR[0];
		case "red": return COLOR_ARR[1];
		case "green": return COLOR_ARR[2];
		case "yellow": return COLOR_ARR[3];
		case "blue": return COLOR_ARR[4];
		case "magenta": return COLOR_ARR[5];
		case "cyan": return COLOR_ARR[6];
		case "white": return COLOR_ARR[7];
		default: return COLOR_ARR[8];
		}
	}
	
	public static Color getNearColor(int red, int green, int blue) {
		int minDistance = Integer.MAX_VALUE;
		int minIdx = 0;
		java.awt.Color color = new java.awt.Color(red, green, blue);
		for (int i = 0; i < AWT_COLOR_ARR.length; i++) {
			int distance = colorDistance(color, AWT_COLOR_ARR[i]);
			if (distance < minDistance) {
				minDistance = distance;
				minIdx = i;
			}
		}
		return COLOR_ARR[minIdx];
	}
	
	private static int colorDistance(java.awt.Color color, java.awt.Color other) {
		int distance = (int) (Math.pow(color.getRed() - other.getRed(), 2) 
				+ Math.pow(color.getGreen() - other.getGreen(), 2)
				+ Math.pow(color.getBlue() - other.getBlue(), 2));
		return distance;
	}
	
}
