package book.chapter06.pubsub;

import com.alibaba.fastjson.JSON;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

// ZooKeeper API 更新节点数据内容，使用同步(sync)接口。
public class SetConfig implements Watcher {
    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    private static ZooKeeper zk;

    public static void main(String[] args) throws Exception {
        // /configserver/app1/database_config
        String path1 = "/configserver";
        String path2 = "/app1";
        String path3 = "/database_config";
        zk = new ZooKeeper("domain1.book.zookeeper:2181",
                5000,
                new SetConfig());
        connectedSemaphore.await();

        zk.create(path1, "".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        zk.create(path1 + path2, "".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        // 设置数据库配置
        zk.create(path1 + path2 + path3, JSON.toJSONString(DbcpUtils.loadOldDbcpConfig()).getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println("创建通用配置节点，path="+(path1 + path2 + path3)+"，并添加通用配置数据");
        // do something
        Thread.sleep(10000);

        System.out.println("修改通用配置节点数据，path="+(path1 + path2 + path3));
        // 设置最新的数据库配置
        Stat stat = zk.setData(path1 + path2 + path3, JSON.toJSONString(DbcpUtils.loadNowDbcpConfig()).getBytes(), -1);

        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    public void process(WatchedEvent event) {
        if (KeeperState.SyncConnected == event.getState()) {
            if (EventType.None == event.getType() && null == event.getPath()) {
                connectedSemaphore.countDown();
            }
        }
    }
}