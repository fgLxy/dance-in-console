package org.tyrant.dance.generator;

import java.util.List;

import org.tyrant.dance.data.Config;
import org.tyrant.dance.data.Frame;
import org.tyrant.dance.data.Resource;

public interface ResourceGenerator {
	List<Frame> generate(Config config, Resource resource);
}
