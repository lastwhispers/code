package cn.lastwhisper.springsecurity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class UserDetailsServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws
            UsernameNotFoundException {
        System.out.println("经过UserDetailsServiceImpl");
        //构建角色集合，项目中此处应该是根据用户名查询用户的角色列表
        List<GrantedAuthority> grantedAuthors = new ArrayList<>();
        grantedAuthors.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return new User(s, "$2a$10$61ogZY7EXsMDWeVGQpDq3OBF1.phaUu7.xrwLyWFTOu8woE08zMIW",
                grantedAuthors);
    }
}