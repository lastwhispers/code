package cn.cunchang;

import java.lang.reflect.Proxy;
import java.util.List;

/**
 * @author cunchang
 * @date 2021/11/25 12:53 上午
 */
public class Main {

    public static void main(String[] args) {
        // 获取SqlSession
        SqlSession sqlSession = new SqlSession() {
            @Override
            public List selectList(String statementId) {
                System.out.println(statementId);
                return null;
            }
        };
        // 获取UserMapper接口
        MyMapperProxy userMapperProxy = new MyMapperProxy<>(UserMapper.class, sqlSession);
        UserMapper userMapper = (UserMapper) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                new Class[] { UserMapper.class }, userMapperProxy);
        // 调用selectAll方法
        List<SysUser> user = userMapper.selectAll();
    }
}
