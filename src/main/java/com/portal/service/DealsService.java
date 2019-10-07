package com.portal.service;

import java.util.List;

import com.portal.entity.Deals;
import com.portal.entity.Vehicle;

public interface DealsService {

	Object getVehicleListByTransporterID(int transporterID);

	//Deals saveDeal(Deals deals, int vehicleIdd);

	List<Deals> getDealListByVehicleIDList(int transporterID);

	Deals saveDeal(Deals deals, int vehicleIdd, int transporterID);

	void dealActivateAndDeactivate(int dealId, String string);

	Deals getDealObjById(int dealId);

	void setDealsRating(int customerID, int dealId, int rating);

	void dealDelete(int dealId);

	List<Vehicle> getApprovedDealsByTransporterId(int transporterID);

}
