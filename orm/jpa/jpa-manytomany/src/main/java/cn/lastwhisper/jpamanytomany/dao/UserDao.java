package cn.lastwhisper.jpamanytomany.dao;

import cn.lastwhisper.jpamanytomany.domain.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 *
 * @author lastwhisper
 * @date 2019/10/17
 */
public interface UserDao extends JpaRepository<SysUser, Long>, JpaSpecificationExecutor<SysUser> {
}
