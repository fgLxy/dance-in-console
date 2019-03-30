package org.tyrant.dance;

import java.util.Comparator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.tyrant.dance.data.Frame;
import org.tyrant.dance.data.Resource;
import org.tyrant.dance.model.ConsoleRenderModel;

/**
 * 负责播放帧序列
 * @author xiaoyangliu
 *
 */
public class Player {
	
	private Queue<Resource> queue;
	private long startTimestamp;
	Runnable player = new Runnable() {
		Resource curResource;
		@Override
		public void run() {
			Frame frame = null;
			if (curResource == null) {
				frame = switchNextResourceAndGetFrame();
			} else {
				frame = curResource.nextFrame();
				if (frame == null) {
					frame = switchNextResourceAndGetFrame();
				}
			}
			if (frame != null) {
				ConsoleRenderModel.render(frame);
			}
			else {
				if (queue.isEmpty()) {
					service.shutdown();
				}
			}
		}
		private Frame switchNextResourceAndGetFrame() {
			curResource = nextResource();
			if (curResource == null) {
				return null;
			}
			Frame frame = curResource.nextFrame();
			if (frame == null) {
				curResource = null;
				return null;
			}
			return frame;
		}
		private Resource nextResource() {
			if (queue.isEmpty()) {
				return null;
			}
			Resource resource = queue.peek();
			long curTimestamp = System.currentTimeMillis();
			if (curTimestamp - startTimestamp < resource.getStartTimeOffset()) {
				return null;
			}
			queue.poll();
			return resource;
		}
	};
	ScheduledExecutorService service;
	public Player(List<Resource> resources) {
		this.queue = new PriorityBlockingQueue<>(1000, new Comparator<Resource>() {
			@Override
			public int compare(Resource o1, Resource o2) {
				return Long.compare(o1.getStartTimeOffset(), o2.getStartTimeOffset());
			}

		});
		this.queue.addAll(resources);
		this.service = Executors.newScheduledThreadPool(1);
	}
	
	public void play() {
		this.startTimestamp = System.currentTimeMillis();
		this.service.scheduleAtFixedRate(player, 0, 60, TimeUnit.MILLISECONDS);
	}
	
}
