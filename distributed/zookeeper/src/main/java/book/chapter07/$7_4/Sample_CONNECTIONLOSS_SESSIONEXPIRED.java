package book.chapter07.$7_4;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooKeeper.States;

/**
 *
 * @author <a href="mailto:nileader@gmail.com">银时</a>
 * 
 */
public class Sample_CONNECTIONLOSS_SESSIONEXPIRED implements Watcher {

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {

        ZooKeeper zk = new ZooKeeper("domain1.book.zookeeper,domain2.book.zookeeper", 5000,
                new Sample_CONNECTIONLOSS_SESSIONEXPIRED());
        connectedSemaphore.await();

        while (true) {
            try {
                System.out.println("Get data: " + zk.getData("/$7_4", true, null));
            } catch (Exception e) {
                e.printStackTrace();
            }
            Thread.sleep(200);
        }
    }

    @Override
    public void process(WatchedEvent event) {

        if (event.getType() == EventType.None && event.getState() == KeeperState.SyncConnected) {
            connectedSemaphore.countDown();
        }

        System.out.println(event.getType().toString() + "-" + event.getState().toString());

    }

}
