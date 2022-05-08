package cn.lastwhisper;


import cn.jdbc.Driver;
import cn.jdbc.DriverManager;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author cunchang
 * @date 2022/5/8 7:17 PM
 */
public class JdbcUtil {

    public static void main(String[] args) throws SQLException {
        String user = "root";
        String password = "12456";
        String url = "jdbc:mysql://47.100.54.177:3637/mysqlstudy?characterEnconding=UTF-8";

        // 拿到jdbc链接搞事情
        Driver driver = DriverManager.getDriver(url, user, password);
        System.out.println("客户端获取driver:" + driver);
        Connection connection = driver.getConnection();
    }

}
