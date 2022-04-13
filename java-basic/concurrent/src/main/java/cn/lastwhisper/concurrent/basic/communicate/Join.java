package cn.lastwhisper.concurrent.basic.communicate;

public class Join {
    public static void main(String[] args){
        System.out.println("MainThread run start.");

        //启动一个子线程
        Thread lock = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("threadA run start.");
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("threadA run finished.");
            }
        });
        lock.start();

        System.out.println("MainThread join before");
        try {
            // main持有lock线程的锁，lock.wait() 阻塞main线程
            // lock线程执行完了，会调用lock.notify_all(thread)，唤醒main线程
            lock.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("MainThread run finished.");
    }
}