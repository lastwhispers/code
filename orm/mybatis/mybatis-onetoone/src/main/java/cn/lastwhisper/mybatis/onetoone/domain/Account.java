package cn.lastwhisper.mybatis.onetoone.domain;

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
    // 基于关联的一对一（多对一）实现
    private User user;
}