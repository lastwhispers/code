
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

/**
 * ZooKeeper API 获取节点数据内容，使用异步(async)接口。
 * 
 * @author <a href="mailto:nileader@gmail.com">银时</a>
 */
public class MasterSelect implements Watcher {

    private CountDownLatch connectedSemaphore = new CountDownLatch(1);
    private static CountDownLatch _semaphore = new CountDownLatch(2);
    private ZooKeeper zk;

    ZooKeeper createSession(String connectString, int sessionTimeout, Watcher watcher) throws IOException {
        ZooKeeper zookeeper = new ZooKeeper(connectString, sessionTimeout, watcher);
        try {
            connectedSemaphore.await();
        } catch (InterruptedException e) {
        }
        return zookeeper;
    }

    /**
     * 分布式抢锁实现原理：
     * 每个客户端同事来调用这个方法，其中只会有一个能成功，那么他就是抢到锁的人了。<br>
     * 其他没有抢到锁的人也不要灰心，马上进行watcher注册，一旦这个人死了，其他人马上再一次进行强锁。<br>
     * “这个人死了”？ ————这个的意思就是他死了，对应的节点就会消失
     * 
     */
    void getLock(String path, String data, CreateMode createMode) throws IOException, KeeperException,
            InterruptedException {
        if (zk == null) {
            zk = this.createSession("domain1.book.zookeeper:2181", 2000, this);
        }
        zk.create(path, data.getBytes(), Ids.OPEN_ACL_UNSAFE, createMode);
    }

    /** 开始盯着这个节点 */
    void exists(String path) throws KeeperException, InterruptedException, IOException {

        if (zk == null) {
            zk = this.createSession("domain1.book.zookeeper:2181", 5000, this);
        }
        zk.exists(path, true);
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        MasterSelect sample = new MasterSelect();
        String path = "/task";

        try {
            try {
                sample.getLock(path + "/worker", "我自己的ip", CreateMode.EPHEMERAL);
            } catch (Exception e) {
                // 没抢到锁的人，这里都会抛出一个异常：节点已经存在，无法重复创建。
                // 那么就要开始盯着这个节点，盼望他早点死，大家又有机会进行抢锁了。
                sample.exists(path);
            }

            _semaphore.await();
        } catch (KeeperException e) {
        }
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println("Receive watched event：" + event);
        if (KeeperState.SyncConnected == event.getState()) {

            if (EventType.None == event.getType() && null == event.getPath()) {
                connectedSemaphore.countDown();
            } else if (event.getType() == EventType.NodeDeleted) {
                // 看到希望了，重新抢锁开始啦！
                // TODO
            }
        }
    }
}