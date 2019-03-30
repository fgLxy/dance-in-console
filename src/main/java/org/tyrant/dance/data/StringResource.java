package org.tyrant.dance.data;

public class StringResource extends Frame implements Resource {

	private long timeOffset;
	
	private Frame frame;
	
	public StringResource(Config config, ColorChar[][] datas) {
		super(-1, -1, datas);
		this.setWidth(config.getWidth());
		this.setHeight(config.getHeight());
		this.frame = this;
	}
	
	@Override
	public void setStartTimeOffset(long timeOffset) {
		this.setTimeOffset(timeOffset);
		this.timeOffset = timeOffset;
	}

	@Override
	public long getStartTimeOffset() {
		return this.timeOffset;
	}

	@Override
	public Frame nextFrame() {
		if (frame == null) {
			return null;
		}
		Frame nextFrame = frame;
		frame = null;
		return nextFrame;
	}
	
}
