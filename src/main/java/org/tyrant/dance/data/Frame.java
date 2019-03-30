package org.tyrant.dance.data;

public class Frame {
	//一屏共多少行
	private Integer height;
	//一行共多少字符
	private Integer width;
	//当前帧内容
	private ColorChar[][] datas;
	//当前帧的时间偏移量
	private long timeOffset;
	
	public Frame(Integer width, Integer height, ColorChar[][] datas) {
		this.height = height;
		this.width = width;
		this.datas = datas;
	}
	
	public Frame(Integer width, Integer height, ColorChar[][] datas, long timeOffset) {
		this.height = height;
		this.width = width;
		this.datas = datas;
		this.timeOffset = timeOffset;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public ColorChar[][] getDatas() {
		return datas;
	}

	public long getTimeOffset() {
		return timeOffset;
	}

	public void setTimeOffset(long timeOffset) {
		this.timeOffset = timeOffset;
	}
	
}
