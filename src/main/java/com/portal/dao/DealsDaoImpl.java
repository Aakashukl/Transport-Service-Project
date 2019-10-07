package com.portal.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.portal.entity.Deals;
import com.portal.entity.Transporter;
import com.portal.entity.Vehicle;

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

	public void dealDeal(int dealId) {
		session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Deals dealsObj = session.get(Deals.class, dealId);
		session.delete(dealsObj);
		transaction.commit();
		session.close();
	}


	public List<Vehicle> getApprovedDealsByTransporterId(int transporterID) {
		session = sessionFactory.openSession();
		Criteria criteria =session.createCriteria(Vehicle.class);
		Criterion criterion1 = Restrictions.eq("transporterVehicle.transporterId", transporterID);
		Criterion criterion2 = Restrictions.eq("vehicleApproval", "1");
		LogicalExpression andExp = Restrictions.and(criterion1,criterion2);
		criteria.add(andExp);
		List<Vehicle> approvedVehicleList = criteria.list(); 
		return approvedVehicleList;
		
	}


}
