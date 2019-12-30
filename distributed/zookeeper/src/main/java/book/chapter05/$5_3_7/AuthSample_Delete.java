package book.chapter05.$5_3_7;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

//删除节点的权限控制
public class AuthSample_Delete {

    final static String PATH = "/zk-book-auth_test";
    final static String PATH2 = "/zk-book-auth_test/child";

    public static void main(String[] args) throws Exception {

        ZooKeeper zookeeper1 = new ZooKeeper("domain1.book.zookeeper:2181", 5000, null);
        zookeeper1.addAuthInfo("digest", "foo:true".getBytes());
        zookeeper1.create(PATH, "init".getBytes(), Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);
        zookeeper1.create(PATH2, "init".getBytes(), Ids.CREATOR_ALL_ACL, CreateMode.EPHEMERAL);

        try {
            ZooKeeper zookeeper2 = new ZooKeeper("domain1.book.zookeeper:2181", 50000, null);
            zookeeper2.delete(PATH2, -1);
        } catch (Exception e) {
            System.out.println("删除节点失败: " + e.getMessage());
        }

        /**
         * 删除chroot下的子节点需要权限
         */
        ZooKeeper zookeeper3 = new ZooKeeper("domain1.book.zookeeper:2181", 50000, null);
        zookeeper3.addAuthInfo("digest", "foo:true".getBytes());
        zookeeper3.delete(PATH2, -1);
        System.out.println("成功删除节点：" + PATH2);

        /**
         * 删除chroot节点不需要权限
         */
        ZooKeeper zookeeper4 = new ZooKeeper("domain1.book.zookeeper:2181", 50000, null);
        zookeeper4.delete(PATH, -1);
        System.out.println("成功删除节点：" + PATH);
    }
}