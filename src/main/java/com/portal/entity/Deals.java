package com.portal.entity;


import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
public class Deals {

	@Id
	@GeneratedValue(generator = "system-increment")
	@GenericGenerator(name = "system-increment", strategy = "increment")
	private int dealId;

	private String startPointCityName;
	private String endPointCityName;

	private Date startPointDate;
	private Date endPointDate;

	private String startPointTime;
	private String endPointTime;

	private String dealPrice;

	@ManyToOne
	private Vehicle vehicle;

	@ManyToOne
	private Transporter transporter;

	// @LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany(mappedBy = "Deals", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private Set<Customer> customer = new HashSet<Customer>();

	@OneToMany(mappedBy = "deals", orphanRemoval = true, cascade = CascadeType.PERSIST)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Query> query;

	private String dealActivation;
	private int dealReview;
	private int numberOfBooking;

	public int getNumberOfBooking() {
		return numberOfBooking;
	}

	public void setNumberOfBooking(int numberOfBooking) {
		this.numberOfBooking = numberOfBooking;
	}

	public int getDealId() {
		return dealId;
	}

	public void setDealId(int dealId) {
		this.dealId = dealId;
	}

	public List<Query> getQuery() {
		return query;
	}

	public void setQuery(List<Query> query) {
		this.query = query;
	}

	public String getStartPointCityName() {
		return startPointCityName;
	}

	public void setStartPointCityName(String startPointCityName) {
		this.startPointCityName = startPointCityName;
	}

	public String getEndPointCityName() {
		return endPointCityName;
	}

	public void setEndPointCityName(String endPointCityName) {
		this.endPointCityName = endPointCityName;
	}

	public Date getStartPointDate() {
		return startPointDate;
	}

	public void setStartPointDate(Date startPointDate) {
		this.startPointDate = startPointDate;
	}

	public Date getEndPointDate() {
		return endPointDate;
	}

	public void setEndPointDate(Date endPointDate) {
		this.endPointDate = endPointDate;
	}

	public String getStartPointTime() {
		return startPointTime;
	}

	public void setStartPointTime(String startPointTime) {
		this.startPointTime = startPointTime;
	}

	public String getEndPointTime() {
		return endPointTime;
	}

	public void setEndPointTime(String endPointTime) {
		this.endPointTime = endPointTime;
	}

	public String getDealPrice() {
		return dealPrice;
	}

	public void setDealPrice(String dealPrice) {
		this.dealPrice = dealPrice;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public Transporter getTransporter() {
		return transporter;
	}

	public void setTransporter(Transporter transporter) {
		this.transporter = transporter;
	}

	public Deals(Vehicle vehicle) {
		super();
		this.vehicle = vehicle;
	}

	public Set<Customer> getCustomer() {
		return customer;
	}

	public void setCustomer(Set<Customer> customer) {
		this.customer = customer;
	}

	public String getDealActivation() {
		return dealActivation;
	}

	public void setDealActivation(String dealActivation) {
		this.dealActivation = dealActivation;
	}

	public int getDealReview() {
		return dealReview;
	}

	public void setDealReview(int dealReview) {
		this.dealReview = dealReview;
	}

	public Deals(int dealId) {
		super();
		this.dealId = dealId;
	}

	public Deals() {
		super();
	}

	@Override
	public String toString() {
		return "Deals [dealId=" + dealId + ", startPointCityName=" + startPointCityName + ", endPointCityName="
				+ endPointCityName + ", startPointDate=" + startPointDate + ", endPointDate=" + endPointDate
				+ ", startPointTime=" + startPointTime + ", endPointTime=" + endPointTime + ", dealPrice=" + dealPrice
				+ ", vehicle=" + vehicle + ", transporter=" + transporter + ", customer=" + customer + ", query="
				+ query + ", dealActivation=" + dealActivation + ", dealReview=" + dealReview + ", numberOfBooking="
				+ numberOfBooking + "]";
	}

}
