package cn.lastwhisper.mybatis.annotation.manytable.dao;

import cn.lastwhisper.mybatis.annotation.manytable.domain.Account;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * mybatis注解替换xml配置多对一
 * @author Administrator
 */
public interface AccountMapper {

    /**
     *
     * @return
     */
    @Select("select * from account")
    @Results(id="accountMap",
            value= {
                    @Result(id=true,column="id",property="id"),
                    @Result(column="uid",property="uid"),
                    @Result(column="money",property="money"),
                    @Result(column="uid",
                            property="user",
                            one=@One(select="cn.lastwhisper.mybatis.annotation.manytable.dao.UserMapper.findById",
                                    fetchType= FetchType.LAZY)
                    )
            })
    List<Account> findAll();


    @Select("select * from account where uid = #{uid} ")
    List<Account> findByUid(Integer userId);
}
