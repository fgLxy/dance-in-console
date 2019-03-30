package org.tyrant.dance;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.fusesource.jansi.Ansi.Color;
import org.tyrant.dance.data.ColorChar;
import org.tyrant.dance.data.Config;
import org.tyrant.dance.data.ImageResource;
import org.tyrant.dance.data.Resource;
import org.tyrant.dance.data.StringResource;
import org.tyrant.dance.utils.ColorUtils;

/**
 * 负责解析配置文件
 * @author xiaoyangliu
 *
 */
public class Analyser {
	
	public static Config analyze(String path) throws DocumentException {
		SAXReader reader = new SAXReader();
		Config config = new Config();
		try {
			Document doc = reader.read(Paths.get(path).toFile());
			Element root = doc.getRootElement();
			config.setWidth(Integer.valueOf(getAttribute(root, "width")));
			config.setHeight(Integer.valueOf(getAttribute(root, "height")));
			Iterator<?> iter = root.elementIterator();
			while (iter.hasNext()) {
				Element elem = (Element) iter.next();
				String type = elem.getName();
				switch(type) {
				case "string": config.addResource(analyzeString(elem));break;
				case "image": config.addResource(analyzeImage(elem));break;
				case "video": config.addResource(analyzeVideo(elem));break;
				default: throw new RuntimeException("unknow resource type." + type);
				}
			}
		} catch (DocumentException e) {
			throw e;
		}
		return config;
	}

	private static String getAttribute(Element elem, String name) {
		Attribute attr = elem.attribute(name);
		if (attr == null) {
			throw new RuntimeException("element " + elem.getName() + " attribute " + name + " is require.");
		}
		return attr.getValue();
	}
	
	private static Resource analyzeVideo(Element elem) {
		String path = getAttribute(elem, "path");
		ImageResource resource = new ImageResource(path);
		long timeOffset = Long.valueOf(getAttribute(elem, "timeOffset"));
		resource.setStartTimeOffset(timeOffset);
		return resource;
	}

	private static Resource analyzeImage(Element elem) {
		String path = getAttribute(elem, "path");
		ImageResource resource = new ImageResource(path);
		long timeOffset = Long.valueOf(getAttribute(elem, "timeOffset"));
		resource.setStartTimeOffset(timeOffset);
		return resource;
	}

	private static Resource analyzeString(Element elem) {
		ColorChar[][] datas = new ColorChar[elem.elements().size()][];
		for (int lineIdx = 0; lineIdx < elem.elements().size(); lineIdx++) {
			Element line = (Element) elem.elements().get(lineIdx);
			List<ColorChar> chars = new ArrayList<>();
			for (int sIdx = 0; sIdx < line.elements().size(); sIdx++) {
				Element sequence = (Element) line.elements().get(sIdx);
				String str = sequence.getText().trim();
				Color color = ColorUtils.getColor(getAttribute(sequence, "color"));
				for (int cIdx = 0; cIdx < str.length(); cIdx++) {
					char c = str.charAt(cIdx);
					chars.add(new ColorChar(c, color));
				}
			}
			datas[lineIdx] = chars.toArray(new ColorChar[chars.size()]);
		}
		StringResource resource = new StringResource(datas);
		long timeOffset = Long.valueOf(getAttribute(elem, "timeOffset"));
		resource.setStartTimeOffset(timeOffset);
		return resource;
	}
}
