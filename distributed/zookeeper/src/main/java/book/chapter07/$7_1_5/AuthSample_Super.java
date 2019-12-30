package book.chapter07.$7_1_5;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
//使用Super权限模式进行权限控制
public class AuthSample_Super {

    final static String PATH = "/zk-book";
    public static void main(String[] args) throws Exception {

        ZooKeeper zookeeper1 = new ZooKeeper("domain1.book.zookeeper:2181",5000,null);
        zookeeper1.addAuthInfo("digest", "foo:true".getBytes());
        zookeeper1.create( PATH, "init".getBytes(), Ids.CREATOR_ALL_ACL, CreateMode.EPHEMERAL );
        
        ZooKeeper zookeeper2 = new ZooKeeper("domain1.book.zookeeper:2181",50000,null);
        zookeeper2.addAuthInfo("digest", "foo:zk-book".getBytes());
        System.out.println(zookeeper2.getData( PATH, false, null ));
        
        ZooKeeper zookeeper3 = new ZooKeeper("domain1.book.zookeeper:2181",50000,null);
        zookeeper3.addAuthInfo("digest", "foo:false".getBytes());
        System.out.println(zookeeper3.getData( PATH, false, null ));
    }
}