package org.tyrant.dance.data;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import org.tyrant.dance.convert.ImageConvert;

public class ImageResource implements Resource {
	
	private String path;
	
	private long timeOffset;
	
	private Frame frame;
	
	public ImageResource(Config config, String path) {
		this.path = path;
		ImageResource imgResource = (ImageResource) this;
		try (InputStream input = Files.newInputStream(Paths.get(imgResource.getPath()))) {
			BufferedImage img = ImageIO.read(input);
			frame = ImageConvert.convert(img, config.getWidth(), config.getHeight());
		} catch (IOException e) {
			System.out.println("获取图片失败.");
			e.printStackTrace();
		}
	}

	@Override
	public void setStartTimeOffset(long timeOffset) {
		this.timeOffset = timeOffset;
	}

	@Override
	public long getStartTimeOffset() {
		return this.timeOffset;
	}
	
	public String getPath() {
		return this.path;
	}

	@Override
	public Frame nextFrame() {
		if (frame == null) {
			return null;
		}
		Frame nextFrame = frame;
		frame = null;
		nextFrame.setTimeOffset(this.getStartTimeOffset());
		return nextFrame;
	}

}
