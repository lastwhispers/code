package cn.lastwhisper.concurrent.juc.lockfree;

/**
 * 模拟的CAS算法
 * @author lastwhisper
 */
public class CASDemo {
    public static void main(String[] args) {
        final CompareAndSwap CAS = new CompareAndSwap();
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int expectedValue = CAS.get();
                    boolean flag = CAS.compareAndSet(expectedValue, (int) (Math.random() * 100));
                    System.out.println(flag);
                }
            }).start();
        }
    }
}

class CompareAndSwap {
    private int value;

    //获取当前内存的值
    public synchronized int get() {
        return value;
    }

    //比较当前内存的值与预期的值是否相同，相同则设置内存值为更新的值
    // 并返回当前内存值
    public synchronized int compareAndSwap(int expectedValue, int newValue) {
        int oldValue = value;
        if (oldValue == expectedValue) {
            this.value = newValue;
        }
        return oldValue;
    }

    //交换是否成功
    public synchronized boolean compareAndSet(int expectedValue, int newValue) {
        return expectedValue == compareAndSwap(expectedValue, newValue);
    }
}
