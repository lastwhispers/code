package cn.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * spi-interface: 是针对厂商定义的接口项目，只提供接口，不提供实现
 * <p>
 * 服务接口 (Service Interface)，这是提供者实现的
 * Connection口就是其服务接口
 *
 * @author lastwhisper
 */
public interface Driver {

    public boolean available(String url,
                                    String user, String password);


    public Connection getConnection() throws SQLException;

}
