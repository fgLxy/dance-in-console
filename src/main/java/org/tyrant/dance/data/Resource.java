package org.tyrant.dance.data;

public interface Resource {
	/**
	 * 设置资源的起始时间偏移量，单位毫秒
	 * @param timeOffset
	 */
	void setStartTimeOffset(long timeOffset);
	
	long getStartTimeOffset();
}
