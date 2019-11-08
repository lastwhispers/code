package cn.lastwhisper.mybatisbasic.dao;

import cn.lastwhisper.mybatisbasic.domain.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Administrator
 */
public interface UserMapper {
    /**
     * 基于XML配置
     * 查询所有用户
     */
    List<User> findAll();
    /**
     * 基于注解配置
     * 查询所有用户
     */
    @Select("select * from user")
    List<User> findUsers();
}
