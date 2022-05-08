package cn.mysql;


import cn.jdbc.Driver;

import java.sql.Connection;

/**
 * MysqlDriver/OracleDriver: 分别是两个厂商对interface的不同实现，所以他们会依赖于interface项目
 * @author lastwhisper
 */
public class MysqlDriver implements Driver {

    static {
        System.out.println("mysql driver 注册成功");
    }


    @Override
    public Connection getConnection() {
        System.out.println("获取mysql connection");
        return null;
    }


}
