package cn.lastwhisper.interview.Thread;

public class SyncBlockAndMethod {
    public void syncsTask() {
        synchronized (this) {
            System.out.println("Hello");
            //可重入
            synchronized (this){
                System.out.println("World");
            }
        }
    }

    public synchronized void syncTask() {
        System.out.println("Hello Again");
    }

}
