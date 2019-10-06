package com.portal.service;

import java.util.List;

import com.portal.entity.Deals;

public interface DealsService {

	Object getVehicleListByTransporterID(int transporterID);

	//Deals saveDeal(Deals deals, int vehicleIdd);

	List<Deals> getDealListByVehicleIDList(int transporterID);

	Deals saveDeal(Deals deals, int vehicleIdd, int transporterID);

	void dealActivateAndDeactivate(int dealId, String string);

	Deals getDealObjById(int dealId);

	void setDealsRating(int customerID, int dealId, int rating);

}
