package cn.lastwhisper.jvm.gc.reference;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal导致内存泄漏
 * 弱引用只是解决了key的内存问题
 * @author lastwhisper
 */
public class ThreadLocalMemoryLeak {
    private final static int TASK_LOOP_SIZE = 100;
    /*线程池*/
    final static ThreadPoolExecutor poolExecutor = new
            ThreadPoolExecutor(5,5,1, TimeUnit.SECONDS,new LinkedBlockingQueue<>());

    static class LocalVariable{
        private byte[] a = new byte[1024*1024*5];//5MB
    }

    ThreadLocal<LocalVariable> localVariableThreadLocal;

    public static void main(String[] args){

        for (int i = 0; i < TASK_LOOP_SIZE; i++) {
            poolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(500);
                        // 第一次，仅使用局部变量 20MB
                        //LocalVariable localVariable = new LocalVariable();
                        // 第二次 放入ThreadLocal 350MB
                        // ThreadLocal弱引用导致内存泄漏
                        ThreadLocalMemoryLeak oom = new ThreadLocalMemoryLeak();
                        oom.localVariableThreadLocal = new ThreadLocal<>();
                        oom.localVariableThreadLocal.set(new LocalVariable());
                        // 第三次 remove解决内存泄漏 20MB
                        oom.localVariableThreadLocal.remove();

                        System.out.println("use local variable");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }

}
