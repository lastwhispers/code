package cn.lastwhisper.jvm.tmp.classload;

/**
 * 作用：clinit方法只会被一个线程执行，然后缓存到元空间，其他线程读的都是缓存
 */
public class DeadThreadTest {

    public static void main(String[] args) {

        Runnable r = () -> {
            System.out.println(Thread.currentThread().getName() + "开始");
            // Java类初始化的时机详解 https://www.jianshu.com/p/3afa5d24bf71

            // 此时会触发 linking阶段的initial
            DeadThread deadThread = new DeadThread();

            System.out.println(Thread.currentThread().getName() + "结束");
        };

        Thread t1 = new Thread(r, "线程1");
        Thread t2 = new Thread(r, "线程2");

        t1.start();
        t2.start();
    }
}

class DeadThread {

    // clinit
    static{
        System.out.println(Thread.currentThread().getName() + "初始化当前类");
    }

    // init
    public DeadThread() {

    }
}