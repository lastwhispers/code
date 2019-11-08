package cn.lastwhisper.mybatis.datasource.dao;


import cn.lastwhisper.mybatis.datasource.domain.User;

/**
 * @author Administrator
 */
public interface UserMapper {

    /**
     * 根据 id 查询
     * @param userId
     * @return
     */
    User findUserById(Integer userId);

}
