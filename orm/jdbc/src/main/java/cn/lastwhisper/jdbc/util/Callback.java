package cn.lastwhisper.jdbc.util;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author lastwhisper
 */
@FunctionalInterface
public interface Callback {
    /**
     * 模板方法的回调方法
     */
    void execute(PreparedStatement pstm) throws SQLException;
}