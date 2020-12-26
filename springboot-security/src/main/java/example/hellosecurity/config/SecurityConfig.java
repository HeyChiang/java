/*
 * Copyright 2002-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package example.hellosecurity.config;

import example.hellosecurity.service.LoginUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * 1.Security配置好路径需要的权限，以及登录成功和登录失败的处理
 * 2.当访问/login地址时，UsernamePasswordAuthenticationFilter会做如下操作
 *     1.获取request里的username和password
 *     2.将username传入LoginUserDetailsService查询帐号和密码
 *     2.验证帐号和密码的结果，跳转成功或者失败的页面（默认跳转/index）
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 配置地址需要哪些权限
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorize -> authorize
                        .antMatchers("/css/**", "/index").permitAll()
                        .antMatchers("/user/**").hasRole("USER")
                        .antMatchers("/anonymous","/login").anonymous() //登录页面不需要权限
                        .antMatchers("/haveAuthority").hasAuthority("authority")
                        .anyRequest().authenticated()
                ).formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .failureUrl("/login-error")
                        .successForwardUrl("/haveAuthority")
                ).logout((logout) -> {
                    logout.invalidateHttpSession(true);
        }).exceptionHandling(handler ->{
            // 设置没有权限时跳转的地址，也可以实现去AuthenticationEntryPoint直接返回拒绝的结果
            handler.accessDeniedPage("/deny");
        });

    }


    /**
     * 用户登录时候获取用户信息
     */
    @Override
    @Bean
    public UserDetailsService userDetailsService() {

//		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//		// outputs {bcrypt}$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG
//		// remember the password that is printed out and use in the next step
//		System.out.println(encoder.encode("password"));
//
//
//		UserDetails userDetails = User.withUsername("user")
//				.password("{bcrypt}$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG")
//				.roles("USER")
//                .authorities("authority") // 和roles是一个东西，一起使用只会生效后面这个调用的
//				.build();


//        InMemoryUser主要的目的是为了测试用的，生产环境不要使用
//        return new InMemoryUserDetailsManager(userDetails);

        return new LoginUserDetailsService();
    }

}