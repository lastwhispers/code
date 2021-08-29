package cn.cunchang.controller;

import cn.cunchang.mapper.UserMapper;
import cn.cunchang.model.User;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author cunchang
 * @date 2020/5/31
 */
@Api(value = "UserController", description = "")
@RestController
@RequestMapping("")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/init")
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED,
            rollbackFor = Exception.class)
    public String init() {
        User user = new User();
        user.setId(1L);
        user.setUsername("tomcat1");
        user.setBirthday(new Date());
        user.setSex("男");
        user.setAddress("北京市顺义区1");

        int row = userMapper.insert(user);
        return "succes"+row;
    }

    @GetMapping("/rctx1")
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED,
            rollbackFor = Exception.class)
    public String rctx1() throws InterruptedException {
        User user1 = userMapper.selectById(1L);
        System.err.println("delete前,用户id=1的信息："+user1);
        // 睡10s，等delete执行完
        TimeUnit.SECONDS.sleep(10L);

        User user2 = userMapper.selectById(1L);
        System.err.println("delete后,用户id=1的信息："+user2);
        System.err.println("user1==user2?："+(user1==user2));
        return "succes";
    }

    @GetMapping("/rctx2")
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED,
            rollbackFor = Exception.class)
    public String rctx2() {
        int row = userMapper.deleteById(1L);
        return "succes";
    }


}
