package org.tyrant.dance;

import java.util.List;

import org.dom4j.DocumentException;
import org.tyrant.dance.data.Config;
import org.tyrant.dance.data.Frame;

public class Application {
	
	public static void main(String[] args) throws DocumentException {
		String configPath = args.length >= 1 ? args[0] : "config.xml";
		Config config = Analyser.analyze(configPath);
		List<Frame> frames = Generator.generate(config);
		Player player = new Player(frames);
		player.play();
	}

}
