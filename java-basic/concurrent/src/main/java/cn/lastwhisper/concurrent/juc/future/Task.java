package cn.lastwhisper.concurrent.juc.future;

/**
 * @author cunchang
 * @date 2022/4/22 5:15 PM
 */
public class Task implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 12; i++) {
            try {
                Thread.sleep(100);
                System.out.println("有12个任务,当前任务" + i + "执行中,已耗时" + i + "00ms,");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
