package com.portal.dao;

import java.util.List;
import java.util.Set;

import com.portal.entity.Customer;
import com.portal.entity.Deals;

public interface CustomerDao {
	
	public void saveCustomerObj(Customer customerObj);
	
	//public Customer getCustomerObj(String username);
	public Customer getCustomerObj(Customer customerObj);

	public Customer getCustomerObjById(int cid);

	public List<Deals> getAllDealsObj();

	public void persistCustomerObj(Deals dealObj,int customerId);
}
