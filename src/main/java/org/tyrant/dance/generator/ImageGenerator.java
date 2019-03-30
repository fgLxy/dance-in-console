package org.tyrant.dance.generator;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.tyrant.dance.convert.ImageConvert;
import org.tyrant.dance.data.Config;
import org.tyrant.dance.data.Frame;
import org.tyrant.dance.data.ImageResource;
import org.tyrant.dance.data.Resource;

public class ImageGenerator implements ResourceGenerator {

	@Override
	public List<Frame> generate(Config config, Resource resource) {
		ImageResource imgResource = (ImageResource) resource;
		try (InputStream input = Files.newInputStream(Paths.get(imgResource.getPath()))) {
			BufferedImage img = ImageIO.read(input);
			Frame frame = ImageConvert.convert(img, config.getWidth(), config.getHeight());
			frame.setTimeOffset(resource.getStartTimeOffset());
			List<Frame> frames = new ArrayList<>();
			frames.add(frame);
			return frames;
		} catch (IOException e) {
			System.out.println("获取图片失败.");
			e.printStackTrace();
			return null;
		}
	}


}
