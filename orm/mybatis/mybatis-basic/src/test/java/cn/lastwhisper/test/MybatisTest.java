package cn.lastwhisper.test;

import cn.lastwhisper.mybatisbasic.dao.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class MybatisTest {
    /**
     * 测试mybatis环境
     */
    public static void main(String[] args) throws Exception {
        // 1、读取配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 2、创建SqlSessionFactory的建造者
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        // 3、使用建造者创建工厂对象SqlSessionFactory
        SqlSessionFactory factory = builder.build(in);
        // 4、使用SqlSessionFactory创建SqlSession对象
        SqlSession session = factory.openSession();
        // 5、使用SqlSession创建dao接口的代理对象
        UserMapper userMapper = session.getMapper(UserMapper.class);
        // 6、使用代理对象执行sql查询
        System.out.println("基于XML配置");
        userMapper.findAll().forEach(System.out::println);
        System.out.println("基于注解配置");
        userMapper.findUsers().forEach(System.out::println);
        // 7、释放资源
        session.close();
        in.close();
    }
}