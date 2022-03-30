package example.hellosecurity.filter;

import example.hellosecurity.config.JwtSecurityProperties;
import example.hellosecurity.utils.JwtTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Token的过滤器
 *
 * @author Chiang
 */
@Component
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private final JwtTokenUtils jwtTokenUtils;
    private final JwtSecurityProperties jwtSecurityProperties;

    public JwtAuthenticationTokenFilter(JwtTokenUtils jwtTokenUtils,JwtSecurityProperties jwtSecurityProperties) {
        this.jwtTokenUtils = jwtTokenUtils;
        this.jwtSecurityProperties = jwtSecurityProperties;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, @NonNull HttpServletResponse httpServletResponse, @NonNull FilterChain filterChain) throws ServletException, IOException {

        //获取request token
        String token = null;
        String bearerToken = httpServletRequest.getHeader(jwtSecurityProperties.getHeader());
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(jwtSecurityProperties.getTokenStartWith())) {
            token = bearerToken.substring(jwtSecurityProperties.getTokenStartWith().length());
        }

        if (StringUtils.hasText(token) && jwtTokenUtils.validateToken(token)) {
            Authentication authentication = jwtTokenUtils.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.debug("set Authentication to security context for '{}', uri: {}, expire: {}", authentication.getName(), httpServletRequest.getRequestURI(),jwtTokenUtils.getExpirationDateFromToken(token));
        } else {
            log.debug("no valid JWT token found, uri: {}, expire:{}", httpServletRequest.getRequestURI(),jwtTokenUtils.getExpirationDateFromToken(token));
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
