package org.tyrant.dance;

import java.util.Comparator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.tyrant.dance.data.Frame;
import org.tyrant.dance.modal.ConsoleRenderModal;

/**
 * 负责播放帧序列
 * @author xiaoyangliu
 *
 */
public class Player {
	
	private Queue<Frame> queue;
	private long startTimestamp;
	Runnable player = new Runnable() {
		@Override
		public void run() {
			Frame curFrame = queue.peek();
			long curTimestamp = System.currentTimeMillis();
			if (curTimestamp - startTimestamp < curFrame.getTimeOffset()) {
				return;
			}
			queue.poll();
			ConsoleRenderModal.render(curFrame);
			if (queue.isEmpty()) {
				service.shutdown();
			}
		}
	};
	ScheduledExecutorService service;
	public Player(List<Frame> frames) {
		this.queue = new PriorityBlockingQueue<>(1000, new Comparator<Frame>() {

			@Override
			public int compare(Frame o1, Frame o2) {
				return Long.compare(o1.getTimeOffset(), o2.getTimeOffset());
			}

		});
		this.queue.addAll(frames);
		this.service = Executors.newScheduledThreadPool(1);
	}
	
	public void play() {
		this.startTimestamp = System.currentTimeMillis();
		this.service.scheduleAtFixedRate(player, 0, 60, TimeUnit.MILLISECONDS);
	}
	
}
