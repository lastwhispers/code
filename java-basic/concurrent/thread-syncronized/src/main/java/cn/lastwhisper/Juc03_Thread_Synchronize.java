package cn.lastwhisper;

/**
 *                  ,;,,;
 *                ,;;'(    社
 *      __      ,;;' ' \   会
 *   /'  '\'~~'~' \ /'\.)  主
 * ,;(      )    /  |.     义
 *,;' \    /-.,,(   ) \    码
 *     ) /       ) / )|    农
 *     ||        ||  \)     
 *     (_\       (_\
 * 
 * @date ：Created in 2020/4/28 17:15
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description: 
 **/
public class Juc03_Thread_Synchronize {

    private final static Object object = new Object();

    public static void reentrantlock(){
        String tname = Thread.currentThread().getName();
        synchronized (object) {
            System.out.println(String.format("{}:) hold {}->monitor lock",tname,object));
            synchronized (object){
                System.out.println(String.format("{}:) re-hold {}->monitor lock",tname,object));
            }
        }
    }

    public static void main(String[] args)
    {
        Juc03_Thread_Synchronize.reentrantlock();
    }
}
