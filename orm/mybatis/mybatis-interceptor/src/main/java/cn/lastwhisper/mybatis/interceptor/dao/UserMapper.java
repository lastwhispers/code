package cn.lastwhisper.mybatis.interceptor.dao;

import cn.lastwhisper.mybatis.interceptor.datascope.DataScope;
import cn.lastwhisper.mybatis.interceptor.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
public interface UserMapper {

    List<Map<String, Object>> selectUsers(@Param("dataScope") DataScope dataScope);


    List<User> findUser();

}
