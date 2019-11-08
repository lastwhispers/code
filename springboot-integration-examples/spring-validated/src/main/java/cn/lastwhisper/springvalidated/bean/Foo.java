package cn.lastwhisper.springvalidated.bean;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class Foo {
    @NotBlank
    private String name;

    //@Min(value = 18)
    @Min(value = 18,groups = {Adult.class})
    private Integer age;

    public interface Adult{}

    public interface Minor{}

    @Pattern(regexp = "^1(3|4|5|7|8)\\d{9}$",message = "手机号码格式错误")
    @NotBlank(message = "手机号码不能为空")
    private String phone;

    @Email(message = "邮箱格式错误")
    private String email;
    
    //... getter setter

}