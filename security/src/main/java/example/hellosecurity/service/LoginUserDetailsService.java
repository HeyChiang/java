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

/**
 * 用户登录查询
 *
 * @author Chiang
 */
public class LoginUserDetailsService implements UserDetailsService {

    /**
     *  假装通过username去数据库里查询用户数据
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginUser loginUser = new LoginUser();
        loginUser.setUserName(username);

        // 添加权限
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(() -> "authority");
        loginUser.setAuthorities(grantedAuthorities);

        return loginUser;
    }

}
