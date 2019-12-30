package book.chapter05.$5_4_1;

import org.I0Itec.zkclient.ZkClient;

//ZkClient删除节点数据
public class Del_Data_Sample {
	public static void main(String[] args) throws Exception {
		String path = "/zk-book";
    	ZkClient zkClient = new ZkClient("domain1.book.zookeeper:2181", 2000);
        zkClient.createPersistent(path, "");
        zkClient.createPersistent(path+"/c1", "");
        zkClient.deleteRecursive(path);
    }
}