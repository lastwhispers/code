package cn.lastwhisper.test;


import cn.lastwhisper.mybatis.onetoone.dao.AccountMapper;
import cn.lastwhisper.mybatis.onetoone.domain.Account;
import cn.lastwhisper.mybatis.onetoone.domain.AccountUser;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class AccountTest {
    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private AccountMapper accountMapper;

    @Test
    public void testFindAll2() {
        //6.执行操作
        List<Account> accounts = accountMapper.findAll2();
        for(Account au : accounts) {
            System.out.println(au);
            System.out.println(au.getUser());
        }
    }

    // 注释掉Account中的User成员变量
    @Test
    public void testFindAll() {
        //6.执行操作
        List<AccountUser> accountUsers = accountMapper.findAll();
        for (AccountUser au : accountUsers) {
            System.out.println(au);
        }
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
        accountMapper = session.getMapper(AccountMapper.class);
    }

    @After//在测试方法执行完成之后执行
    public void destroy() throws Exception {
        session.commit();
        //7.释放资源
        session.close();
        in.close();
    }
}