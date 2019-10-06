package com.portal.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Email;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Entity
public class Customer {

	@Id
	@GeneratedValue(generator = "system-increment")
	@GenericGenerator(name = "system-increment", strategy = "increment")
	private int customerId;

	private String customerName;
	private String customerMobileNumber;
	@Email
	@Column(unique = true)
	private String cutomerEmail;
	private String customerPincode;
	@Column(unique = true)
	private String customerUsername;
	private String customerPassword;
	private String customerGender;
	private String customerAddress;

	// @LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany( fetch = FetchType.EAGER,cascade = CascadeType.PERSIST) 
	private Set<Deals> Deals = new HashSet<Deals>();

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerMobileNumber() {
		return customerMobileNumber;
	}

	public void setCustomerMobileNumber(String customerMobileNumber) {
		this.customerMobileNumber = customerMobileNumber;
	}

	public String getCutomerEmail() {
		return cutomerEmail;
	}

	public void setCutomerEmail(String cutomerEmail) {
		this.cutomerEmail = cutomerEmail;
	}

	public String getCustomerPincode() {
		return customerPincode;
	}

	public void setCustomerPincode(String customerPincode) {
		this.customerPincode = customerPincode;
	}

	/*
	 * public String getCustomerdateOfBirth() { return customerdateOfBirth; } public
	 * void setCustomerdateOfBirth(String customerdateOfBirth) {
	 * this.customerdateOfBirth = customerdateOfBirth; }
	 */
	public String getCustomerUsername() {
		return customerUsername;
	}

	public void setCustomerUsername(String customerUsername) {
		this.customerUsername = customerUsername;
	}

	public String getCustomerPassword() {
		return customerPassword;
	}

	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}

	public String getCustomerGender() {
		return customerGender;
	}

	public void setCustomerGender(String customerGender) {
		this.customerGender = customerGender;
	}

	public Set<com.portal.entity.Deals> getDeals() {
		return Deals;
	}

	public void setDeals(Set<com.portal.entity.Deals> deals) {
		Deals = deals;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	/*
	 * public String getCustomerCity() { return customerCity; } public void
	 * setCustomerCity(String customerCity) { this.customerCity = customerCity; }
	 * public String getCustomerTypeOfAddress() { return customerTypeOfAddress; }
	 * public void setCustomerTypeOfAddress(String customerTypeOfAddress) {
	 * this.customerTypeOfAddress = customerTypeOfAddress; }
	 */

	public Customer() {
		super();
	}

	public Customer(int customerId, String customerName, String customerMobileNumber, String cutomerEmail,
			String customerPincode, String customerUsername, String customerPassword, String customerGender,
			String customerAddress) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerMobileNumber = customerMobileNumber;
		this.cutomerEmail = cutomerEmail;
		this.customerPincode = customerPincode;
		this.customerUsername = customerUsername;
		this.customerPassword = customerPassword;
		this.customerGender = customerGender;
		this.customerAddress = customerAddress;
	}

	public Customer(String customerUsername) {
		this.customerUsername = customerUsername;
	}

	public Customer(int customerId) {
		super();
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", customerMobileNumber="
				+ customerMobileNumber + ", cutomerEmail=" + cutomerEmail + ", customerPincode=" + customerPincode
				+ ", customerUsername=" + customerUsername + ", customerPassword=" + customerPassword
				+ ", customerGender=" + customerGender + ", customerAddress=" + customerAddress + "]";
	}

}
