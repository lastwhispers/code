package cn.lastwhisper.mybatis.manytomany.domain;

import lombok.*;

import java.io.Serializable;
import java.util.List;
/**
 * @author Administrator
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Role implements Serializable {
    private Integer roleId;
    private String roleName;
    private String roleDesc;

    // 一对多
    private List<User> users;
}