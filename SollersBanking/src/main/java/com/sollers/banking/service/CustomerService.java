package com.sollers.banking.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sollers.banking.dao.CustomerDao;
import com.sollers.banking.errors.SollersSecurityException;
import com.sollers.banking.vo.CustomerVO;

@Component
public class CustomerService {

	@Autowired
	private CustomerDao customerDao;

	public boolean createCustomer(CustomerVO customerVO) throws SollersSecurityException {
		// Invoke DAO layer
		System.out.println("Entering createCustomer method of CustomerService");
		boolean b = customerDao.createCustomer(customerVO);
		System.out.println("Exiting createCustomer method of CustomerService");
		return b;
		
	}

	public void modifyCustomer(CustomerVO customerVO) throws SollersSecurityException {
		System.out.println("Entering modifyCustomer method of CustomerService");
		customerDao.modifyCustomer(customerVO);
		System.out.println("Exiting modifyCustomer method of CustomerService");
	}

	public void deleteCustomer(String loginId, long customerId) throws SollersSecurityException {
		System.out.println("Entering deleteCustomer method of CustomerService");
		customerDao.deleteCustomer(loginId, customerId);
		System.out.println("Exiting deleteCustomer method of CustomerService");
	}

	public Map<String, CustomerVO> getAllCustomers() throws SollersSecurityException {
		System.out.println("Entering getAllCustomers method of CustomerService");
		Map<String, CustomerVO> m = customerDao.getAllCustomers();
		System.out.println("Size of all customers - " + m.size());
		System.out.println("Exiting getAllCustomers method of CustomerService");
		return m;
	}

	public boolean validateLogin(String loginId, String password) throws SollersSecurityException {
		System.out.println("Entering deleteCustomer method of CustomerService");
		boolean b = customerDao.validateLogin(loginId, password);
		System.out.println("Exiting deleteCustomer method of CustomerService");
		return b;
	}

}
