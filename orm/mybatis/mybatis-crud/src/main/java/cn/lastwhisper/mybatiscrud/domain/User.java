package cn.lastwhisper.mybatiscrud.domain;

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
    private Integer userId;
    private String userName;
    private Date userBirthday;
    private String userSex;
    private String userAddress;
}