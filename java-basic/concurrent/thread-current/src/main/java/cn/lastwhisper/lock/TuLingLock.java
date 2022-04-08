package cn.lastwhisper.lock;

import cn.lastwhisper.util.UnsafeInstance;
import sun.misc.Unsafe;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.LockSupport;

/**
 * @author ：
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description : 公平锁
 */
public class TuLingLock {
    /**
     * 当前加锁状态,记录加锁的次数
     */
    private volatile int state = 0;

    /**
     * 当前持有锁的线程
     */
    private Thread lockHolder;

    private ConcurrentLinkedQueue<Thread> queue = new ConcurrentLinkedQueue<>();

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Thread getLockHolder() {
        return lockHolder;
    }

    public void setLockHolder(Thread lockHolder) {
        this.lockHolder = lockHolder;
    }



    private boolean tryAquire(){
        Thread t = Thread.currentThread();
        int state = getState();
        if(state==0){
            if((queue.size()==0 || t == queue.peek()) && compareAndSwapState(0,1)){
                setLockHolder(t);
                return true;
            }
        }
        return false;
    }

    /**
     * 加锁
     */
    public void lock(){
        //1、获取锁-CAS-compareAndSwap
        if(tryAquire()){
            return;
        }
        Thread current = Thread.currentThread();
        queue.add(current);
        //2、停留在当前方法
        for(;;){
            if(queue.peek()==current && tryAquire()){
                System.out.println("hold lock Thread-name:"+current.getName());
                queue.poll();
                return ;
            }
            //阻塞线程
            LockSupport.park(current);
        }
        //3、锁被释放后，再次获取锁
    }

    /**
     * 解锁
     */
    public void unlock(){
        Thread current = Thread.currentThread();
        if(current != lockHolder){
            throw new RuntimeException("你不是持有锁的线程，不可以释放锁");
        }
        int state = getState();
        if(compareAndSwapState(state,0)){
            System.out.println(String.format("Thread-name:%s,释放锁成功",current.getName()));
            setLockHolder(null);
            Thread head = queue.peek();
            if(head != null){
                LockSupport.unpark(head);//线程2被唤醒
            }
        }
    }

    /**
     * 原子操作
     * @param oldValue
     *        oldvalue:线程工作内存当中的值
     * @param
     *        newValue:要替换的新值
     * @return
     */
    public final boolean compareAndSwapState(int oldValue,int newValue){
        return unsafe.compareAndSwapInt(this,stateOffset,oldValue,newValue);
    }

    private static final Unsafe unsafe = UnsafeInstance.reflectGetUnsafe();

    private static final long stateOffset;

    static {
        try {
            stateOffset = unsafe.objectFieldOffset(TuLingLock.class.getDeclaredField("state"));
        } catch (Exception e) {
            throw new Error();
        }
    }

}
