package org.tyrant.dance.data;

public class ImageResource implements Resource {
	
	private String path;
	
	private long timeOffset;
	
	public ImageResource(String path) {
		this.path = path;
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

}
