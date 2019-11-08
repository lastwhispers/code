package cn.lastwhisper.mybatis.onetoone.domain;

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
    private Integer id;
    private String username;
    private String address;
    private String sex;
    private Date birthday;
}