package book.chapter05;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.Watcher.Event.KeeperState;

/**
 * ZooKeeper API 获取子节点列表，使用异步(ASync)接口。
 * @author <a href="mailto:nileader@gmail.com">银时</a>
 */
public class ZooKeeper_GetChildren_API_ASync_Usage_Deadlock implements Watcher {

    private CountDownLatch connectedSemaphore = new CountDownLatch(1);
    private static CountDownLatch _semaphore = new CountDownLatch(1);
    private ZooKeeper zk;

    ZooKeeper createSession(String connectString, int sessionTimeout, Watcher watcher) throws IOException {
        ZooKeeper zookeeper = new ZooKeeper(connectString, sessionTimeout, watcher);
        try {
            connectedSemaphore.await();
        } catch (InterruptedException e) {
        }
        return zookeeper;
    }

    /** create path by sync */
    void createPath_sync(String path, String data, CreateMode createMode) throws IOException, KeeperException,
            InterruptedException {

        if (zk == null) {
            zk = this.createSession("domain1.book.zookeeper:2181", 5000, this);
        }
        zk.create(path, data.getBytes(), Ids.OPEN_ACL_UNSAFE, createMode);
    }

    /** Get children znodes of path and set watches */
    void getChildren(String path) throws KeeperException, InterruptedException, IOException {

        System.out.println("===Start to get children znodes.===");
        if (zk == null) {
            zk = this.createSession("domain1.book.zookeeper:2181", 5000, this);
        }

        final CountDownLatch _semaphore_get_children = new CountDownLatch(1);

        zk.getChildren(path, true, new AsyncCallback.Children2Callback() {
            @Override
            public void processResult(int rc, String path, Object ctx, List<String> children, Stat stat) {

                System.out.println("Get Children znode result: [response code: " + rc + ", param path: " + path
                        + ", ctx: " + ctx + ", children list: " + children + ", stat: " + stat);
                _semaphore_get_children.countDown();
            }
        }, null);
        _semaphore_get_children.await();
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        ZooKeeper_GetChildren_API_ASync_Usage_Deadlock sample = new ZooKeeper_GetChildren_API_ASync_Usage_Deadlock();
        String path = "/get_children_test";

        try {
            sample.createPath_sync(path, "", CreateMode.PERSISTENT);
            sample.createPath_sync(path + "/c1", "", CreateMode.PERSISTENT);
            // Get children and register watches.
            sample.getChildren(path);
            // Add a new child znode to test watches event notify.
            sample.createPath_sync(path + "/c2", "", CreateMode.PERSISTENT);

            _semaphore.await();
        } catch (KeeperException e) {
            System.err.println("error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Process when receive watched event
     */
    @Override
    public void process(WatchedEvent event) {
        System.out.println("Receive watched event：" + event);
        if (KeeperState.SyncConnected == event.getState()) {

            if (EventType.None == event.getType() && null == event.getPath()) {
                connectedSemaphore.countDown();
            } else if (event.getType() == EventType.NodeChildrenChanged) {
                // children list changed
                try {
                    this.getChildren(event.getPath());
                    _semaphore.countDown();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
