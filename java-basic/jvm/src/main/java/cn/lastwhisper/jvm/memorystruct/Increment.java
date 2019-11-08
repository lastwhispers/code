package cn.lastwhisper.jvm.memorystruct;

/**
 * 栈帧中i++引发的问题
 * @author lastwhisper
 * @date 2019/10/25
 */
public class Increment {
   public int increment() {
        int i = 0;
        i = i++;
        return i;
    }
}
