package cn.lastwhisper.redis.bloomfilter;

import io.rebloom.client.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 测试bloomfilter的误判率
 * @author lastwhisper
 */
public class BloomTest2 {

    private String chars;

    {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            builder.append((char) ('a' + i));
        }
        chars = builder.toString();
    }

    private String randomString(int n) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int idx = ThreadLocalRandom.current().nextInt(chars.length());
            builder.append(chars.charAt(idx));
        }
        return builder.toString();
    }

    private List<String> randomUsers(int n) {
        List<String> users = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            users.add(randomString(64));
        }
        return users;
    }

    public static void main(String[] args) {
        BloomTest2 bloomer = new BloomTest2();
        List<String> users = bloomer.randomUsers(100000);
        List<String> usersTrain = users.subList(0, users.size() / 2);
        List<String> usersTest = users.subList(users.size() / 2, users.size());

        Client client = new Client("192.168.108.129", 6379);
        client.delete("codehold");
        for (String user : usersTrain) {
            client.add("codehold",user);
        }
        int falses = 0;
        for (String user : usersTest) {
            boolean ret = client.exists("codehold", user);
            if(ret){//true说明误判了
                falses++;
            }
        }
        System.out.printf("%d %d\n",falses,usersTest.size());
    }
}
