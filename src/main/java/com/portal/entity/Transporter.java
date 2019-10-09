package com.portal.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.Email;

@Entity
public class Transporter {
	
	@Id
	@GeneratedValue(generator = "system-increment")
	@GenericGenerator(name = "system-increment", strategy = "increment")
	private int transporterId;
	@Column(unique = true)
	
	@Size(min=5, max=25, message="Your name should be between 5 - 25 characters.")
	private String transporterUsername;
	
	private String transporterPassword;
	
	@Size(min=5, max=25, message="Your name should be between 5 - 25 characters.")
	private String transportName;
	
	@Size(min=5, max=25, message="Your name should be between 5 - 25 characters.")
	private String transporterName;
	
	@Pattern(regexp="(^$|[0-9]{10})")
	private String transporterMobile;
	
	@Email
	@Column(unique = true)
	private String transporterEmail;
	
	private String transporterPANPath;
	
	private String transporterValidation;
	
	@Column(unique = true)
	private String transporterGSTNo;
	
	private int transporterRating;
	
	@OneToMany(mappedBy = "transporter", orphanRemoval = true, cascade = CascadeType.PERSIST)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Deals> deals;
	
	@OneToMany(mappedBy = "transporterVehicle", orphanRemoval = true, cascade = CascadeType.PERSIST)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Vehicle> Vehicle;

	@OneToMany(mappedBy = "transporter", orphanRemoval = true, cascade = CascadeType.PERSIST)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Query> query;
	
	public int getTransporterId() {
		return transporterId;
	}

	public void setTransporterId(int transporterId) {
		this.transporterId = transporterId;
	}

	public String getTransporterUsername() {
		return transporterUsername;
	}

	public void setTransporterUsername(String transporterUsername) {
		this.transporterUsername = transporterUsername;
	}

	public String getTransporterPassword() {
		return transporterPassword;
	}

	public void setTransporterPassword(String transporterPassword) {
		this.transporterPassword = transporterPassword;
	}

	public String getTransportName() {
		return transportName;
	}

	public void setTransportName(String transportName) {
		this.transportName = transportName;
	}

	public String getTransporterName() {
		return transporterName;
	}

	public void setTransporterName(String transporterName) {
		this.transporterName = transporterName;
	}

	public String getTransporterMobile() {
		return transporterMobile;
	}

	public void setTransporterMobile(String transporterMobile) {
		this.transporterMobile = transporterMobile;
	}

	public String getTransporterEmail() {
		return transporterEmail;
	}

	public void setTransporterEmail(String transporterEmail) {
		this.transporterEmail = transporterEmail;
	}

	public String getTransporterPANPath() {
		return transporterPANPath;
	}

	public void setTransporterPANPath(String transporterPANPath) {
		this.transporterPANPath = transporterPANPath;
	}

	public String getTransporterValidation() {
		return transporterValidation;
	}

	public void setTransporterValidation(String transporterValidation) {
		this.transporterValidation = transporterValidation;
	}

	public String getTransporterGSTNo() {
		return transporterGSTNo;
	}

	public void setTransporterGSTNo(String transporterGSTNo) {
		this.transporterGSTNo = transporterGSTNo;
	}

	
	public int getTransporterRating() {
		return transporterRating;
	}

	public void setTransporterRating(int transporterRating) {
		this.transporterRating = transporterRating;
	}

	
	public List<Vehicle> getVehicle() {
		return Vehicle;
	}

	public void setVehicle(List<Vehicle> vehicle) {
		Vehicle = vehicle;
	}

	public List<Deals> getDeals() {
		return deals;
	}

	public void setDeals(List<Deals> deals) {
		this.deals = deals;
	}

	
	public List<Query> getQuery() {
		return query;
	}

	public void setQuery(List<Query> query) {
		this.query = query;
	}

	public Transporter(int transporterId) {
		super();
		this.transporterId = transporterId;
	}

	
	public Transporter(List<com.portal.entity.Vehicle> vehicle) {
		super();
		Vehicle = vehicle;
	}



	public Transporter() {
		super();
	}
	
	
}
