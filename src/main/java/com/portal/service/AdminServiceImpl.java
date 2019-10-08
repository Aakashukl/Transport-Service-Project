package com.portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portal.dao.AdminDao;
import com.portal.entity.Transporter;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private TranspoterService transpoterService;
	
	@Autowired
	private DealsService dealsService;

	public Boolean checkAdminUsernamePassword(String username, String password) {
		if(username.equals("admin") && password.equals("admin")) {
			return true;
		}
		else
			return false;
	}


	public void dealDelete(int dealId, String reason) {
		Transporter transporterObj = dealsService.getDealObjById(dealId).getTransporter();
		dealsService.dealDelete(dealId);
		transpoterService.sendEmail(transporterObj.getTransporterEmail(), "Deal Deleted", reason);
	}
}
