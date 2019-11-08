package cn.lastwhisper.mybatis.onetoone.dao;

import cn.lastwhisper.mybatis.onetoone.domain.Account;
import cn.lastwhisper.mybatis.onetoone.domain.AccountUser;

import java.util.List;

/**
 * @author Administrator
 */
public interface AccountMapper {
    /**
     * 基于继承实现一对一（多对一）
     * @return
     */
    List<AccountUser> findAll();

    /**
     * 基于关联实现一对一（多对一）
     * @return
     */
    List<Account> findAll2();
}
