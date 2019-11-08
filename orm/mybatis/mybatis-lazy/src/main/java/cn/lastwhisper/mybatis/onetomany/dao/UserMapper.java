package cn.lastwhisper.mybatis.onetomany.dao;


import cn.lastwhisper.mybatis.onetomany.domain.Account;
import cn.lastwhisper.mybatis.onetomany.domain.User;

import java.util.List;

/**
 * @author Administrator
 */
public interface UserMapper {
    /**
     * 查询所有用户，同时获取出每个用户下的所有账户信息
     * @return
     */
    List<User> findAll();

    /**
     * 根据用户 id 查询账户信息
     * @param uid
     * @return
     */
    List<Account> findByUid(Integer uid);
}
