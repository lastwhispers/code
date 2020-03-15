package cn.lastwhisper.test;

import cn.lastwhisper.mybatis.interceptor.dao.UserMapper;
import cn.lastwhisper.mybatis.interceptor.datascope.DataScope;
import cn.lastwhisper.mybatis.interceptor.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 高级查询
 * @author lastwhisper
 * @date 2019/10/17
 */
public class MybatisSelectTest {
    private InputStream in;
    private SqlSession session;
    private UserMapper userMapper;

    @Test
    public void testDataPermission() {
        DataScope dataScope = new DataScope(getCurrentUserDept());
        dataScope.setScopeName("deptid");
        // deptId是指你想查询用户所在的部门，0表示所有
        // 但是你自己能看的用户所在的部门范围在DataScope里面
        List<Map<String, Object>> maps = userMapper.selectUsers(dataScope);

        System.out.println(Optional.ofNullable(maps).orElse(new ArrayList<>()).size());
    }


    public List<Integer> getCurrentUserDept() {
        // 获取当前用户所属的部门
        List<Integer> list = new ArrayList<>();
        list.add(24);
        return list;
    }

    @Test
    public void testFindUser() {
        //5.执行查询一个方法
        List<User> user = userMapper.findUser();
        user.forEach(System.out::println);
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
