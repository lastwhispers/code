package cn.lastwhisper.test;

import cn.lastwhisper.mybatiscrud.dao.UserMapper;
import cn.lastwhisper.mybatiscrud.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
/**
 * 增删改查
 */
public class MybatisCRUDTest {
    private InputStream in;
    private SqlSession session;
    private UserMapper userMapper;


    // 查询
    @Test
    public void testFindOne() {
        //6.执行操作
        User user1 = userMapper.findById(41);
        User user2 = userMapper.findById(41);
        System.out.println(user1);
        System.out.println(user1==user2);
    }

    // 新增
    @Test
    public void testSave() {
        User user = new User();
        user.setUserName("modify User property");
        user.setUserAddress("北京市顺义区");
        user.setUserSex("男");
        user.setUserBirthday(new Date());
        System.out.println("保存操作之前： " + user);
        //5.执行保存方法
        userMapper.saveUser(user);
        System.out.println("保存操作之后： " + user);//出现id值
    }

    @Test
    public void testUpdateUser()throws Exception{
        // 根据 id 查询
        User user = userMapper.findById(52);
        //6.执行操作
        user.setUserAddress("北京市顺义区");
        int res = userMapper.updateUser(user);
        System.out.println(res);
    }

    @Test
    public void testDeleteUser() throws Exception {
        //6.执行操作
        int res = userMapper.deleteUser(52);
        System.out.println(res);
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