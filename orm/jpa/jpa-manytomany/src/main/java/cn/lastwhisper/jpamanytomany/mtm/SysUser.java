package cn.lastwhisper.jpamanytomany.mtm;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


/**
 * @author Administrator
 */
public class SysUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    @ManyToMany(mappedBy="users")
    private Set<SysRole> roles = new HashSet<>();
}