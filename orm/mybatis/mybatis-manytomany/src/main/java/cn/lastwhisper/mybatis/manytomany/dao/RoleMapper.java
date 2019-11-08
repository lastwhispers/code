package cn.lastwhisper.mybatis.manytomany.dao;


import cn.lastwhisper.mybatis.manytomany.domain.Role;

import java.util.List;

/**
 * @author Administrator
 */
public interface RoleMapper {
    /**
     * 查询所有角色
     * @return
     */
    List<Role> findAll();
}
