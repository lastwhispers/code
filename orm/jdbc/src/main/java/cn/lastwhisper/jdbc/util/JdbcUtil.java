package cn.lastwhisper.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * https://blog.csdn.net/zhangw1236/article/details/54583192
 * @author lastwhisper
 * @date 2020/2/5
 */
public class JdbcUtil {

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            String user = "root";
            //String password = "root";
            String password = "4710054177.gg";
            //String url = "jdbc:mysql://localhost:3306/mysqlstudy?characterEnconding=UTF-8";
            String url = "jdbc:mysql://47.100.54.177:3637/mysqlstudy?characterEnconding=UTF-8";
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Connection getBatchConnection() {
        try {
            String user = "root";
            //String password = "root";
            String password = "4710054177.gg";

            //String url = "jdbc:mysql://localhost:3306/mysqlstudy?characterEnconding=UTF-8&rewriteBatchedStatements=true";
            String url = "jdbc:mysql://47.100.54.177:3637/mysqlstudy?characterEnconding=UTF-8&rewriteBatchedStatements=true";
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 增删改模板
     *
     */
    public static void executeUpdate(Callback callback, String sql) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        try {
            assert conn != null;
            pstm = conn.prepareStatement(sql);
            Long startTime = System.currentTimeMillis();

            callback.execute(pstm);

            Long endTime = System.currentTimeMillis();
            System.out.println("执行sql=" + sql + " ||用时=" + (endTime - startTime));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn);
            close(pstm);
        }

    }

    public static void executeBatchUpdate(Callback callback, String sql) {
        Connection conn = getBatchConnection();
        PreparedStatement pstm = null;
        try {
            assert conn != null;
            conn.setAutoCommit(false);
            pstm = conn.prepareStatement(sql);
            Long startTime = System.currentTimeMillis();

            callback.execute(pstm);

            conn.commit();
            Long endTime = System.currentTimeMillis();
            System.out.println("执行sql=" + sql + " ||用时=" + (endTime - startTime));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn);
            close(pstm);
        }

    }

    private static void close(PreparedStatement pstm) {
        if (pstm != null) {
            try {
                pstm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    private static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
