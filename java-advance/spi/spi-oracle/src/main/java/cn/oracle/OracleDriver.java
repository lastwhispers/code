package cn.oracle;


import cn.jdbc.Driver;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * spi-boy/spi-gril: 分别是两个厂商对interface的不同实现，所以他们会依赖于interface项目
 *
 * @author lastwhisper
 */
public class OracleDriver implements Driver {

    static {
        System.out.println("加载oracle driver");
    }

    @Override
    public boolean available(String url, String user, String password) {
        if (url != null && url.contains("oracle")) {
            System.out.println("成功注册oracle驱动");
            return true;
        }
        return false;
    }

    @Override
    public Connection getConnection() throws SQLException {
        // return new OracleConnection();
        return null;
    }
}
