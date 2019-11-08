package cn.lastwhisper.jpamanytomany.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


/**
 * @author Administrator
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sys_user")
public class SysUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "user_code")
    private String userCode;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "user_password")
    private String userPassword;
    @Column(name = "user_state")
    private String userState;

    /**
     * 配置用户到角色的多对多关系
     *      配置多对多的映射关系
     *          1.声明表关系的配置
     *              @ManyToMany(targetEntity = Role.class)  //多对多
     *                  targetEntity：代表对方的实体类字节码
     *          2.配置中间表（包含两个外键）
     *                @JoinTable
     *                  name : 中间表的名称
     *                  joinColumns：配置当前对象在中间表的外键
     *                      @JoinColumn的数组
     *                          name：外键名
     *                          referencedColumnName：参照的主表的主键名
     *                  inverseJoinColumns：配置对方对象在中间表的外键
     */
    @ManyToMany(targetEntity = SysRole.class, cascade = CascadeType.ALL)
    @JoinTable(name = "sys_user_role",//中间表的名称
            //中间表sys_user_role字段sys_user_id 关联sys_user表的主键字段user_id
            joinColumns = {@JoinColumn(name = "sys_user_id", referencedColumnName = "user_id")},
            //中间表sys_user_role字段sys_role_id 关联sys_role表的主键字段role_id
            inverseJoinColumns = {@JoinColumn(name = "sys_role_id", referencedColumnName = "role_id")}
    )
    private Set<SysRole> roles = new HashSet<>();
}