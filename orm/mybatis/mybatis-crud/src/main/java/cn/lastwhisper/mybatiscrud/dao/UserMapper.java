package cn.lastwhisper.mybatiscrud.dao;

import cn.lastwhisper.mybatiscrud.domain.QueryVo;
import cn.lastwhisper.mybatiscrud.domain.User;

import java.util.List;

/**
 * @author Administrator
 */
public interface UserMapper {

    /**
     * 根据 id 查询
     * @param userId
     * @return
     */
    User findById(Integer userId);

    /**
     * 保存用户
     * @param user
     * @return 影响数据库记录的行数
     */
    int saveUser(User user);

    /**
     * 更新用户
     * @param user
     * @return 影响数据库记录的行数
     */
    int updateUser(User user);

    /**
     * 根据 id 删除用户
     * @param userId
     * @return
     */
    int deleteUser(Integer userId);

    /*=======================测试${}与#{}===============================*/
    /**
     * 根据名称模糊查询
     * @param username
     * @return
     */
    List<User> findLikeByName(String username);

    /**
     * 根据名称模糊查询
     * @param username
     * @return
     */
    List<User> findLikeByName2(String username);

    /**
     * 查询总记录条数
     * @return
     */
    int findTotal();
    /*=======================测试parameterType===============================*/

    /**
     * 根据 QueryVo 中的条件查询用户
     * @param vo
     * @return
     */
    List<User> findUserByVo(QueryVo vo);

    /*=======================测试resultType===============================*/
    /**
     * 查询所有用户
     * @return
     */
    List<User> findAll();

    List<User> findAll2();

}
