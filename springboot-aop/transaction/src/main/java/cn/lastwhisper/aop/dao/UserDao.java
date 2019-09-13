package cn.lastwhisper.aop.dao;


import cn.lastwhisper.aop.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
}
