package com.portal.dao;

import com.portal.entity.Deals;

public interface DealsDao {

	Deals saveDeal(Deals deals);

	Deals getDealObjById(int dealId);

	void dealDeal(int dealId);



}
