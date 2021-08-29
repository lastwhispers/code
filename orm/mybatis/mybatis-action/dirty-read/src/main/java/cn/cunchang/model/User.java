package cn.cunchang.model;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author cunchang
 * @date 2020/5/30
 */
@Data
@ToString
public class User {

    private Long id;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 性别
     */
    private String sex;

    /**
     * 地址
     */
    private String address;



}
