package cn.lastwhisper.mybatis.annotation.manytable.dao;


import cn.lastwhisper.mybatis.annotation.manytable.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * mybatis注解替换xml配置一对多
 * @author Administrator
 */
@CacheNamespace(blocking=true)//mybatis 基于注解方式实现配置二级缓存
public interface UserMapper {

    /**
     * 查询所有用户
     * @return
     */
    @Select("select * from user")
    //@Results(id = "userMap",
    //        value = {
    //                @Result(id = true, column = "id", property = "userId"),
    //                @Result(column = "username", property = "userName"),
    //                @Result(column = "sex", property = "userSex"),
    //                @Result(column = "address", property = "userAddress"),
    //                @Result(column = "birthday", property = "userBirthday")
    //        })
    @Results(id="userMap",
            value= {
                    @Result(id=true,column="id",property="userId"),
                    @Result(column="username",property="userName"),
                    @Result(column="sex",property="userSex"),
                    @Result(column="address",property="userAddress"),
                    @Result(column="birthday",property="userBirthday"),
                    @Result(column="id",property="accounts",
                            many=@Many(
                                    select="cn.lastwhisper.mybatis.annotation.manytable.dao.AccountMapper.findByUid",
                                    fetchType= FetchType.LAZY
                            )
                    )
            })
    List<User> findAll();

    /**
     * 根据 id 查询一个用户
     * @param userId
     * @return
     */
    @Select("select * from user where id = #{userId} ")
    @ResultMap("userMap")
    User findById(Integer userId);

}
