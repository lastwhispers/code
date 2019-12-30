package cn.lastwhisper.user.service.impl;

import cn.lastwhisper.user.domain.UserInfo;
import cn.lastwhisper.user.repository.UserRepository;
import cn.lastwhisper.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lastwhisper
 * @date 2019/11/3
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserInfo findByOpenid(String openid) {
        return userRepository.findByOpenid(openid);
    }
}
