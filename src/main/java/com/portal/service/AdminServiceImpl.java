package com.portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portal.dao.AdminDao;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminDao adminDao;

	public Boolean checkAdminUsernamePassword(String username, String password) {
		if(username.equals("admin") && password.equals("admin")) {
			return true;
		}
		else
			return false;
	}
}
