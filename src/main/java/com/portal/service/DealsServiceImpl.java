package com.portal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portal.dao.CustomerDao;
import com.portal.dao.DealsDao;
import com.portal.entity.Customer;
import com.portal.entity.Deals;
import com.portal.entity.Transporter;
import com.portal.entity.Vehicle;

@Service
public class DealsServiceImpl implements DealsService {

	@Autowired
	private TranspoterService transpoterService;

	@Autowired
	private DealsDao dealsDao;

	@Autowired
	private CustomerDao customerDao;

	public List<Vehicle> getVehicleListByTransporterID(int transporterID) {

		List<Vehicle> vehicleListObj = transpoterService.getTransporterObjByID(transporterID).getVehicle();
		/*
		 * List<Integer> vehicleIDs = new ArrayList<Integer>(); for (Vehicle veh :
		 * vehicleListObj) { vehicleIDs.add(veh.getVehicleId()); }
		 */

		return vehicleListObj;
	}

	public Deals saveDeal(Deals deals, int vehicleIdd, int transporterid) {
		deals.setVehicle(new Vehicle(vehicleIdd));
		deals.setTransporter(new Transporter(transporterid));
		deals.setDealActivation("Activated");
		Deals dealsObj = dealsDao.saveDeal(deals);
		return dealsObj;
	}

	/*
	 * public List<Deals> getDealListByVehicleIDList(int transporterID) {
	 * List<Deals> allDealListobj = new ArrayList<>();
	 * 
	 * List<Vehicle> vehicleListObj =
	 * transpoterService.getTransporterObjByID(transporterID).getVehicle();
	 * 
	 * //List<Vehicle> dealsObjs = new ArrayList<Vehicle>(); for (Vehicle
	 * vehicleObjs : vehicleListObj) { List<Deals> d =
	 * vehicleObjs.getTransporterDeals(); for(Deals deal:d) {
	 * allDealListobj.add(deal); } }
	 * 
	 * return allDealListobj; }
	 */
	public List<Deals> getDealListByVehicleIDList(int transporterID) {

		List<Deals> dealsListObj = transpoterService.getTransporterObjByID(transporterID).getDeals();

		return dealsListObj;
	}

	// ------------Deal Activate And Deactivate--------------------
	public void dealActivateAndDeactivate(int dealId, String string) {
		Deals dealsObj = dealsDao.getDealObjById(dealId);
		if (string.equals("activatedeal")) {
			dealsObj.setDealActivation("Activated");
		} else if (string.equals("deactivatedeal")) {
			dealsObj.setDealActivation("Deactivated");
		}
		dealsDao.saveDeal(dealsObj);
	}

	public Deals getDealObjById(int dealId) {
		Deals dealObj = dealsDao.getDealObjById(dealId);
		return dealObj;
	}

//--------------------------- Setting deals--------------------------------
	public void setDealsRating(int customerID, int dealId, int rating) {
		Deals dealObj = dealsDao.getDealObjById(dealId);
		int current_rating = dealObj.getDealReview();
		int numberOfBooking = dealObj.getNumberOfBooking() + 1;
		int newRating = (current_rating + rating)/numberOfBooking;
		dealObj.setDealReview(newRating);
		dealObj.setNumberOfBooking(numberOfBooking);
		customerDao.persistCustomerObj(dealObj,customerID);
		dealsDao.saveDeal(dealObj);
		System.out.println("Ho gya");

	}
}
