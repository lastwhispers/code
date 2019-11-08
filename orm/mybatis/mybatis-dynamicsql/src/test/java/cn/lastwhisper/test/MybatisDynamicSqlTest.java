package cn.lastwhisper.test;

import cn.lastwhisper.mybatis.dynamicsql.dao.UserMapper;
import cn.lastwhisper.mybatis.dynamicsql.domain.QueryVo;
import cn.lastwhisper.mybatis.dynamicsql.domain.User;
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

/**
 * 增删改查
 */
public class MybatisDynamicSqlTest {
    private InputStream in;
    private SqlSession session;
    private UserMapper userMapper;

    /**
     * 动态 SQL 之<if>标签、动态 SQL 之<where>标签、SQL 片段
     *  实体类的不同取值，使用不同的 SQL 语句来进行查询
     */
    @Test
    public void testFindByUser() {
        User u = new User();
        u.setUsername("%王%");
        u.setAddress("%顺义%");
        //6.执行操作
        List<User> users = userMapper.findByUser(u);
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 动态标签之<foreach>标签
     *
     */
    @Test
    public void testFindInIds() {
        QueryVo vo = new QueryVo();
        List<Integer> ids = new ArrayList<Integer>();
        ids.add(41);
        ids.add(42);
        ids.add(43);
        ids.add(46);
        ids.add(57);
        vo.setIds(ids);
        //6.执行操作
        List<User> users = userMapper.findInIds(vo);
        for (User user : users) {
            System.out.println(user);
        }
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