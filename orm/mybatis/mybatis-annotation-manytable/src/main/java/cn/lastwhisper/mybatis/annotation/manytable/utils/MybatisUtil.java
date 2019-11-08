package cn.lastwhisper.mybatis.annotation.manytable.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * mybatis工具类
 * @author lastwhisper
 * @date 2019/10/18
 */
public class MybatisUtil {
    private static SqlSessionFactory factory;

    public static void template(Callback callback) {
        SqlSession session = null;
        //1.读取配置文件
        try (InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");) {
            //2.创建构建者对象
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            //3.创建 SqlSession 工厂对象
            factory = builder.build(in);
            //4.创建 SqlSession 对象
            session = factory.openSession();
            //5.执行操作
            callback.execute(session);
            //6.提交或回滚事务
            session.commit();
        } catch (IOException e) {
            if (session != null) {
                session.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

}
