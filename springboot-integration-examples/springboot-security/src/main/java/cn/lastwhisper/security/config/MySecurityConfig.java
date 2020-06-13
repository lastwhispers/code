package cn.lastwhisper.security.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 定制请求的授权规则
     *
     * @param http
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);

        http.authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("VIP1")
                .antMatchers("/level2/**").hasRole("VIP2")
                .antMatchers("/level3/**").hasRole("VIP3");

        // 开启自动配置的登陆功能，效果，如果没有登陆，没有权限就会来到登陆页面
        http.formLogin();
        // 1、/login来到登陆页
        // 2、重定向到/login?error表示登陆失败
        // 3、更多详细规定
        // 4、默认post形式的 /login代表处理登陆
        // 5、一但定制loginPage；那么 loginPage的post请求就是登陆

        // 自定义登录表单
        //http.formLogin().usernameParameter("user").passwordParameter("pwd")
        //        .loginPage("/userlogin");

        // 开启自动配置的注销功能。
        http.logout().logoutSuccessUrl("/");// 注销成功以后来到 logoutSuccessUrl("/")
        // 1、访问 /logout 表示用户注销，清空session
        // 2、注销成功会返回 /login?logout 页面；

        // 开启记住我功能
        http.rememberMe().rememberMeParameter("remeber");
        // 登陆成功以后，将cookie发给浏览器保存，以后访问页面带上这个cookie，只要通过检查就可以免登录
        // 点击注销会删除cookie

    }

    /**
     * 定义认证规则
     *  认证方式
     *  （1）inMemoryAuthentication 内存数据
     *  （2）userDetailsService 既可以内存也可以从数据库
     *  认证密码设置有两种方式：
     *  （1）password("{bcrypt}$2a$10$PoXmoLJurvqDW/uZRuNdV.MStSU3ZcVNZTApTE0JZ9kDRqi8EVL7u")
     *  （2）password(new BCryptPasswordEncoder().encode("123456"))
     *
     * @param auth
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //super.configure(auth);
        auth.inMemoryAuthentication()
                //.withUser("zhangsan").password("{bcrypt}$2a$10$PoXmoLJurvqDW/uZRuNdV.MStSU3ZcVNZTApTE0JZ9kDRqi8EVL7u").roles("VIP1", "VIP2")
                .withUser("zhangsan").password("{bcrypt}$2a$10$PoXmoLJurvqDW/uZRuNdV.MStSU3ZcVNZTApTE0JZ9kDRqi8EVL7u").roles("VIP1", "VIP2")
                .and()
                .withUser("lisi").password("{bcrypt}$2a$10$PoXmoLJurvqDW/uZRuNdV.MStSU3ZcVNZTApTE0JZ9kDRqi8EVL7u").roles("VIP2", "VIP3")
                .and()
                .withUser("wangwu").password("{bcrypt}$2a$10$PoXmoLJurvqDW/uZRuNdV.MStSU3ZcVNZTApTE0JZ9kDRqi8EVL7u").roles("VIP1", "VIP3");

    }


    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("123456"));
    }
}
