import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooKeeper.States;

/**
 * Description: ZooKeepre ACL权限控制 测试
 * 
 * @author nileader / nileader@gmail.com
 * @Date Feb 2, 2012
 */
public class Session_Test implements Watcher{

    final static String SERVER_LIST = "localhost:2182";

    static ZooKeeper zookeeper = null;

    public static void main(String[] args) throws Exception {

        try {
            zookeeper = new ZooKeeper(SERVER_LIST, 20000, new Session_Test());
            while (zookeeper.getState() != States.CONNECTED) {
                Thread.sleep(3000);
                System.out.println("...");
            }

            System.out.println( "完成数据获取：" + zookeeper.setData("/test", "test".getBytes(), -1) );
            Thread.sleep(1000000000000l);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    static void priint_session(ZooKeeper zookeeper) {

        System.out.println("zookeeper: " + zookeeper);
        System.out.println("sessionId: " + zookeeper.getSessionId());
        System.out.println("pwd: " + print_byte(zookeeper.getSessionPasswd()));

    }

    static String print_byte(byte[] b) {
        StringBuilder sb = new StringBuilder("[");
        for (byte _b : b) {
            sb.append(_b).append(",");
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public void process(WatchedEvent arg0) {
        System.out.println( arg0.getState() );
        System.out.println(arg0.getType());
    }

}