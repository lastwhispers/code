package cn.lastwhisper.springbootmybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.ToString;

/**
 * @desc
 * 
 * @author lastwhisper
 * @email gaojun56@163.com
 */
@Data
@ToString
public class User {
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
