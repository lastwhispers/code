package book.chapter05.$5_4_2;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache.StartMode;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class PathChildrenCache_Sample_POST_INITIALIZED_EVENT {

    static String path = "/zk-book";
    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("domain1.book.zookeeper:2181")
            .retryPolicy(new ExponentialBackoffRetry(1000, 3))
            .sessionTimeoutMs(5000)
            .build();
	public static void main(String[] args) throws Exception {
		client.start();
		PathChildrenCache cache = new PathChildrenCache(client, path, true);
		cache.start(StartMode.NORMAL);
		cache.getListenable().addListener(new PathChildrenCacheListener() {
			public void childEvent(CuratorFramework client, 
					               PathChildrenCacheEvent event) throws Exception {
				switch (event.getType()) {
				case INITIALIZED:
					System.out.println("INITIALIZED," + event.getData().getPath());
					break;
				default:
					break;
				}
			}
		});
		Thread.sleep(Integer.MAX_VALUE);
	}
}