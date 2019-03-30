package org.tyrant.dance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.tyrant.dance.data.Config;
import org.tyrant.dance.data.Frame;
import org.tyrant.dance.data.ImageResource;
import org.tyrant.dance.data.Resource;
import org.tyrant.dance.data.StringResource;
import org.tyrant.dance.data.VideoResource;
import org.tyrant.dance.generator.ImageGenerator;
import org.tyrant.dance.generator.ResourceGenerator;
import org.tyrant.dance.generator.StringGenerator;
import org.tyrant.dance.generator.VideoGenerator;

/**
 * 负责根据配置实例生成帧序列
 * @author xiaoyangliu
 *
 */
public class Generator {
	
	private static Map<Class<? extends Resource>, ResourceGenerator> GENERATOR_MAP = new HashMap<>();
	static {
		//注册不同资源类型的帧序列生成器
		GENERATOR_MAP.put(VideoResource.class, new VideoGenerator());
		GENERATOR_MAP.put(ImageResource.class, new ImageGenerator());
		GENERATOR_MAP.put(StringResource.class, new StringGenerator());
	}
	
	public static List<Frame> generate(Config config) {
		List<Frame> frames = new ArrayList<>();
		for (Resource resource : config.getResources()) {
			ResourceGenerator generator = GENERATOR_MAP.get(resource.getClass());
			if (generator == null) {
				throw new RuntimeException("unknow resource type." + resource.getClass());
			}
			frames.addAll(generator.generate(config, resource));
		}
		return frames;
	}

}
