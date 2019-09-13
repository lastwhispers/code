package cn.lastwhisper.offer.Interview_2;

/** 
 * 懒汉式之枚举
 */
public enum Singleton5 {
    INSTANCE;

    public Singleton5 getInstance() {
        return INSTANCE;
    }
}
