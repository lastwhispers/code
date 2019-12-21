package cn.lastwhisper.springbootaop.mapper;

import cn.lastwhisper.springbootaop.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 *
 * @author lastwhisper
 * @date 2019/12/5
 */
public interface UserMapper extends BaseMapper<User> {

    @Select("SELECT * FROM `user` WHERE username = #{username}")
    User findUserByName(@Param("username") String username);

}
