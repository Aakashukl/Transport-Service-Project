package com.portal.service;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.portal.dao.CustomerDao;
import com.portal.entity.Customer;
import com.portal.entity.Deals;
import com.portal.entity.Query;
import com.portal.entity.Transporter;

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

	// public boolean login(String username, String password) {
	public Customer login(Customer customerObj) {
		Customer customerObject = getCustomerObj(customerObj);
		if (customerObject != null
				&& hashPasswordCheck(customerObj.getCustomerPassword(), customerObject.getCustomerPassword())) {
			return customerObject;
		} else {
			return customerObj;
		}

	}

	public Customer getCustomerObjById(int cid) {
		Customer customerObj = customerDao.getCustomerObjById(cid);
		customerObj.setCustomerPassword(null);
		return customerObj;
	}

//-------------get all deals-----------------
	public Set<Deals> getAllDealsObj() {
		List<Deals> dealsListObj = customerDao.getAllDealsObj();
		Set<Deals> dealsListSet = new HashSet<Deals>();
		for (Deals deal : dealsListObj) {
			dealsListSet.add(deal);
		}
		return dealsListSet;
	}

//----------------get filtered deals ----------------------------------
	public Set<Deals> getAllDealsObj(String fromCity, String toCity, Date fromDate, Date toDate) {
		List<Deals> dealsListObj = customerDao.getAllDealsObj(fromCity, toCity, fromDate, toDate);
		Set<Deals> dealsListSet = new HashSet<Deals>();
		for (Deals deal : dealsListObj) {
			dealsListSet.add(deal);
		}
		return dealsListSet;
	}

//---------------Filter By From City--------------------------
	public Set<Deals> getAllDealsObj(String fromCity) {
		List<Deals> dealsListObj = customerDao.getAllDealsObj(fromCity);
		Set<Deals> dealsListSet = new HashSet<Deals>();
		for (Deals deal : dealsListObj) {
			dealsListSet.add(deal);
		}
		return dealsListSet;
	}
//-----------------Filter By From CityA to CityB----------------------- 

	public Set<Deals> getAllDealsObj(String fromCity, String toCity) {
		List<Deals> dealsListObj = customerDao.getAllDealsObj(fromCity, toCity);
		Set<Deals> dealsListSet = new HashSet<Deals>();
		for (Deals deal : dealsListObj) {
			dealsListSet.add(deal);
		}
		return dealsListSet;
	}

//-----------------Filter By From DateA to DateB----------------------- 

	public Set<Deals> getAllDealsObj(Date fromDate, Date toDate) {

		List<Deals> dealsListObj = customerDao.getAllDealsObj(fromDate, toDate);
		Set<Deals> dealsListSet = new HashSet<Deals>();
		for (Deals deal : dealsListObj) {
			dealsListSet.add(deal);
		}
		return dealsListSet;
	}

	// ---------------Filter By From DateA--------------------------

	public Set<Deals> getAllDealsObj(Date fromDate) {
		List<Deals> dealsListObj = customerDao.getAllDealsObj(fromDate);
		Set<Deals> dealsListSet = new HashSet<Deals>();
		for (Deals deal : dealsListObj) {
			dealsListSet.add(deal);
		}
		return dealsListSet;
	}

//----------------Save Customer Query------------------------	
	public void saveCustomerQuery(int customerID, int transporterId, int dealId, Query queryObj) {
		queryObj.setCustomer(new Customer(customerID));
		queryObj.setDeals(new Deals(dealId));
		queryObj.setTransporter(new Transporter(transporterId));
		customerDao.saveCustomerQuery(queryObj);
	}

	public List<Query> getCustomerQueryListById(int customerID) {
		List<Query> customerAllQueryList = customerDao.getCustomerQueryListById(customerID);
		return customerAllQueryList;
	}

	// customerObject.getCustomerPassword().equals(customerObj.getCustomerPassword())
}
