package cn.lastwhisper.springbootaop.service.impl;

import cn.lastwhisper.springbootaop.mapper.UserMapper;
import cn.lastwhisper.springbootaop.pojo.User;
import cn.lastwhisper.springbootaop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lastwhisper
 * @date 2019/12/5
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public String login(String username, String password) {
        User user = userMapper.findUserByName(username);
        if(user==null){
            return "用户名错误";
        }
        if (password.equals(user.getPassword())) {
            return "登录成功！！！";
        }
        return "密码错误";
    }
}
