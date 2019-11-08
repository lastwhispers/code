package cn.lastwhisper.mybatis.onetomany.domain;

import lombok.*;

import java.io.Serializable;

/**
 * @author Administrator
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Account implements Serializable {
    private Integer id;
    private Integer uid;
    private Double money;

    private User user;
}