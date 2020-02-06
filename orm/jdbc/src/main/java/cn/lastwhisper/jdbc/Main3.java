package cn.lastwhisper.jdbc;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
 
public class Main3 {
 
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pst = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://********:3306/****?rewriteBatchedStatements=true", "****", "****");
			
			String sql = "insert into employees (user_id, age, first_name, second_name, date) values(?,?,?,?,?)";
			
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
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
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