package example.hellosecurity.web;

import example.hellosecurity.utils.JwtTokenUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Chiang
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtTokenUtils jwtTokenUtils;
    private final PasswordEncoder passwordEncoder;

    private static final String PASSWORD = "123456";

    @GetMapping(value = "/login")
    public String login(String user, String password) {

        if(StringUtils.isEmpty(user) || StringUtils.isEmpty(password)){
            return "账号或密码不能为空";
        }

        String encode = passwordEncoder.encode(PASSWORD);
        if(!passwordEncoder.matches(password,encode)){
            return "密码不正确";
        }

        Map<String, Object> map = new HashMap<>(2);
        map.put("user", user);
        map.put("password", password);
        return jwtTokenUtils.createToken(map);
    }
}
