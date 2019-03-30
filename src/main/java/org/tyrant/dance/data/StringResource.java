package org.tyrant.dance.data;

public class StringResource extends Frame implements Resource {

	private long timeOffset;
	
	public StringResource(ColorChar[][] datas) {
		super(-1, -1, datas);
	}
	
	@Override
	public void setStartTimeOffset(long timeOffset) {
		this.timeOffset = timeOffset;
	}

	@Override
	public long getStartTimeOffset() {
		return this.timeOffset;
	}
	
}
