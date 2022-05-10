package cn.lastwhisper.concurrent.juc.executor.trace;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class TraceMain {

	public static void main(String[] args) {
		ThreadPoolExecutor pools=new TraceThreadPoolExecutor(0, Integer.MAX_VALUE,
                0L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
		for(int i=0;i<5;i++){
			pools.execute(new DivTask(100,i));
		}
	}

}
