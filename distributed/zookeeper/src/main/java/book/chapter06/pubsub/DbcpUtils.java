package book.chapter06.pubsub;

import java.util.ResourceBundle;

/**
 *
 * @author lastwhisper
 * @date 12/29/2019
 */
public class DbcpUtils {

    private static String driverClass;
    private static String url;
    private static String username;
    private static String password;
    private static String maxActive;
    private static String maxIdle;
    private static String maxWait;

    static {
        ResourceBundle bundle = ResourceBundle.getBundle("config");
        driverClass = bundle.getString("dbcp.driverClass");
        url = bundle.getString("dbcp.url");
        username = bundle.getString("dbcp.username");
        password = bundle.getString("dbcp.password");
        maxActive = bundle.getString("dbcp.maxActive");
        maxIdle = bundle.getString("dbcp.maxIdle");
        maxWait = bundle.getString("dbcp.maxWait");
    }

    // 获取配置文件未修改前的配置
    public static DbcpConfig loadOldDbcpConfig() {
        return new DbcpConfig(driverClass, url, "lastwhisper", "lastwhisper", maxActive, maxIdle, maxWait);
    }

    // 获取配置文件修改会后的配置
    public static DbcpConfig loadNowDbcpConfig() {
        return new DbcpConfig(driverClass, url, username, password, maxActive, maxIdle, maxWait);
    }


}
