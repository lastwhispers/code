package book;

import java.util.ResourceBundle;

/**
 *
 * @author lastwhisper
 * @date 12/26/2019
 */
public class PropertiesUtils {
    private static String connectString;

    static {
        ResourceBundle bundle = ResourceBundle.getBundle("config");
        connectString = bundle.getString("zookeeper.connectString");
    }

    public static String getConnectString() {
        return connectString;
    }

    public static void main(String[] args) {
        System.out.println(getConnectString());
    }
}
