package cn.lastwhisper.interview.timestamp;

/**
 * 时间戳
 * @author lastwhisper
 * @date 2019/11/24
 */
public class TimeStampTest {
    /**
     * 时间戳的二进制为41位
     */
    public static void main(String[] args){
        long currentTimeMillis = System.currentTimeMillis();
        System.out.println(Long.toBinaryString(currentTimeMillis).length());
    }
}
