package cn.lastwhisper.concurrent.juc.executor.trace;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TraceThreadPoolExecutor extends ThreadPoolExecutor {
	public TraceThreadPoolExecutor(int corePoolSize, int maximumPoolSize,
			long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}

	@Override
	public void execute(Runnable task) {
		super.execute(wrap(task, clientTrace(), Thread.currentThread()
				.getName()));
	}

	@Override
	public Future<?> submit(Runnable task) {
		return super.submit(wrap(task, clientTrace(), Thread.currentThread()
				.getName()));
	}
	
	private Exception clientTrace() {
		return new Exception("Client stack trace");
	}

	private Runnable wrap(final Runnable task, final Exception clientStack,
			String clientThreadName) {
		return new Runnable() {
			@Override
			public void run() {
				try {
					task.run();
				} catch (Exception e) {
					clientStack.printStackTrace();
					throw e;
				}
			}
		};
	}
}
