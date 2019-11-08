package cn.lastwhisper.mybatis.dynamicsql.dao;


import cn.lastwhisper.mybatis.dynamicsql.domain.QueryVo;
import cn.lastwhisper.mybatis.dynamicsql.domain.User;

import java.util.List;

/**
 * @author Administrator
 */
public interface UserMapper {

    /**
     * 根据用户信息，查询用户列表
     * @param user
     * @return
     */
    List<User> findByUser(User user);

    /**
     * 根据 id 集合查询用户
     * @param vo
     * @return
     */
    List<User> findInIds(QueryVo vo);

}
