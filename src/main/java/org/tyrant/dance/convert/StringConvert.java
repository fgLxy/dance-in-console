package org.tyrant.dance.convert;

import org.fusesource.jansi.Ansi.Color;
import org.tyrant.dance.data.ColorChar;
import org.tyrant.dance.data.Frame;

public class StringConvert {
	
	public static Frame convert(String str, int width, int height, Color color) {
		ColorChar[][] datas = new ColorChar[height][width];
		Frame frame = new Frame(width, height, datas);
		String[] lines = str.split("\r\n");
		for (int i = 0; i < lines.length; i++) {
			setLine(frame, i, lines[i], color);
		}
		return frame;
	}
	
	public static Frame setLine(Frame frame, int row, String str, Color color) {
		return setSequence(frame, row, 0, str, color);
	}
	
	public static Frame setSequence(Frame frame, int row, int begin, String str, Color color) {
		ColorChar[][] datas = frame.getDatas();
		for (int i = begin; i < Math.min(str.length(), frame.getWidth()); i++) {
			datas[row][i] = str.charAt(i) == ' ' ? new ColorChar(str.charAt(i), Color.DEFAULT) 
					: new ColorChar(str.charAt(i), color);
		}
		return frame;
	}
	
}
