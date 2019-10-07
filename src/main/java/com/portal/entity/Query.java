package com.portal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Query {
	@Id
	@GeneratedValue(generator = "system-increment")
	@GenericGenerator(name = "system-increment", strategy = "increment")
	private int queryId;
	
	@ManyToOne
	private Customer customer;
	
	@ManyToOne
	private Transporter transporter;
	
	@ManyToOne
	private Deals deals;
	
	@Column(length = 500)
	private String customerQuery;
	@Column(length = 500)
	private String transporterResponce;
	
	public int getQueryId() {
		return queryId;
	}
	public void setQueryId(int queryId) {
		this.queryId = queryId;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Transporter getTransporter() {
		return transporter;
	}
	public void setTransporter(Transporter transporter) {
		this.transporter = transporter;
	}
	public Deals getDeals() {
		return deals;
	}
	public void setDeals(Deals deals) {
		this.deals = deals;
	}
	public String getCustomerQuery() {
		return customerQuery;
	}
	public void setCustomerQuery(String customerQuery) {
		this.customerQuery = customerQuery;
	}
	public String getTransporterResponce() {
		return transporterResponce;
	}
	public void setTransporterResponce(String transporterResponce) {
		this.transporterResponce = transporterResponce;
	}
	public Query(int queryId) {
		super();
		this.queryId = queryId;
	}
	public Query(Customer customer) {
		super();
		this.customer = customer;
	}
	public Query(Transporter transporter) {
		super();
		this.transporter = transporter;
	}
	public Query(Deals deals) {
		super();
		this.deals = deals;
	}
	public Query() {
		super();
	} 
	
	
	
}
