//package book.chapter05;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.I0Itec.zkclient.ZkClient;
//import org.apache.zookeeper.WatchedEvent;
//import org.apache.zookeeper.Watcher;
//import org.apache.zookeeper.ZooDefs.Ids;
//import org.apache.zookeeper.data.ACL;
//
///**
// * Description: ZooKeepre ACL权限控制 测试
// *
// * @author nileader / nileader@gmail.com
// * @Date Feb 2, 2012
// */
//public class DemoAuth2 implements Watcher {
//
//    final static String SERVER_LIST = "127.0.0.1:2182";
//
//    final static String PATH = "/yinshi_auth_test";
//    final static String authentication_type = "digest";
//
//    final static String correctAuthentication = "taokeeper:true";
//
//    static ZkClient zkClient = null;
//
//    public static void main(String[] args) throws Exception {
//
//        List<ACL> acls = new ArrayList<ACL>(1);
//        for (ACL ids_acl : Ids.CREATOR_ALL_ACL) {
//            acls.add(ids_acl);
//        }
//
//        try {
//            //zkClient = new ZkClient(SERVER_LIST, 50000);
//            //zkClient.addAuthInfo(authentication_type, correctAuthentication.getBytes());
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//        try {
//            //zkClient.createPersistent(PATH, acls, "init content");
//            //System.out.println("使用授权key：" + correctAuthentication + "创建节点：" + PATH + ", 初始内容是: init content");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        // 获取数据
//        getDataByNoAuthentication();
//        getDataByBadAuthentication();
//        getDataByCorrectAuthentication();
//
//        zkClient.close();
//    }
//
//    /** 获取数据：采用错误的密码 */
//    static void getDataByBadAuthentication() {
//        String prefix = "[使用错误的授权信息]";
//        try {
//            System.out.println(prefix + "获取数据：" + PATH);
//            zkClient = new ZkClient(SERVER_LIST, 50000);
//            zkClient.addAuthInfo(authentication_type, "error".getBytes());
//            System.out.println(prefix + "成功获取数据：" + zkClient.readData(PATH));
//        } catch (Exception e) {
//            System.err.println(prefix + "获取数据失败，原因：" + e.getMessage());
//        }
//    }
//
//    /** 获取数据：不采用密码 */
//    static void getDataByNoAuthentication() {
//        String prefix = "[不使用任何授权信息]";
//        try {
//            System.out.println(prefix + "获取数据：" + PATH);
//            zkClient = new ZkClient(SERVER_LIST, 50000);
//            System.out.println(prefix + "成功获取数据：" + zkClient.readData(PATH));
//        } catch (Exception e) {
//            System.err.println(prefix + "获取数据失败，原因：" + e.getMessage());
//        }
//    }
//
//    /** 采用正确的密码 */
//    static void getDataByCorrectAuthentication() {
//        String prefix = "[使用正确的授权信息]";
//        try {
//            System.out.println(prefix + "获取数据：" + PATH);
//            zkClient = new ZkClient(SERVER_LIST, 50000);
//            zkClient.addAuthInfo(authentication_type, correctAuthentication.getBytes());
//            System.out.println(prefix + "成功获取数据：" + zkClient.readData(PATH));
//        } catch (Exception e) {
//            System.out.println(prefix + "获取数据失败，原因：" + e.getMessage());
//        }
//    }
//
//    @Override
//    public void process(WatchedEvent event) {
//        // TODO Auto-generated method stub
//
//    }
//
//}
