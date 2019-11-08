package cn.lastwhisper.user.service;

import cn.lastwhisper.user.domain.UserInfo;

/**
 *
 * @author lastwhisper
 * @date 2019/11/3
 */
public interface UserService {

    UserInfo findByOpenid(String openid);

}
