package cn.lastwhisper.service;

import cn.lastwhisper.aop.dao.OperationLogDao;
import cn.lastwhisper.aop.dao.UserDao;
import cn.lastwhisper.aop.domain.OperationLog;
import cn.lastwhisper.aop.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by cat on 2017-03-12.
 */
@Component
public class DemoService {

    @Autowired
    UserDao userDao;

    @Autowired
    OperationLogDao operationLogDao;


    /**
     * TransactionInterceptor
     *      |--invoke
     *      |--TransactionAspectSupport.invokeWithinTransaction
     *          |--retVal = invocation.proceedWithInvocation();
     *
     * Spring对单个的数据库操作有事务控制，但是对多个的数据库操作需要使用 @Transactional进行统一控制
     *
     */
    // 没有事务控制，非原子操作，导致脏数据
    @Transactional
    public void addUser(String name) {
        OperationLog log = new OperationLog();
        log.setContent("create user:" + name);
        operationLogDao.save(log);

        User user = new User();
        user.setName(name);
        userDao.save(user);
    }
}
