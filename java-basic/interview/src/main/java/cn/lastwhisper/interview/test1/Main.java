package cn.lastwhisper.interview.test1;

import java.util.*;

/**
 *
 * @author lastwhisper
 * @date 2019/12/16
 */
public class Main {
    private static List<Map<String, String>> listMap = new ArrayList<>();

    static {
        Map<String, String> user1 = new LinkedHashMap<>();
        user1.put("username", "tom");
        user1.put("age", "18");
        listMap.add(user1);
    }

    public static void main(String[] args) {
        for (Map<String, String> map : listMap) {
            if (map.containsKey("username")) {
                map.put("username", "netty");
            }
        }
        for (Map<String, String> map : listMap) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                System.out.println("k=" + entry.getKey() + " v=" + entry.getValue());
            }
        }
    }

}
