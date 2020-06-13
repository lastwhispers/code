package cn.lastwhisper.task.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 异步非阻塞式
 */
@Service
public class AsyncService {

    /**
     * @Async 告诉 Spring 这是一个异步方法
     *  默认使用 Spring 内的线程池
     *  threadName:task-1
     *
     * @Async("BeanName") 从 IOC 容器中指定线程池
     *  threadName:myThreadPool-1
     *
     */
    @Async("myThreadPool")
    public void hello() {
        String threadName = Thread.currentThread().getName();
        long threadId = Thread.currentThread().getId();
        System.out.println(getClass()+" threadName:" + threadName + " , threadId:" + threadId);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(getClass()+" threadName:" + threadName + " , threadId:" + threadId + " ,处理数据中...");
    }
}
