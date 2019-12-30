package book.chapter06.pubsub;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

// ZooKeeper API 获取节点数据内容，使用同步(sync)接口。
public class GetConfig implements Watcher {

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    private static ZooKeeper zk = null;
    private static Stat stat = new Stat();

    public static void main(String[] args) throws Exception {

        String path1 = "/configserver";
        String path2 = "/app1";
        String path3 = "/database_config";
        zk = new ZooKeeper("domain1.book.zookeeper:2181",
                5000,
                new GetConfig());
        connectedSemaphore.await();

        System.out.println(new String(zk.getData(path1 + path2 + path3, true, stat)));

        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    public void process(WatchedEvent event) {
        if (KeeperState.SyncConnected == event.getState()) {
            if (EventType.None == event.getType() && null == event.getPath()) {
                connectedSemaphore.countDown();
            } else if (event.getType() == EventType.NodeDataChanged) {
                try {
                    System.out.println(new String("获取最新配置数据：" + new String(zk.getData(event.getPath(), true, stat))));
                } catch (Exception e) {
                }
            }
        }
    }
}