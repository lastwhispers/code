package cn.lastwhisper.test;

import cn.cunchang.dao.UserMapper;
import cn.cunchang.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 增删改查
 */
public class InsertFetchIdTest {
    private InputStream in;
    private SqlSession session;
    private UserMapper userMapper;

    @Test
    public void testInsert1() {
        User user = new User();
        user.setUsername("tomcat");
        user.setAddress("北京市顺义区");
        user.setSex("男");
        user.setBirthday(new Date());

        System.out.println("保存操作之后：" + user);
    }


    @Before//在测试方法执行之前执行
    public void init() throws Exception {
        //1.读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建构建者对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //3.创建 SqlSession 工厂对象
        SqlSessionFactory factory = builder.build(in);
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