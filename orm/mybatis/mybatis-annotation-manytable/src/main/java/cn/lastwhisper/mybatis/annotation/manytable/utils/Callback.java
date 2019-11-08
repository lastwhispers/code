package cn.lastwhisper.mybatis.annotation.manytable.utils;

import org.apache.ibatis.session.SqlSession;

/**
 * @author Administrator
 */
@FunctionalInterface
public interface Callback {
    /**
     * 执行者
     */
    void execute(SqlSession sqlSession);
}
