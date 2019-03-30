package org.tyrant.dance.generator;

import java.util.ArrayList;
import java.util.List;

import org.tyrant.dance.data.Config;
import org.tyrant.dance.data.Frame;
import org.tyrant.dance.data.Resource;
import org.tyrant.dance.data.StringResource;

public class StringGenerator implements ResourceGenerator {

	@Override
	public List<Frame> generate(Config config, Resource resource) {
		StringResource strResource = (StringResource) resource;
		Frame frame = strResource;
		frame.setWidth(config.getWidth());
		frame.setHeight(config.getHeight());
		frame.setTimeOffset(strResource.getStartTimeOffset());
		List<Frame> frames = new ArrayList<>();
		frames.add(frame);
		return frames;
	}

}
