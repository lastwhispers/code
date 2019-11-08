package cn.lastwhisper.test;

import cn.lastwhisper.mybatis.cache.dao.UserMapper;
import cn.lastwhisper.mybatis.cache.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

/**
 * 一级缓存与二级缓存
 */
public class MybatisCacheTest {

    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private UserMapper userMapper;

    /**
     * 测试二级缓存
     */
    @Test
    public void testCache2() {
        SqlSession sqlSession1 = factory.openSession();
        UserMapper dao1 = sqlSession1.getMapper(UserMapper.class);
        User user1 = dao1.findByUserId(41);
        System.out.println(user1);
        sqlSession1.close();//一级缓存消失

        SqlSession sqlSession2 = factory.openSession();
        UserMapper dao2 = sqlSession2.getMapper(UserMapper.class);
        User user2 = dao2.findByUserId(41);
        System.out.println(user2);
        sqlSession2.close();
        //false，由于二级缓存是使用序列化的方式来保存对象，所以对象地址不同

        System.out.println(user1 == user2);
    }

    /**
     * 测试一级缓存的清除时机
     *   sqlSession 去执行 commit 操作（执行插入、更新、删除），清空 SqlSession 中的一级缓存，这样
     *   做的目的为了让缓存中存储的是最新的信息，避免脏读
     */
    @Test
    public void testClearCache1() {
        //1.根据 id 查询用户
        User user1 = userMapper.findByUserId(41);
        System.out.println(user1);
        //2.更新用户信息
        user1.setUsername("update user clear cache");
        user1.setAddress("北京市海淀区");
        userMapper.updateUser(user1);//清空缓存
        //session.commit();//清空缓存
        //session.clearCache();//清空缓存
        //3.再次查询 id 为 41 的用户
        User user2 = userMapper.findByUserId(41);
        System.out.println(user2);
        System.out.println(user1 == user2);//false
    }

    /**
     * 测试一级缓存
     */
    @Test
    public void testCache1() {
        User user = userMapper.findByUserId(41);
        System.out.println("第一次查询的用户： " + user);

        User user2 = userMapper.findByUserId(41);
        System.out.println("第二次查询用户： " + user2);
        System.out.println(user == user2);//true
    }

    @Before//在测试方法执行之前执行
    public void init() throws Exception {
        //1.读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建构建者对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //3.创建 SqlSession 工厂对象
        factory = builder.build(in);
        //4.创建 SqlSession 对象
        session = factory.openSession();
        //5.创建 Dao 的代理对象
        userMapper = session.getMapper(UserMapper.class);
    }

    @After//在测试方法执行完成之后执行
    public void destroy() throws Exception {
        session.commit();
        //7.释放资源
        session.close();
        in.close();
    }

}