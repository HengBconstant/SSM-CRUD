package com.sc.crud.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/shiro")
public class ShiroController {

	@RequestMapping("/login")
	public String login(@RequestParam("user")String user,@RequestParam("pwd")String pwd) {
		Subject subject = SecurityUtils.getSubject();
		if(!subject.isAuthenticated()) {
			UsernamePasswordToken token = new UsernamePasswordToken(user, pwd);
			token.setRememberMe(true);
			try {
				subject.login(token);
			} catch (Exception e) {
				System.out.println("登陆失败："+e.getMessage());
			}
		}
		return "redirect:/index.jsp";
	}
}
