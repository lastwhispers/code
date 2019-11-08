package cn.lastwhisper.mybatis.onetoone.domain;

import lombok.*;

import java.io.Serializable;

/**
 * 基于继承的一对一（多对一）实现
 * @author Administrator
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AccountUser extends Account implements Serializable {
    private String username;
    private String address;

}