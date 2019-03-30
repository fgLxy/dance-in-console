package org.tyrant.dance.data;

import org.fusesource.jansi.Ansi.Color;

public class ColorChar {
	//字符内容，为空就是这里为不可见
	private Character value;
	//字符颜色
	private Color color;
	
	public ColorChar(Character value, Color color) {
		this.value = value;
		this.color = color;
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public Character getValue() {
		return this.value;
	}
}
