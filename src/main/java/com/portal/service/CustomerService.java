package com.portal.service;

import java.util.List;
import java.util.Set;

import com.portal.entity.Customer;
import com.portal.entity.Deals;

public interface CustomerService {
	
	public void saveCustomerObj(Customer customerobj);

	public Customer login(Customer customerObj);
			
	public Customer getCustomerObj(Customer customerObj);

	public Customer getCustomerObjById(int cid);

	public Set<Deals> getAllDealsObj();

}
