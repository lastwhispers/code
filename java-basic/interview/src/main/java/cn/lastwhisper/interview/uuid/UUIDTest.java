package cn.lastwhisper.interview.uuid;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 *
 * @author lastwhisper
 * @date 2019/11/23
 */
public class UUIDTest {
    private static Set<String> set = new HashSet<String>();

    public static void main(String[] args) throws InterruptedException {
        long busyTime = 7;
        int i = 0;
        while (true) {
            long nowSys = System.currentTimeMillis();
            while ((System.currentTimeMillis() - nowSys) <= busyTime) {
                i++;
                String uuid = UUID.randomUUID().toString();
                System.out.println("log info 第 " + i + " 次生成uuid：" + uuid);
                if (set.contains(uuid)) {
                    System.err.println("log error 第 " + i + " 次生成uuid：" + uuid + " 发生重复");
                    break;
                } else {
                    set.add(uuid);
                }
            }
            Thread.sleep(1);
        }

    }

}
