package com.portal.dao;

import java.util.List;
import java.util.Set;

import javax.swing.text.DefaultEditorKit.CutAction;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.portal.entity.Customer;
import com.portal.entity.Deals;

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
				.createQuery("from Customer where customerUsername='" + customerObj.getCustomerUsername() + "'").list().get(0));

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
		List allDealsListObj= session.createCriteria(Deals.class).list();
		session.close();
		return allDealsListObj;
	}

	public void persistCustomerObj(Deals dealObj,int customerId) {
		session =sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Customer customerObj = session.get(Customer.class, customerId);
		customerObj.getDeals().add(dealObj);
		session.update(customerObj);
		transaction.commit();
		session.close();
	}

}
