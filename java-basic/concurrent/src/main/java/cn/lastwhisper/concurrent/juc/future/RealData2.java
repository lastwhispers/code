package cn.lastwhisper.concurrent.juc.future;

/**
 * @author cunchang
 * @date 2022/4/22 5:15 PM
 */
public class RealData2 implements Runnable {

    private String para;

    public RealData2(String para) {
        this.para = para;
    }

    @Override
    public void run() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            sb.append(para);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
