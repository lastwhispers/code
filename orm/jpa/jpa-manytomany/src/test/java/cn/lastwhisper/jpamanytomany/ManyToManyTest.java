package cn.lastwhisper.jpamanytomany;

import cn.lastwhisper.jpamanytomany.dao.RoleDao;
import cn.lastwhisper.jpamanytomany.dao.UserDao;
import cn.lastwhisper.jpamanytomany.domain.SysRole;
import cn.lastwhisper.jpamanytomany.domain.SysUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManyToManyTest {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    /**
     * 需求：
     * 	    保存用户和角色
     * 要求：
     * 	    创建2个用户和3个角色
     * 	    让1号用户具有1号和2号角色(双向的)
     * 	    让2号用户具有2号和3号角色(双向的)
     *      保存用户和角色
     * 问题：
     *      在保存时，会出现主键重复的错误，因为都是要往中间表中保存数据造成的。
     * 解决办法：
     * 	    让任意一方放弃维护关联关系的权利
     * 	        让未配置@JoinTable的一方放弃维护关系 SysRole.@ManyToMany(mappedBy="roles")
     */
    @Test
    @Transactional  //开启事务
    @Rollback(false)//设置为不回滚
    public void testSave() {
        SysUser user = new SysUser();
        user.setUserName("小李");
        SysRole role = new SysRole();
        role.setRoleName("java程序员");

        //配置用户到角色关系，可以对中间表中的数据进行维护     1-1
        user.getRoles().add(role);
        //配置角色到用户的关系，可以对中间表的数据进行维护     1-1
        role.getUsers().add(user);

        //保存
        roleDao.save(role);
        userDao.save(user);
    }

    /**
     * 测试级联添加（保存一个用户的同时保存用户的关联角色）
     *  cascade = CascadeType.ALL
     */
    @Test
    @Transactional
    @Rollback(false)
    public void testCasCadeAdd() {
        SysUser user = new SysUser();
        user.setUserName("小李");
        SysRole role = new SysRole();
        role.setRoleName("java程序员");

        //配置用户到角色关系，可以对中间表中的数据进行维护     1-1
        user.getRoles().add(role);
        //配置角色到用户的关系，可以对中间表的数据进行维护     1-1
        role.getUsers().add(user);

        userDao.save(user);
    }

    /**
     * 测试级联删除
     *  删除id为1的用户，同时删除他的关联对象
     */
    @Test
    @Transactional
    @Rollback(false)
    public void testCasCadeRemove() {
        //查询1号用户
        SysUser user = userDao.findById(1l).get();
        //删除1号用户s
        userDao.delete(user);

    }

}
