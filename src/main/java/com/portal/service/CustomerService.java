package com.portal.service;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import com.portal.entity.Customer;
import com.portal.entity.Deals;
import com.portal.entity.Query;

public interface CustomerService {
	
	public void saveCustomerObj(Customer customerobj);

	public Customer login(Customer customerObj);
			
	public Customer getCustomerObj(Customer customerObj);

	public Customer getCustomerObjById(int cid);

	public Set<Deals> getAllDealsObj();

	public void saveCustomerQuery(int customerID, int transporterId, int dealId, Query queryObj);

	public List<Query> getCustomerQueryListById(int customerID);

	public Set<Deals> getAllDealsObj(String fromCity,String toCity, Date fromDate,Date toDate);

	public Set<Deals> getAllDealsObj(String fromCity,String toCity);
	
	public Set<Deals> getAllDealsObj(Date fromDate,Date toDate);
	
	public Set<Deals> getAllDealsObj(String fromCity);
	
	public Set<Deals> getAllDealsObj(Date fromDate);

}
