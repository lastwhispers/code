package cn.lastwhisper.test;

import cn.lastwhisper.mybatis.onetomany.dao.UserMapper;
import cn.lastwhisper.mybatis.onetomany.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class UserTest {
    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private UserMapper userMapper;

    /**
     * 延迟加载返回的是代理对象
     */
    @Test
    public void testFindAll() {
        //6.执行操作
        List<User> users = userMapper.findAll();
        // 延迟加载不使用，不加载Account的查询语句
        //for (User user : users) {
        //    System.out.println("-------每个用户的内容---------");
        //    System.out.println(user);
        //    System.out.println(user.getAccounts());
        //}
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