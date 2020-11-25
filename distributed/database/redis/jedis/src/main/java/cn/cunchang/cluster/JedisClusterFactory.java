package cn.cunchang.cluster;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JedisClusterFactory {
    private JedisCluster jedisCluster;
    private List<String> hostPortList;
    //超时时间
    private int timeout;

    public void init() {
        //这里可以设置相关参数
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

        //从配置文件中读取ip:port的参数放进Set中
        Set<HostAndPort> nodeSet = new HashSet<>();
        for (String hostPort : hostPortList) {
            String[] arr = hostPort.split(":");
            if (arr.length != 2) {
                continue;
            }
            nodeSet.add(new HostAndPort(arr[0], Integer.parseInt(arr[1])));
        }

        try {
            jedisCluster = new JedisCluster(nodeSet, timeout, jedisPoolConfig);
        } catch (Exception e) {
            // logger
            e.printStackTrace();
        }
    }


    public void destroy() {
        if (jedisCluster != null) {
            try {
                jedisCluster.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public JedisCluster getJedisCluster() {
        return jedisCluster;
    }

    //spring注入hostPortList和timeout
    public void setHostPortList(List<String> hostPortList) {
        this.hostPortList = hostPortList;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
}