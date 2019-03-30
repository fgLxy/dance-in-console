package org.tyrant.dance.data;

import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Paths;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FrameGrabber.Exception;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.tyrant.dance.convert.ImageConvert;

public class VideoResource implements Resource {

	private String path;
	private Config config;
	private FFmpegFrameGrabber ff;
	
	private long timeOffset;
	
	public VideoResource(Config config, String path) {
		this.path = path;
		try {
			this.config = config;
			startFrameGrabber();
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void startFrameGrabber() throws Exception {
		File file = Paths.get(this.getPath()).toFile();
		this.ff = new FFmpegFrameGrabber(file);
        ff.start();
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
		if (ff == null) {
			return null;
		}
		org.bytedeco.javacv.Frame frame = null;
		while (ff.hasVideo()) {
            try {
				frame = ff.grabFrame();
			} catch (Exception e) {
				e.printStackTrace();
			}
            if (frame != null && frame.image != null) {
            	Java2DFrameConverter converter = new Java2DFrameConverter();
            	BufferedImage img = converter.getBufferedImage(frame);
            	Frame myFrame = ImageConvert.convert(img, config.getWidth(), config.getHeight());
            	myFrame.setTimeOffset(frame.timestamp/1000);
            	myFrame.setWidth(config.getWidth());
            	myFrame.setHeight(config.getHeight());
            	myFrame.setTimeOffset(myFrame.getTimeOffset() + this.getStartTimeOffset());
            	return myFrame;
            }
            if (frame == null) {
            	try {
					ff.stop();
				} catch (Exception e) {
					e.printStackTrace();
				}
            	ff = null;
            	return null;
            }
        }
		return null;
	}
}
