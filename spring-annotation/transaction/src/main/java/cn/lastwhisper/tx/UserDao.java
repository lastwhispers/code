package cn.lastwhisper.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 *
 * @author lastwhisper
 * @date 2020/2/17
 */
@Repository
public class UserDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void insert() {
        String sql = "INSERT INTO tbl_user(username,age) VALUES(?,?)";
        String username = UUID.randomUUID().toString().substring(0, 5);
        Integer age = 19;
        jdbcTemplate.update(sql, username, age);
    }


}
