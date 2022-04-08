package cn.cunchang.dao;

import cn.cunchang.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectKey;

import java.util.List;

/**
 * @author Administrator
 */
public interface UserMapper {

    /**
     * 普通新增
     * @param user
     * @return
     */
    int insert(User user);

    /**
     * xml配置<selectKey></selectKey>标签获取id
     * @param user
     * @return
     */
    int insert1(User user);

    /**
     * xml配置useGeneratedKeys
     * @param user
     * @return
     */
    int insert2(User user);

    /**
     * 结合@Param注解
     * @param user
     * @return
     */
    int insert3(@Param("do") User user);

    /**
     * 注解配置
     * @param user
     * @return
     */
    @Insert("insert into user(username,sex,birthday,address) values(#{username},#{sex},#{birthday},#{address})")
    @SelectKey(keyColumn = "id", keyProperty = "id", resultType = Integer.class, before =
            false, statement = {"select last_insert_id()"})
    int insert4(User user);

    /**
     * 普通批量插入
     * @param list
     * @return
     */
    int insertBatch(@Param("list") List<User> list);

    /**
     * 批量插入xml配置
     * @param list
     * @return
     */
    int insertBatch1(@Param("list") List<User> list);

    /**
     * 批量插入注解配置
     * @param list
     * @return
     */
    @Insert("<script>" +
            "insert into user(username,birthday,sex,address) values " +
            "<foreach collection='list' item='do' separator=','>(#{do.username},#{do.birthday},#{do.sex},#{do.address})</foreach>" +
            "</script>")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertBatch2(@Param("list") List<User> list);



    /*************ExecutorType.BATCH模式************/



}
