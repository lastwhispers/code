package cn.lastwhisper.test;

import cn.lastwhisper.mybatis.datasource.dao.UserMapper;
import cn.lastwhisper.mybatis.datasource.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;

/**
 * 增删改查
 */
public class MybatisCRUDTest {
    private InputStream in;
    private SqlSession session;
    private UserMapper userMapper;


    /**
     * mybatis数据源
     *  PooledDataSource中找到popConnection()方法
     */
    @Test
    public void testSql() throws Exception {
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        SqlSession sqlSession = factory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.findUserById(42);
        System.out.println(user);

        // 释放资源
        sqlSession.close();
        in.close();
    }


}