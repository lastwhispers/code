package cn.lastwhisper.test;

import cn.lastwhisper.mybatiscrud.dao.UserMapper;
import cn.lastwhisper.mybatiscrud.domain.QueryVo;
import cn.lastwhisper.mybatiscrud.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * 高级查询
 * @author lastwhisper
 * @date 2019/10/17
 */
public class MybatisSelectTest {
    private InputStream in;
    private SqlSession session;
    private UserMapper userMapper;

    /**
     * 模糊查询 #{}占位符号
     *  可以防止sql注入
     */
    @Test
    public void testFindByName() {
        //5.执行查询一个方法
        userMapper.findLikeByName("%王%").forEach(System.out::println);
    }

    /**
     * 模糊查询 ${}拼接 sql
     *  无法防止sql注入，且 parameterType 传输单个简单类型值，
     *  #{}括号中可以是 value 或其它名称。
     */
    @Test
    public void testFindByName2() {
        //5.执行查询一个方法
        userMapper.findLikeByName2("王").forEach(System.out::println);
    }

    /**
     * 测试聚合查询，查询总数
     */
    @Test
    public void testFindTotal() throws Exception {
        //6.执行操作
        int res = userMapper.findTotal();
        System.out.println(res);
    }

    /**
     * 测试parameterType为QueryVo
     */
    @Test
    public void testFindByQueryVo() {
        QueryVo vo = new QueryVo();
        User user = new User();
        user.setUsername("%王%");
        vo.setUser(user);
        List<User> users = userMapper.findUserByVo(vo);
        for(User u : users) {
            System.out.println(u);
        }
    }

    /**
     *  测试resultMap
     */
    @Test
    public void testFindAll() {
        userMapper.findAll().forEach(System.out::println);
        userMapper.findAll2().forEach(System.out::println);
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
