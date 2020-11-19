package cn.cunchang.cluster;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 集群测试
 * @author cunchang
 */
public class ClusterTest {

    public static void main(String[] args) {
        Set<HostAndPort> nodeList = new HashSet<>();
        nodeList.add(new HostAndPort("192.168.108.131", 7000));
        nodeList.add(new HostAndPort("192.168.108.131", 7001));
        nodeList.add(new HostAndPort("192.168.108.131", 7002));
        nodeList.add(new HostAndPort("192.168.108.131", 7003));
        nodeList.add(new HostAndPort("192.168.108.131", 7004));
        nodeList.add(new HostAndPort("192.168.108.131", 7005));
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        int timeout = 30_000;
        JedisCluster jedisCluster = new JedisCluster(nodeList, timeout, poolConfig);
        jedisCluster.set("hello", "world");
        System.out.println(jedisCluster.get("hello"));

        //获取所有节点的JedisPool
        Map<String, JedisPool> jedisPoolMap = jedisCluster.getClusterNodes();
    }

}
