package cn.cunchang.bloomfilter;

import io.rebloom.client.Client;
import org.junit.Test;

import java.util.List;

/**
 * 布隆过滤器测试
 * @author cunchang
 */
public class BloomTest {

    /**
     * bloomfilter对于已经见过的元素不会误判
     */
    @Test
    public void test1(){
        Client client = new Client("192.168.108.129", 6379);
        for (int i = 0; i < 100000; i++) {
            client.add("codehole", "user" + i);
            boolean ret = client.exists("codehole", "user" +i);
            if (!ret) {
                System.out.println(i);
                break;
            }
        }
    }

    /**
     * bloomfilter对于没见过的元素会误判
     */
    @Test
    public void test2(){
        Client client = new Client("192.168.108.129", 6379);
        for (int i = 0; i < 100000; i++) {
            client.add("codehole", "user" + i);
            boolean ret = client.exists("codehole", "user" + (i + 1));
            if (ret) {
                System.out.println(i);
                break;
            }
        }

    }

}
