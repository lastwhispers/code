package book.chapter05.$5_3_7;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

//使用无权限信息的ZooKeeper会话访问含权限信息的数据节点
public class AuthSample_Get {

    final static String PATH = "/zk-book-auth_test";

    public static void main(String[] args) throws Exception {

        ZooKeeper zookeeper1 = new ZooKeeper("domain1.book.zookeeper:2181", 5000, null);
        zookeeper1.addAuthInfo("digest", "foo:true".getBytes());
        zookeeper1.create(PATH, "init".getBytes(), Ids.CREATOR_ALL_ACL, CreateMode.EPHEMERAL);

        ZooKeeper zookeeper2 = new ZooKeeper("domain1.book.zookeeper:2181", 50000, null);
        zookeeper2.getData(PATH, false, null);
    }
}