package cn.lastwhisper.test;

import cn.lastwhisper.mybatis.annotation.crud.dao.UserMapper;
import cn.lastwhisper.mybatis.annotation.crud.domain.User;
import cn.lastwhisper.mybatis.annotation.crud.utils.MybatisUtil;
import org.junit.Test;

import java.util.Date;

/**
 * mybatis注解增删改查
 */
public class MybatisAnnotationTest {

    /**
     * 测试查询所有
     */
    @Test
    public void testFindAll() {
        MybatisUtil.template(sqlSession -> {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            userMapper.findAll().forEach(System.out::println);
        });
    }

    /**
     * 测试注解是否默认开启一级缓存
     * 测试查询一个
     */
    @Test
    public void testFindById() {
        MybatisUtil.template(sqlSession -> {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user1 = userMapper.findById(41);
            User user2 = userMapper.findById(41);
            System.out.println(user1);
            System.out.println(user1 == user2);
        });
    }

    /**
     * 测试保存
     */
    @Test
    public void testSave() {
        MybatisUtil.template(sqlSession -> {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            User user = new User();
            user.setUserName("mybatis annotation");
            user.setUserSex("男");
            user.setUserAddress("北京市顺义区");
            user.setUserBirthday(new Date());

            int res = userMapper.saveUser(user);
            System.out.println("影响数据库记录的行数： " + res);
            System.out.println("插入的主键值： " + user.getUserId());
        });
    }

    /**
     * 测试更新
     */
    @Test
    public void testUpdate() {
        MybatisUtil.template(sqlSession -> {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = userMapper.findById(45);
            user.setUserBirthday(new Date());
            user.setUserSex("女");
            int res = userMapper.updateUser(user);
            System.out.println(res);
        });
    }

    /**
     * 测试删除
     */
    @Test
    public void testDelete() {
        MybatisUtil.template(sqlSession -> {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            int res = userMapper.deleteUser(55);
            System.out.println(res);
        });

    }

    /**
     * 测试查询使用聚合函数
     */
    @Test
    public void testFindTotal() {
        MybatisUtil.template(sqlSession -> {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            int res = userMapper.findTotal();
            System.out.println(res);
        });
    }

    /**
     * 测试模糊查询
     */
    @Test
    public void testFindByName() {
        MybatisUtil.template(sqlSession -> {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            userMapper.findByName("%m%").forEach(System.out::println);
        });
    }

}