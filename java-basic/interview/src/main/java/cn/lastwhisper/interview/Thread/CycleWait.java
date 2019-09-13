package cn.lastwhisper.interview.Thread;

/**
 * 处理线程的返回值：主线程等待法
 * @author lastwhisper
 */
public   class CycleWait implements Runnable{
    private String rtv=null;
    @Override
    public void run() {
        //子线程执行中
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //拿到子线程计算的结果
        rtv=Thread.currentThread().getName()+" return value";
    }

    public static void main(String[] args) throws InterruptedException {
        CycleWait cycleWait = new CycleWait();
        Thread thread = new Thread(cycleWait);
        thread.start();
        // 1.主线程等待法
        // 等待子线程执行完成
        //while (cycleWait.rtv==null){
        //    //时间不好确定
        //    Thread.sleep(100);
        //}
        // 2.子线程join，主线程阻塞
        thread.join();
        System.out.println(""+cycleWait.rtv);
    }
}
