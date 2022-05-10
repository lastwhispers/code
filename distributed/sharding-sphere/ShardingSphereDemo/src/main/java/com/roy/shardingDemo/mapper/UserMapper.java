package com.roy.shardingDemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.roy.shardingDemo.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author ：楼兰
 * @date ：Created in 2021/1/5
 * @description:
 **/
public interface UserMapper extends BaseMapper<User> {

    @Select("select u.user_id,u.username,d.uvalue ustatus from user u left join t_dict d on u.ustatus = d.ustatus")
    public List<User> queryUserStatus();
}
