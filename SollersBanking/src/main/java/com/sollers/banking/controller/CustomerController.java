package com.sollers.banking.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sollers.banking.errors.SollersSecurityException;
import com.sollers.banking.service.CustomerService;
import com.sollers.banking.vo.CustomerVO;

@Controller
@RequestMapping("/")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@RequestMapping("/createForm")
	public String showCreateForm(HttpServletRequest request, Model model) {
		System.out.println("**********************************************");
		model.addAttribute("customer", new CustomerVO());
		return "signup";

	}

	@RequestMapping("/viewCustomer")
	public String getCustomer(HttpServletRequest request, Model model) {
		System.out.println("**********************************************");
		model.addAttribute("message", "This is my first controller");
		return "viewCustomer";

	}

	@RequestMapping("/createCustomer")
	public String createCustomer(@ModelAttribute("customerVO") CustomerVO customerObj, Model model) {
		try {
			System.out.println("**********************************************");
			System.out.println(customerObj.getFirstName());

			System.out.println("Customer Service -  " + customerService);
			// Call bean and your service to passvo
			boolean b = customerService.createCustomer(customerObj);
			if (b) {
				model.addAttribute("message", "Signup Successful! Please login");
			} else {
				model.addAttribute("message", "Some generic message for failure");
			}
		} catch (SollersSecurityException e) {
			System.out.println("Exception occured in createCustomer - " + e.getMessage());
			model.addAttribute("message", "Some generic message for failure");
		}

		// if creation is successful then return customerSuccess, for failure return genericError
		return "index";

	}
}
