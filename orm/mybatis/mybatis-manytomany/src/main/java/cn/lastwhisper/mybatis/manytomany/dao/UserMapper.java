package cn.lastwhisper.mybatis.manytomany.dao;


import cn.lastwhisper.mybatis.manytomany.domain.User;

import java.util.List;

/**
 * @author Administrator
 */
public interface UserMapper {
    /**
     * 查询所有角色
     * @return
     */
    List<User> findAll();
}
