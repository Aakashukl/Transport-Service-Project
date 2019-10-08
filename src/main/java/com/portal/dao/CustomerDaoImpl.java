package com.portal.dao;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import javax.swing.text.DefaultEditorKit.CutAction;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.portal.entity.Customer;
import com.portal.entity.Deals;
import com.portal.entity.Query;

@Repository
public class CustomerDaoImpl implements CustomerDao {

	@Autowired
	private SessionFactory sessionFactory;
	private Session session;

	// Save Customer Data
	public void saveCustomerObj(Customer customerObj) {
		session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(customerObj);
		transaction.commit();
		session.close();
	}

	public Customer getCustomerObj(Customer customerObj) {
		session = sessionFactory.openSession();

		Customer customerObject = (Customer) (session
				.createQuery("from Customer where customerUsername='" + customerObj.getCustomerUsername() + "'").list()
				.get(0));

		session.close();
		return customerObject;
	}

	public Customer getCustomerObjById(int cid) {
		session = sessionFactory.openSession();
		Customer customerObj = session.get(Customer.class, cid);
		session.close();
		return customerObj;
	}

	public List<Deals> getAllDealsObj() {
		session = sessionFactory.openSession();
		List allDealsListObj = session.createCriteria(Deals.class).list();
		session.close();
		return allDealsListObj;
	}

//-------------------Filters------------------------------------------------
	public List<Deals> getAllDealsObj(String fromCity, String toCity, Date fromDate, Date toDate) {
		System.out.println("fromCity " + fromCity);
		System.out.println("toCity " + toCity);
		System.out.println("fromDate " + fromDate);
		System.out.println("toDate " + toDate);

		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Deals.class);
		Criterion criteriafromCity = Restrictions.eq("startPointCityName", fromCity);
		Criterion criteriaToCity = Restrictions.eq("endPointCityName", toCity);
		Criterion andExp = Restrictions.and(criteriafromCity, criteriaToCity);

		Criterion dfrom = Restrictions.ge("startPointDate", fromDate);
		Criterion dto = Restrictions.le("startPointDate", toDate);
		Criterion between = Restrictions.and(dfrom, dto);

		LogicalExpression bothCityAndDate = Restrictions.and(andExp, between);
		criteria.add(bothCityAndDate);

		List<Deals> filteredDealsList = criteria.list();
		System.out.println(filteredDealsList.toString());
		session.close();
		return filteredDealsList;
	}

//------------------Filter by From City-------------------

	public List<Deals> getAllDealsObj(String fromCity) {
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Deals.class);
		Criterion criteriaFromCity = Restrictions.eq("startPointCityName", fromCity);
		criteria.add(criteriaFromCity);
		List<Deals> filteredDealsList = criteria.list();
		System.out.println(filteredDealsList.toString());
		session.close();
		return filteredDealsList;
	}

//------------Filter by From City To City--------------------

	public List<Deals> getAllDealsObj(String fromCity, String toCity) {
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Deals.class);
		Criterion criteriafromCity = Restrictions.eq("startPointCityName", fromCity);
		Criterion criteriaToCity = Restrictions.eq("endPointCityName", toCity);
		Criterion andExp = Restrictions.and(criteriafromCity, criteriaToCity);

		criteria.add(andExp);

		List<Deals> filteredDealsList = criteria.list();
		System.out.println(filteredDealsList.toString());
		session.close();
		return filteredDealsList;
	}
//------------Filter by From Date To Date--------------------

	public List<Deals> getAllDealsObj(Date fromDate, Date toDate) {
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Deals.class);

		Criterion dfrom = Restrictions.ge("startPointDate", fromDate);
		Criterion dto = Restrictions.le("startPointDate", toDate);
		Criterion between = Restrictions.and(dfrom, dto);

		criteria.add(between);

		List<Deals> filteredDealsList = criteria.list();
		System.out.println(filteredDealsList.toString());
		session.close();
		return filteredDealsList;
	}

//------------Filter by From Date--------------------
	public List<Deals> getAllDealsObj(Date fromDate) {
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Deals.class);

		Criterion dfrom = Restrictions.ge("startPointDate", fromDate);

		criteria.add(dfrom);

		List<Deals> filteredDealsList = criteria.list();
		System.out.println(filteredDealsList.toString());
		session.close();
		return filteredDealsList;
	}

//---------In ManyTOMany Relationship adding deal object For Composite key Generation--------------
	public void persistCustomerObj(Deals dealObj, int customerId) {
		session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Customer customerObj = session.get(Customer.class, customerId);
		customerObj.getDeals().add(dealObj);
		session.update(customerObj);
		transaction.commit();
		session.close();
	}

	public void saveCustomerQuery(Query queryObj) {
		session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(queryObj);
		transaction.commit();
		session.close();
	}

	public List<Query> getCustomerQueryListById(int customerID) {
		session = sessionFactory.openSession();
		List<Query> customerAllQueryList = session.get(Customer.class, customerID).getQuery();
		return customerAllQueryList;
	}

}
