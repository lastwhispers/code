package book.chapter05.$5_4_2;
import java.io.File;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.test.TestingServer;

public class TestingServer_Sample {
	static String path = "/zookeeper";
	public static void main(String[] args) throws Exception {
		TestingServer server = new TestingServer(2181,new File("/home/admin/zk-book-data"));
		
		CuratorFramework client = CuratorFrameworkFactory.builder()
	            .connectString(server.getConnectString())
	            .sessionTimeoutMs(5000)
	            .retryPolicy(new ExponentialBackoffRetry(1000, 3))
	            .build();
        client.start();
        System.out.println( client.getChildren().forPath( path ));
        server.close();
	}
}