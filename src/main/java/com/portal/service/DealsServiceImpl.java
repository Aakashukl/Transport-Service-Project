package com.portal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.portal.dao.CustomerDao;
import com.portal.dao.DealsDao;
import com.portal.dao.TransporterDao;
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

	@Autowired
	private CustomerService customerService;

	@Autowired
	private TransporterDao transporterDao;
	
	@Autowired
	JavaMailSender mailSender;

	// -----------------------get Vehicle List By TransporterID---------------
	public List<Vehicle> getVehicleListByTransporterID(int transporterID) {
		List<Vehicle> vehicleListObj = transpoterService.getTransporterObjByID(transporterID).getVehicle();
		// List<Vehicle> vehicleListObj = dealsDao.getApprovedDealsByTransporterId
		return vehicleListObj;
	}

//--------------------get Approved Deals By Transporter Id-------------------
	public List<Vehicle> getApprovedDealsByTransporterId(int transporterID) {
		List<Vehicle> approvedVehicleList = dealsDao.getApprovedDealsByTransporterId(transporterID);
		return approvedVehicleList;
	}

	// -----------------Send mail to ALL Users------------

	public void sendEmail(String [] toAllCustomer, String subject, String message) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(toAllCustomer);
		mailMessage.setSubject(subject);
		mailMessage.setText(message);
		mailSender.send(mailMessage);
	}

//-----------------------save Deal--------------------------------
	public Deals saveDeal(Deals deals, int vehicleIdd, int transporterid) {
		deals.setVehicle(new Vehicle(vehicleIdd));
		deals.setTransporter(new Transporter(transporterid));
		deals.setDealActivation("Activated");
		Deals dealsObj = dealsDao.saveDeal(deals);
		String[] allCustomerEmail =  customerDao.getAllCustomerEmail().toArray(new String[customerDao.getAllCustomerEmail().size()]);
		String msg = "From "+deals.getStartPointCityName()+" To "+deals.getEndPointCityName()+" New Transport Deal.";
		sendEmail( allCustomerEmail,"New Deal Post", msg);
		return dealsObj;
	}

//-------------get Deal List By Vehicle ID List---------------------- 
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

//--------------------------Deal Delete---------------------------
	@Override
	public void dealDelete(int dealId) {
		dealsDao.dealDeal(dealId);
	}

//--------------------------- Setting deals--------------------------------
	public void setDealsRating(int customerID, int dealId, int rating) {
		Deals dealObj = dealsDao.getDealObjById(dealId);
		int current_rating = dealObj.getDealReview();
		int numberOfBooking = dealObj.getNumberOfBooking();
		if (numberOfBooking == 0) {
			dealObj.setDealReview(rating);
		} else {
			int newRating = (current_rating * numberOfBooking + rating) / (numberOfBooking + 1);
			dealObj.setDealReview(newRating);
		}
		dealObj.setNumberOfBooking(numberOfBooking + 1);
		customerDao.persistCustomerObj(dealObj, customerID);
		dealsDao.saveDeal(dealObj);
		System.out.println("Ho gya");
		// -----Calling Function For Updating Transporter Review Table--------------
		updateReviewColumnOfTransporter(dealObj);
	}
//---------------Updating Transporter Rating Column in Transporter Table--------------

	public void updateReviewColumnOfTransporter(Deals dealObj) {
		int transporterId = dealObj.getTransporter().getTransporterId();
		Transporter transporterObj = transpoterService.getTransporterObjByID(transporterId);
		int singleTransHasDeals = transporterObj.getDeals().size();
		int totalDealsRating = transporterObj.getDeals().stream().mapToInt(o -> o.getDealReview()).sum()
				/ singleTransHasDeals;
		transporterObj.setTransporterRating(totalDealsRating);
		transporterDao.saveTransporterObj(transporterObj);

		System.out.println(totalDealsRating);
	}
}
