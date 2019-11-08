package cn.lastwhisper.concurrent.basic.threadlocal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ThreadLocal解决线程不安全问题
 * @author lastwhisper
 */
public class ThreadLocalDemo {
    //private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    static ThreadLocal<SimpleDateFormat> tl = new ThreadLocal<>();
    public static class ParseDate implements Runnable{
        private int i=0;
        public ParseDate(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            try {
                //Date t = sdf.parse("2015-03-28 19:15:" + i % 60);

                if(tl.get()==null){
                    tl.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
                }
                Date t = tl.get().parse("2015-03-28 19:15:" + i % 60);
                System.out.println(i+"\t"+t);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        ExecutorService pool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            pool.execute(new ParseDate(i));
        }

    }
}
