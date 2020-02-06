package cn.lastwhisper.jdbc.test;

import cn.lastwhisper.jdbc.util.JdbcUtil;
import org.junit.Test;

import java.util.Random;

/**
 *
 * @author lastwhisper
 * @date 2020/2/5
 */
public class JdbcTest {

    // 插入一条，执行一条
    @Test
    public void TestInsert() {
        // 执行sql=INSERT INTO batchtable(account,name,area,title,motto) values(?,?,?,?,?) ||用时=12771
        JdbcUtil.executeUpdate(pstm -> {
            for (int i = 1; i <= 1000; i++) {
                pstm.setString(1, generateRandomString(10));
                pstm.setString(2, generateRandomString(20));
                pstm.setString(3, generateRandomString(20));
                pstm.setString(4, generateRandomString(20));
                pstm.setString(5, generateRandomString(50));
                pstm.executeUpdate();
            }
        }, "INSERT INTO batchtable(account,name,area,title,motto) values(?,?,?,?,?)");
    }

    // 批量插入
    @Test
    public void TestBatchInsert() {
        // 执行sql=INSERT INTO batchtable(account,name,area,title,motto) values(?,?,?,?,?) ||用时=12210
        JdbcUtil.executeBatchUpdate(pstm -> {
            for (int i = 1; i <= 1000; i++) {
                pstm.setString(1, generateRandomString(10));
                pstm.setString(2, generateRandomString(20));
                pstm.setString(3, generateRandomString(20));
                pstm.setString(4, generateRandomString(20));
                pstm.setString(5, generateRandomString(50));
                pstm.addBatch();
                // 每1000条记录插入一次
                //if (i % 1000 == 0){
                //    pstm.executeBatch();
                //    conn.commit();
                //    pstm.clearBatch();
                //}
            }
            pstm.executeBatch();
            //conn.commit();
            pstm.clearBatch();
        }, "INSERT INTO batchtable(account,name,area,title,motto) values(?,?,?,?,?)");
    }


    //生成指定length的随机字符串（A-Z，a-z，0-9）
    public static String generateRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(str.length());
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(generateRandomString(10).length() == 10);
        System.out.println(generateRandomString(20).length() == 20);
        System.out.println(generateRandomString(50).length() == 50);
    }
}
