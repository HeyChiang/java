/*
 * Copyright 2002-2016 the original author or authors.
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
package example.hellosecurity.web;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * @author Joe Grandja
 */
@Controller
public class MainController {

	@RequestMapping("/")
	public String root() {
		return "redirect:/index";
	}

	/**
	 * 必须要有USER权限
	 */
	@RequestMapping("/index")
	public String index() {
		return "index";
	}

	@RequestMapping("/user/index")
	public String userIndex() {
		return "user/index";
	}

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping("/haveAuthority")
	@ResponseBody
	public String haveAuthority(){
		return "权限符合";
	}

	/**
	 * 打印所有的权限信息
	 */
	@RequestMapping("/printAuthority")
	@ResponseBody
	public String printAuthority(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		print(authentication.getAuthorities());
		print(authentication.getCredentials());
		print(authentication.getDetails());
		print(authentication.getPrincipal());

		//修改权限
		List<GrantedAuthority> updatedAuthorities = new ArrayList<>(authentication.getAuthorities());
		updatedAuthorities.add(() -> "wangwu");
		Authentication newAuth = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), updatedAuthorities);
		SecurityContextHolder.getContext().setAuthentication(newAuth);

		return authentication.getPrincipal().toString();
	}

	@RequestMapping("/anonymous")
	@ResponseBody
	public String anonymous(){
		return "任何人都可以访问";
	}

	@RequestMapping("/login-error")
	@ResponseBody
	public String loginError(Model model) {
		return "登录错误了";
	}

	@RequestMapping("/deny")
	@ResponseBody
	public String deny() {
		return "拒绝访问，没有权限";
	}

	private void print(Object o){
		if(o == null){
			return;
		}
		System.out.println(o.toString());
	}
}