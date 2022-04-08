package cn.lastwhisper.tmodel;

/**
 * 
 * @date ：Created in 2020/8/2
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description:
 **/
public class ThreadModel {

    public static void main(String[] args) {

        for (int i=0;i<200;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while(true){
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
    }

}
