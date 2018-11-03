package com.sollers.banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sollers.banking.errors.SollersSecurityException;
import com.sollers.banking.service.CustomerService;
import com.sollers.banking.vo.LoginVO;

@Controller
@RequestMapping("/")
public class LoginController {
	
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public String authenticateUser(@ModelAttribute("login") LoginVO loginObj, Model model) {
		System.out.println("Load authenticateUser page");
		try {
			boolean b = customerService.validateLogin(loginObj.getUserName(), loginObj.getPassword());
			b = true;
			if(b) {
				System.out.println("Login successful");
				model.addAttribute("loggedinuser", loginObj.getUserName());
				return "dashboard";
			}
			else {
				System.out.println("Login failed");
				model.addAttribute("message", "User authentication error!!!");
				return "index";
			}
			
		} catch (SollersSecurityException e) {
			System.out.println("Exception occured in authenticateUser - " + e.getMessage());
		}
		return "index";
	}
}
