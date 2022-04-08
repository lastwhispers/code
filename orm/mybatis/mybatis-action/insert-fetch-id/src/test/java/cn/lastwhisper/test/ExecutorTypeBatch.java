package cn.lastwhisper.test;

import cn.cunchang.dao.UserMapper;
import cn.cunchang.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author cunchang
 * @date 2021/11/25 11:12 下午
 */
public class ExecutorTypeBatch {

    private InputStream in;
    SqlSessionFactory sqlSessionFactory;


    @Test
    public void testInsertBatch1(List<User> userList) {
        // 创建 SqlSession 对象
        SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH,false);
        // 创建 Dao 的代理对象
        UserMapper userMapper = session.getMapper(UserMapper.class);

        for (User user : userList) {
            userMapper.insert(user);
        }

        session.commit();
        //7.释放资源
        session.close();
    }


    @Before//在测试方法执行之前执行
    public void init() throws Exception {
        //1.读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建构建者对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //3.创建 SqlSession 工厂对象
        SqlSessionFactory factory = builder.build(in);

    }

    @After//在测试方法执行完成之后执行
    public void destroy() throws Exception {
        in.close();
    }

}
