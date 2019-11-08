package cn.lastwhisper.mybatis.annotation.crud.dao;


import cn.lastwhisper.mybatis.annotation.crud.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * mybatis注解替换xml
 * @author Administrator
 */
public interface UserMapper {

    /**
     * 查询所有用户
     */
    @Select("select * from user")
    @Results(
            id = "userMap",
            value = { //故意修改字段名和列名不同
                    @Result(id = true, column = "id", property = "userId"),
                    @Result(id = true, column = "id", property = "userName"),
                    @Result(id = true, column = "id", property = "userBirthday"),
                    @Result(id = true, column = "id", property = "userSex"),
                    @Result(id = true, column = "id", property = "userAddress"),
            }
    )
    List<User> findAll();

    /**
     * 根据 id 查询一个用户
     */
    @Select("select * from user where id = #{uid} ")
    @ResultMap("userMap")
    User findById(Integer userId);

    /**
     * 保存操作
     */
    @Insert("insert into user(username,sex,birthday,address) values(#{userName},#{userSex},#{userBirthday},#{userAddress})")
    @SelectKey(keyColumn = "id", keyProperty = "userId", resultType = Integer.class, before =
            false, statement = {"select last_insert_id()"})
    int saveUser(User user);

    /**
     * 更新操作
     */
    @Update("update user set username=#{userName},address=#{userAddress},sex=#{userSex},birthday=#{userBirthday} where id=#{userId} ")
    int updateUser(User user);

    /**
     * 删除用户
     */
    @Delete("delete from user where id = #{userId} ")
    int deleteUser(Integer userId);

    /**
     * 查询使用聚合函数
     */
    @Select("select count(*) from user ")
    int findTotal();

    /**
     * 模糊查询
     */
    @Select("select * from user where username like #{userName} ")
    List<User> findByName(String name);

}
