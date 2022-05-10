package cn.lastwhisper.concurrent.juc.future;

import java.util.concurrent.Callable;

/**
 * @author cunchang
 * @date 2022/4/22 5:15 PM
 */
public class RealData implements Callable<String> {

    private String para;

    public RealData(String para) {
        this.para = para;
    }

    @Override
    public String call() throws Exception {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            sb.append(para);
            Thread.sleep(100);
        }
        return sb.toString();
    }

}
