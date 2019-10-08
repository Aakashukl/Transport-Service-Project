package com.portal.dao;

import java.sql.Date;
import java.util.List;

import com.portal.entity.Customer;
import com.portal.entity.Deals;
import com.portal.entity.Query;

public interface CustomerDao {

	public void saveCustomerObj(Customer customerObj);

	// public Customer getCustomerObj(String username);
	public Customer getCustomerObj(Customer customerObj);

	public Customer getCustomerObjById(int cid);

	public List<Deals> getAllDealsObj();

	public void persistCustomerObj(Deals dealObj, int customerId);

	public void saveCustomerQuery(Query queryObj);

	public List<Query> getCustomerQueryListById(int customerID);

	public List<Deals> getAllDealsObj(String fromCity,String toCity, Date fromDate,Date toDate);
	
	public List<Deals> getAllDealsObj(String fromCity,String toCity);
	
	public List<Deals> getAllDealsObj(Date fromDate,Date toDate);
	
	public List<Deals> getAllDealsObj(Date fromDate);
	
	public List<Deals> getAllDealsObj(String fromCity);
}
