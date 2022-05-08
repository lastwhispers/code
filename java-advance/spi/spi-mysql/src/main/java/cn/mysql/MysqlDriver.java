package cn.mysql;


import cn.jdbc.Driver;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * MysqlDriver/OracleDriver: 分别是两个厂商对interface的不同实现，所以他们会依赖于interface项目
 *
 * @author lastwhisper
 */
public class MysqlDriver implements Driver {

    static {
        System.out.println("加载mysql driver");
    }

    @Override
    public boolean available(String url, String user, String password) {
        if (url != null && url.contains("mysql")) {
            System.out.println("成功注册mysql驱动");
            return true;
        }
        return false;
    }

    @Override
    public Connection getConnection() throws SQLException {
        // return new MysqlConnection();
        return null;
    }
}
