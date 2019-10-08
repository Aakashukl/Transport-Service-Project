package com.portal.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.portal.entity.Deals;
import com.portal.entity.Query;
import com.portal.entity.Transporter;
import com.portal.entity.Vehicle;

@Repository
public class TransporterDaoImpl implements TransporterDao {

	@Autowired
	private SessionFactory sessionFactory;
	private Session session;
	// Save Transporter Registration Data

	public void saveTransporterObj(Transporter transporterObj) {
		session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(transporterObj);
		transaction.commit();
		session.close();

	}

	public Transporter getTransporterObj(Transporter transporterObj) {
		session = sessionFactory.openSession();
		Transporter transporterObject = (Transporter) (session
				.createQuery(
						"from Transporter where transporterUsername='" + transporterObj.getTransporterUsername() + "'")
				.list().get(0));
		session.close();
		return transporterObject;
	}

	public void saveVehicleObj(Vehicle vehicleObj) {
		session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(vehicleObj);
		transaction.commit();
		session.close();
		
	}

	public List<Transporter> getListOfALLTransporter() {
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Transporter.class);
		List<Transporter> allTransporterListObj = criteria.list();
		session.close();
		return allTransporterListObj;
	}
	
	public List<Vehicle> getListOfALLVehicle() {
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Vehicle.class);
		List<Vehicle> allVehicleListObj = criteria.list();
		session.close();
		return allVehicleListObj;
	}

	public Transporter getTransporterObjByID(int transporterId) {
		session = sessionFactory.openSession();
		Transporter transporterObj = session.get(Transporter.class, transporterId);
		session.close();
		return transporterObj;
	}
	
	public Vehicle getVehicleObjById(int vehicleId) {
		session = sessionFactory.openSession();
		Vehicle vehicleObj = session.get(Vehicle.class, vehicleId);
		session.close();
		return vehicleObj;
	}
	
	public void updateTransportRecord(Transporter transporterObj) {
		session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.update(transporterObj);
		transaction.commit();
		session.close();
	}
	
	public void updateVehicleRecord(Vehicle vehicleObj) {
		session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.update(vehicleObj);
		transaction.commit();
		session.close();
	}

	public void deleteTransportRecord(Transporter transporterObj) {
		session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.delete(transporterObj);
		transaction.commit();
		session.close();
	}

	public void deleteVehicleRecord(Vehicle vehicleObj) {
		session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.delete(vehicleObj);
		transaction.commit();
		session.close();
	}

	
	public List<Deals> getDealsofTransporter(int transporterId) {
		session = sessionFactory.openSession();
		List<Deals> transporterDeals = session.get(Transporter.class, transporterId).getDeals();
		return transporterDeals;
	}


	public List<Query> getCustomerAllQueryList(int transporterId) {
		session = sessionFactory.openSession();
		List<Query> listOfAllCustomerQuery = session.get(Transporter.class,transporterId).getQuery();
		return listOfAllCustomerQuery;
	}

	public void saveResponseOfTransporter(int queryId, String transporterResponce) {
		session =sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query queryObj = session.get(Query.class, queryId);
		queryObj.setTransporterResponce(transporterResponce);
		session.update(queryObj);
		transaction.commit();
		session.close();
	}


	

	

	

}
