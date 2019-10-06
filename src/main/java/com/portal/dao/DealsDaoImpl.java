package com.portal.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.portal.entity.Deals;

@Repository
public class DealsDaoImpl implements DealsDao {

	@Autowired
	private SessionFactory sessionFactory;
	private Session session;
	
	public Deals saveDeal(Deals deals) {

		session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(deals);
		transaction.commit();
		session.close();

		return deals;
	}

	public Deals getDealObjById(int dealId) {
		session =sessionFactory.openSession();
		Deals dealsObj = session.get(Deals.class, dealId);
		session.close();
		return dealsObj;
	}


}
