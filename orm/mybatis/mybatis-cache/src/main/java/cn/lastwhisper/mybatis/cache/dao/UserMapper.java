package cn.lastwhisper.mybatis.cache.dao;


import cn.lastwhisper.mybatis.cache.domain.User;

/**
 * @author Administrator
 */
public interface UserMapper {

    /**
     * 根据 id 查询
     * @param userId
     * @return
     */
    User findByUserId(Integer userId);

    int updateUser(User user);
}
