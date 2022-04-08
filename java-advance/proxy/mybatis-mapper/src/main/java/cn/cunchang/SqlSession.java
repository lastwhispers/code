package cn.cunchang;

import java.util.List;

/**
 * @author cunchang
 * @date 2021/11/25 12:46 上午
 */
public interface SqlSession {

    List selectList(String statementId);

}
