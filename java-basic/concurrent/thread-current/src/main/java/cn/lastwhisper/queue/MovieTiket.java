package cn.lastwhisper.queue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: 图灵学院-杨过
 * QQ：692927914
 */
public class MovieTiket implements Delayed {
    //延迟时间
    private final long delay;
    //到期时间
    private final long expire;
    //数据
    private final String msg;
    //创建时间
    private final long now;

    public long getDelay() {
        return delay;
    }

    public long getExpire() {
        return expire;
    }

    public String getMsg() {
        return msg;
    }

    public long getNow() {
        return now;
    }

    /**
     * @param msg 消息
     * @param delay 延期时间
     */
    public MovieTiket(String msg , long delay) {
        this.delay = delay;
        this.msg = msg;
        expire = System.currentTimeMillis() + delay;    //到期时间 = 当前时间+延迟时间
        now = System.currentTimeMillis();
    }

    /**
     * @param msg
     */
    public MovieTiket(String msg){
        this(msg,1000);
    }

    public MovieTiket(){
        this(null,1000);
    }

    /**
     * 获得延迟时间   用过期时间-当前时间,时间单位毫秒
     * @param unit
     * @return
     */
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.expire
                - System.currentTimeMillis() , TimeUnit.MILLISECONDS);
    }

    /**
     * 用于延迟队列内部比较排序  当前时间的延迟时间 - 比较对象的延迟时间
     * 越早过期的时间在队列中越靠前
     * @param delayed
     * @return
     */
    public int compareTo(Delayed delayed) {
        return (int) (this.getDelay(TimeUnit.MILLISECONDS)
                - delayed.getDelay(TimeUnit.MILLISECONDS));
    }

    @Override
    public String toString() {
        return "MovieTiket{" +
                "delay=" + delay +
                ", expire=" + expire +
                ", msg='" + msg + '\'' +
                ", now=" + now +
                '}';
    }
}
