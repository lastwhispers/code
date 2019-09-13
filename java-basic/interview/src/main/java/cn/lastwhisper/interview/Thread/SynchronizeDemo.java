package cn.lastwhisper.interview.Thread;

/**
 * synchronize支持对象锁和字节码锁
 *
 * @author lastwhisper
 */
public class SynchronizeDemo {
    /**
     * @param args
     */
    public static void main(String[] args) {
        new SynchronizeDemo().init();
    }

    private void init(){
        final Outputer outputer = new Outputer();
        new Thread(new Runnable(){
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    outputer.output("zhangxiaoxiang");
                }

            }
        }).start();

        new Thread(new Runnable(){
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    outputer.output2("lihuoming");
                }

            }
        }).start();

    }

    static class Outputer{

        public void output(String name){
            int len = name.length();
            synchronized (Outputer.class)
            {
                for(int i=0;i<len;i++){
                    System.out.print(name.charAt(i));
                }
                System.out.println();
            }
        }

        public synchronized void output2(String name){
            int len = name.length();
            for(int i=0;i<len;i++){
                System.out.print(name.charAt(i));
            }
            System.out.println();
        }

        public static synchronized void output3(String name){
            int len = name.length();
            for(int i=0;i<len;i++){
                System.out.print(name.charAt(i));
            }
            System.out.println();
        }
    }
}
