package cn.lastwhisper.redis.bloomfilter;

import io.rebloom.client.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author lastwhisper
 */
public class BloomTest3 {
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
        BloomTest3 bloomer = new BloomTest3();
        List<String> users = bloomer.randomUsers(100000);
        List<String> usersTrain = users.subList(0, users.size() / 2);
        List<String> usersTest = users.subList(users.size() / 2, users.size());
        Client client = new Client("192.168.108.129", 6379);
        client.delete("codehole");
        // 对应 bf.reserve 指令
        client.createFilter("codehole", 50000, 0.001);
        for (String user : usersTrain) {
            client.add("codehole", user);
        }
        int falses = 0;
        for (String user : usersTest) {
            boolean ret = client.exists("codehole", user);
            if (ret) {
                falses++;
            }
        }
        System.out.printf("%d %d\n", falses, usersTest.size());
    }
}
