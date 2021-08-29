package cn.cunchang.mapper;

import cn.cunchang.model.User;

/**
 * 用户数据层
 *
 * @author cunchang
 * @date 2020/5/31
 */
public interface UserMapper {

    /**
     * 根据用户 id 查询单个用户
     *
     * @param userId 用户 id
     * @return  用户
     */
    User selectById(Long id);

    /**
     * 保存用户
     *
     * @param user 用户
     * @return int 更新行数量
     */
    int insert(User user);

    /**
     * 更新用户
     *
     * @param user 用户
     * @return int 更新行数量
     */
    int updateById(User user);

    /**
     * 根据用户 id 删除用户
     *
     * @param userId 用户 id
     * @return int 更新行数量
     */
    int deleteById(Long id);

}
