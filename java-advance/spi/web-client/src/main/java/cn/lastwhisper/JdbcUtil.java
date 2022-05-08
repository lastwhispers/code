package cn.lastwhisper;


import cn.jdbc.DriverManager;

import java.sql.Connection;

/**
 * @author cunchang
 * @date 2022/5/8 7:17 PM
 */
public class JdbcUtil {

    public static void main(String[] args) {
        // 拿到jdbc链接搞事情
        Connection connection = DriverManager.getDriver().getConnection();
    }

}
