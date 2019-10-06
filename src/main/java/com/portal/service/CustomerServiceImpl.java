package com.portal.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.portal.dao.CustomerDao;
import com.portal.entity.Customer;
import com.portal.entity.Deals;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerDao customerDao;

	public static String hashPasswordConverter(String passwordPlainText) {
		String salt = BCrypt.gensalt(5); 
		String hashPassword = BCrypt.hashpw(passwordPlainText, salt);
		return hashPassword;
	}
	
	public static Boolean hashPasswordCheck(String loggerPassword, String passwordFromDatabase) {
		 
		boolean hashPassword = BCrypt.checkpw(loggerPassword, passwordFromDatabase);
		return hashPassword;
	}
	
	public void saveCustomerObj(Customer customerObj) {
		
		String password = customerObj.getCustomerPassword();
		String hashPassword = hashPasswordConverter(password);
		customerObj.setCustomerPassword(hashPassword);
		customerDao.saveCustomerObj(customerObj);
	}

	public Customer getCustomerObj(Customer customerObj) {
		Customer customerObject = customerDao.getCustomerObj(customerObj);
		return customerObject;
	}


	//public boolean login(String username, String password) {
	public Customer login(Customer customerObj) {
		Customer customerObject = getCustomerObj(customerObj);
		if(customerObject != null && hashPasswordCheck(customerObj.getCustomerPassword(), customerObject.getCustomerPassword())){
			return customerObject;
		}
		else {
			return customerObj;
		}
		
	}

	public Customer getCustomerObjById(int cid) {
		Customer customerObj = customerDao.getCustomerObjById(cid);
		customerObj.setCustomerPassword(null);
		return customerObj;
	}

	public Set<Deals> getAllDealsObj() {
		List<Deals> dealsListObj = customerDao.getAllDealsObj();
		Set<Deals> dealsListSet = new HashSet<Deals>();
		for(Deals deal:dealsListObj) {
			dealsListSet.add(deal);
		}
		return dealsListSet;
	}
	
	//customerObject.getCustomerPassword().equals(customerObj.getCustomerPassword())
}
