package com.portal.service;

public interface AdminService {
	
	public Boolean checkAdminUsernamePassword(String username, String password);

	public void dealDelete(int dealId, String reason);
}
