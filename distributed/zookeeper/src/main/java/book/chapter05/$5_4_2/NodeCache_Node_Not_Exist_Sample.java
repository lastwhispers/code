package book.chapter05.$5_4_2;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

public class NodeCache_Node_Not_Exist_Sample {

    static String path = "/curator_nodecache_sample";
    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("domain1.book.zookeeper:2181")
            .sessionTimeoutMs(5000)
            .retryPolicy(new ExponentialBackoffRetry(1000, 3))
            .build();
	
	public static void main(String[] args) throws Exception {
		client.start();
	    final NodeCache cache = new NodeCache(client,path,false);
		cache.start(true);
		cache.getListenable().addListener(new NodeCacheListener() {
			@Override
			public void nodeChanged() throws Exception {
				System.out.println("Node data update, new data: " + 
			    new String(cache.getCurrentData().getData()));
			}
		});
		client.create()
	      .creatingParentsIfNeeded()
	      .withMode(CreateMode.EPHEMERAL)
	      .forPath(path, "init".getBytes());
		Thread.sleep( Integer.MAX_VALUE );
	}
}