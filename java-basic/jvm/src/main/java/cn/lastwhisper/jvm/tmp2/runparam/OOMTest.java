package cn.lastwhisper.jvm.tmp2.runparam;

import java.util.ArrayList;
import java.util.Random;

/**
 * -XX:+PrintFlagsFinal -Xms600m -Xmx600m
 *  -XX:SurvivorRatio=8
 * 默认情况下，新生代占 1/3 ： 200m，老年代占2/3 : 400m
 *   其中，Eden默认占新生代的8/10 : 160m ,Survivor0，Survivor1各占新生代的1/10 ： 20m
 *
 * @author shkstart  shkstart@126.com
 * @create 2020  21:12
 */
public class OOMTest {
    public static void main(String[] args) {
        ArrayList<Picture> list = new ArrayList<>();
        while(true){
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list.add(new Picture(new Random().nextInt(100 * 50)));
        }
    }
}

class Picture{
    private byte[] pixels;

    public Picture(int length) {
        this.pixels = new byte[length];
    }

    public byte[] getPixels() {
        return pixels;
    }

    public void setPixels(byte[] pixels) {
        this.pixels = pixels;
    }
}
