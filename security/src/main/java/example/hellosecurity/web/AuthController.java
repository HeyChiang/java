package example.hellosecurity.web;

import example.hellosecurity.utils.JwtTokenUtils;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtTokenUtils jwtTokenUtils;

    public AuthController(JwtTokenUtils jwtTokenUtils) {
        this.jwtTokenUtils = jwtTokenUtils;
    }

    @GetMapping(value = "/login")
    public String login(String user, String password) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("user", user);
        map.put("password", password);
        return jwtTokenUtils.createToken(map);
    }
}
