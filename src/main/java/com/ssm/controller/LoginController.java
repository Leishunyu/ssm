/**
 * LoginController.java
 */
package com.ssm.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssm.entity.User;
import com.ssm.service.UserService;
import com.ssm.utils.MD5;

/**
 * @author wywl Jan 3, 2018 3:47:41 PM modified by :
 */
@RequestMapping(value = "/login")
@Controller("loginController")
public class LoginController {

	@Resource
	private UserService userService;

	@RequestMapping(value = "/repass")
	public String repass(HttpSession session) {
		if (session.getAttribute("user") == null) {
			return "login";
		}
		return "repass";
	}

	@RequestMapping(value = "/repassword")
	public String repassword(@RequestParam(required = true) String password, HttpSession session, final Model model) {
		if (session.getAttribute("user") == null) {
			return "login";
		}
		User sessionUser = (User) session.getAttribute("user");
		userService.repass(sessionUser.getId(), MD5.string2MD5(password));
		model.addAttribute("error", "账号或密码错误");
		return "redirect:/pay/list.htm";
	}

	@RequestMapping(value = "/login")
	public String findAll(@RequestParam(required = true) String name, @RequestParam(required = true) String password,
			HttpSession session, final Model model) {
		User user = userService.login(name, MD5.string2MD5(password));
		if (user != null) {
			session.setAttribute("user", user);
			return "redirect:/pay/list.htm";
		}
		model.addAttribute("error", "账号或密码错误");
		return "login";
	}
}
