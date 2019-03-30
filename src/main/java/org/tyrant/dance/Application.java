package org.tyrant.dance;

import org.dom4j.DocumentException;
import org.tyrant.dance.data.Config;

public class Application {
	
	public static void main(String[] args) throws DocumentException {
		String configPath = args.length >= 1 ? args[0] : "config.xml";
		Config config = Analyser.analyze(configPath);
		Player player = new Player(config.getResources());
		player.play();
	}

}
