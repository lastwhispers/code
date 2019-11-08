package cn.lastwhisper.jpamanytomany.mtm;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 角色的数据模型
 * @author Administrator
 */
public class SysRole implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")

    @ManyToMany
    @JoinTable(name = "sys_user_role",//中间表的名称
            //中间表sys_user_role字段sys_role_id 关联sys_role表的主键字段role_id
            joinColumns = {@JoinColumn(name = "sys_role_id", referencedColumnName = "role_id")},
            //中间表sys_user_role字段sys_user_id 关联sys_user表的主键字段user_id
            inverseJoinColumns = {@JoinColumn(name = "sys_user_id", referencedColumnName = "user_id")}
    )
    private Set<SysUser> users = new HashSet<>();

}