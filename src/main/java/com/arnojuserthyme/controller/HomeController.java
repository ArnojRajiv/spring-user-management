package com.arnojuserthyme.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.arnojuserthyme.model.UserDtls;
import com.arnojuserthyme.service.UserService;

@Controller
public class HomeController {

	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/signin")
	public String login() {
		return "login";
	}

	@GetMapping("/register")
	public String register() {
		return "register";
	}
	
	@PostMapping("/createuser")
	public String createuser(@ModelAttribute UserDtls user, HttpSession session) {

		
		
		boolean f = userService.checkEmail(user.getEmail());
		if(!user.checkvalid())
		{
			session.setAttribute("fail", "Enter valid details");	
		}

		else {

			if (f) {
				session.setAttribute("fail", "Email Id alreday exists");
			}

			else {
				UserDtls userDtls = userService.createuser(user);
				if (userDtls != null) {
					session.setAttribute("msg", "Register Sucessfully");
				} else {
					session.setAttribute("msg", "Something wrong on server");
				}
			}
		}

		
		return "redirect:/register";
	}

}