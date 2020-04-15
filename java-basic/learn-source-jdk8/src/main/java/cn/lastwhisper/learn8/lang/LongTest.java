package cn.lastwhisper.learn8.lang;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Long
 * @author lastwhisper
 * @date 2020/4/12
 */
@Slf4j
public class LongTest {

    /**
     * Long常量池
     */
    @Test
    public void testLongCache() {
        Long.valueOf(128);
        Long.valueOf(129);

        Long.parseLong("128");
        Long.valueOf("128");
    }


    @Test
    public void testParse() throws InterruptedException {
        log.info("----");
        Thread.sleep(1000L);
        String s = "100";
        int valueOf = 0;
        int parseLong = 0;
        for (int j = 0; j < 100; j++) {
            long beginTime = System.currentTimeMillis();
            for (int i = 0; i < 1000; i++) {
                Long.valueOf(s);
            }
            long valueOfTime = System.currentTimeMillis() - beginTime;
            long beginTime1 = System.currentTimeMillis();
            for (int i = 0; i < 1000; i++) {
                Long.parseLong(s);
            }
            long parseLongTime = System.currentTimeMillis() - beginTime1;

            if (valueOfTime > parseLongTime) {
                valueOf++;
            } else {
                parseLong++;
            }
        }
        System.out.println("valueOf 累计" + valueOf);
        System.out.println("parseLong 累计" + parseLong);

    }
}
