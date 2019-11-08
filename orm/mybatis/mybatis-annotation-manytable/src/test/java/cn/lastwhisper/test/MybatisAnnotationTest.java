package cn.lastwhisper.test;

import cn.lastwhisper.mybatis.annotation.manytable.dao.AccountMapper;
import cn.lastwhisper.mybatis.annotation.manytable.dao.UserMapper;
import cn.lastwhisper.mybatis.annotation.manytable.domain.Account;
import cn.lastwhisper.mybatis.annotation.manytable.domain.User;
import cn.lastwhisper.mybatis.annotation.manytable.utils.MybatisUtil;
import org.junit.Test;

import java.util.List;

/**
 * mybatis注解实现一对一（多对一）、一对多、多对多、延迟加载、一级缓存、二级缓存
 */
public class MybatisAnnotationTest {

    /**
     * 一对多延迟加载
     */
    @Test
    public void testLazyOneToMany() {
        MybatisUtil.template(sqlSession -> {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<User> users = userMapper.findAll();
            //for (User user : users) {
            //    System.out.println(user);
            //    System.out.println(user.getAccounts());
            //}
        });
    }

    /**
     * 多对一延迟加载
     */
    @Test
    public void testLazyManyToOne() {
        MybatisUtil.template(sqlSession -> {
            AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
            List<Account> accounts = accountMapper.findAll();
            // for(Account account : accounts) {
            // System.out.println(account);
            // System.out.println(account.getUser());
            // }
        });
    }
}