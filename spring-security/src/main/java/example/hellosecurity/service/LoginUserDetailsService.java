package example.hellosecurity.service;

import example.hellosecurity.module.LoginUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

import java.util.HashSet;
import java.util.Set;

public class LoginUserDetailsService implements UserDetailsService {

    /**
     *  假装通过username去数据库里查询用户数据
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginUser loginUser = new LoginUser();
        loginUser.setUserName(username);
        loginUser.setPassword(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("password"));

        // 添加权限
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(() -> "authority");
        loginUser.setAuthorities(grantedAuthorities);

        return loginUser;
    }

}
