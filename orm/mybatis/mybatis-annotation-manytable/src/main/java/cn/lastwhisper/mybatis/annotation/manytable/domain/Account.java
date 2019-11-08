package cn.lastwhisper.mybatis.annotation.manytable.domain;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Account implements Serializable {
    private Integer id;
    private Integer uid;
    private Double money;
    // 多对一（一对一）关系映射：从表方应该包含一个主表方的对象引用
    private User user;
}