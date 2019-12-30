package cn.lastwhisper.user.repository;

import cn.lastwhisper.user.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author lastwhisper
 * @date 2019/11/3
 */
public interface UserRepository extends JpaRepository<UserInfo,String> {

    UserInfo findByOpenid(String openid);

}
