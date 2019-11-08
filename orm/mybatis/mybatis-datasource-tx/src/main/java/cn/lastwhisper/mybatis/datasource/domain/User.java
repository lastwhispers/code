package cn.lastwhisper.mybatis.datasource.domain;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Administrator
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User implements Serializable {
    private Integer userid;
    private String username;
    private Date userbirthday;
    private String usersex;
    private String useraddress;
}