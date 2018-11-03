package com.sollers.banking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sollers.banking.vo.CustomerVO;
import com.sollers.banking.vo.LoginVO;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@RequestMapping("/")
	public String showHomePage() {
		System.out.println("Load home page");
		return "index";
	}
	
	@RequestMapping("/login")
	public String showLoginPage(Model model) {
		System.out.println("Load showLoginPage page");
		model.addAttribute("login", new LoginVO());
		return "login";
	}
	
	@RequestMapping("/signup")
	public String showSignUpPage(Model model) {
		System.out.println("Load showSignUpPage page");
		model.addAttribute("customer", new CustomerVO());
		return "signup";
	}

	
	@RequestMapping("/temp")
	public String showTemp() {
		System.out.println("Load showTemp page");
		return "dashboard";
	}
}
