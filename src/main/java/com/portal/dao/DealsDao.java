package com.portal.dao;

import java.util.List;

import com.portal.entity.Deals;
import com.portal.entity.Vehicle;

public interface DealsDao {

	Deals saveDeal(Deals deals);

	Deals getDealObjById(int dealId);

	void dealDeal(int dealId);

	List<Vehicle> getApprovedDealsByTransporterId(int transporterID);



}
