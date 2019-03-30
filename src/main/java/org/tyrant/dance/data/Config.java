package org.tyrant.dance.data;

import java.util.ArrayList;
import java.util.List;

public class Config {
	private Integer width;
	private Integer height;
	private List<Resource> resources;
	
	public Config() {
		this.resources = new ArrayList<>();
	}
	
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public List<Resource> getResources() {
		return resources;
	}
	public void addResource(Resource resource) {
		this.resources.add(resource);
	}
	
}
