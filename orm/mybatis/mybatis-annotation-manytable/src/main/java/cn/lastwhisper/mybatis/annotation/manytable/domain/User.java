package cn.lastwhisper.mybatis.annotation.manytable.domain;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Administrator
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User implements Serializable {
    private Integer userId;
    private String userName;
    private Date userBirthday;
    private String userSex;
    private String userAddress;
    //一对多关系映射：主表方法应该包含一个从表方的集合引用
    private List<Account> accounts;
}