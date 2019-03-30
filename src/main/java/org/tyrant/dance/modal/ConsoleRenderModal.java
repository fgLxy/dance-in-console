package org.tyrant.dance.modal;

import org.fusesource.jansi.Ansi;
import org.tyrant.dance.data.ColorChar;
import org.tyrant.dance.data.Frame;

public class ConsoleRenderModal {
	private static Character EMPTY_CHAR = ' ';
	
	/**
	 *  渲染一帧画面
	 * @param frame
	 */
	public static void render(Frame frame) {
		System.out.println(generateAnsi(frame));
	}
	 
	private static Ansi generateAnsi(Frame frame) {
		try {
			Ansi ansi = Ansi.ansi().eraseScreen();
			ansi.cursor(1, 1);
			ColorChar[][] datas = frame.getDatas();
			for (int i = 0; i < frame.getHeight(); i++) {
				for (int j = 0; j < frame.getWidth(); j++) {
					if (i >= datas.length || j >= datas[i].length || datas[i][j] == null) {
						ansi = ansi.a(EMPTY_CHAR);
						continue;
					}
					ColorChar colorChar = datas[i][j];
					ansi = ansi.fg(colorChar.getColor()).a(colorChar.getValue());
				}
				ansi.newline();
			}
			return ansi.reset();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
