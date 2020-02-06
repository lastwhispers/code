package cn.lastwhisper.jdbc;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
 
public class Main4 {
 
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pst = null;
		// 执行sql=insert into employees (user_id, age, first_name, second_name, date) values(?,?,?,?,?) ||用时=8297
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://47.100.54.177:3637/mysqlstudy?rewriteBatchedStatements=true", "root", "4710054177.gg");

			long startTime = System.currentTimeMillis();

			String sql = "insert into employees (user_id, age, first_name, second_name, date) values(?,?,?,?,?)";
			
			conn.setAutoCommit(false);
			pst = conn.prepareStatement(sql);
			
			int loop = 0;
			for(loop = 0; loop < 1000; loop++) {
				pst.setInt(1, loop);
				pst.setInt(2, 18);
				pst.setString(3, "roger");
				pst.setString(4, "zhang");
				pst.setString(5, "2017-01-17");
				pst.addBatch();
			}
 
			pst.executeBatch();
			conn.commit();

			System.out.println("执行sql=" + sql + " ||用时=" + (System.currentTimeMillis() - startTime));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			if(pst != null) {
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}