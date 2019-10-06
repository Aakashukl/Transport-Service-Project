package com.portal.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Vehicle {

	@Id
	@GeneratedValue(generator = "system-increment")
	@GenericGenerator(name = "system-increment", strategy = "increment")
	private int vehicleId;
	private String vehicleType;
	private String vehicleName;
	private String vehicleModel;
	private String vehicleRegistrationNumber;
	private String vehicleRegistrationCertificatePath;
	private String vehicleInsurancePaperPath;
	private String vehicleFitnessCertificatePath;
	private String vehicleDriverDrivingLicencePath;
	
	private String vehicleApproval;
	
	@ManyToOne()
	private Transporter transporter;
	
	@OneToMany(mappedBy="vehicle",fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.PERSIST)
	private List<Deals> transporterDeals;
	
	
	public int getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	public String getVehicleName() {
		return vehicleName;
	}
	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}
	public String getVehicleModel() {
		return vehicleModel;
	}
	public void setVehicleModel(String vehicleModel) {
		this.vehicleModel = vehicleModel;
	}
	public String getVehicleRegistrationNumber() {
		return vehicleRegistrationNumber;
	}
	public void setVehicleRegistrationNumber(String vehicleRegistrationNumber) {
		this.vehicleRegistrationNumber = vehicleRegistrationNumber;
	}
	public String getVehicleRegistrationCertificatePath() {
		return vehicleRegistrationCertificatePath;
	}
	public void setVehicleRegistrationCertificatePath(String vehicleRegistrationCertificatePath) {
		this.vehicleRegistrationCertificatePath = vehicleRegistrationCertificatePath;
	}
	public String getVehicleInsurancePaperPath() {
		return vehicleInsurancePaperPath;
	}
	public void setVehicleInsurancePaperPath(String vehicleInsurancePaperPath) {
		this.vehicleInsurancePaperPath = vehicleInsurancePaperPath;
	}
	public String getVehicleFitnessCertificatePath() {
		return vehicleFitnessCertificatePath;
	}
	public void setVehicleFitnessCertificatePath(String vehicleFitnessCertificatePath) {
		this.vehicleFitnessCertificatePath = vehicleFitnessCertificatePath;
	}

	public String getVehicleDriverDrivingLicencePath() {
		return vehicleDriverDrivingLicencePath;
	}
	public void setVehicleDriverDrivingLicencePath(String vehicleDriverDrivingLicencePath) {
		this.vehicleDriverDrivingLicencePath = vehicleDriverDrivingLicencePath;
	}
	public String getVehicleApproval() {
		return vehicleApproval;
	}
	public void setVehicleApproval(String vehicleApproval) {
		this.vehicleApproval = vehicleApproval;
	}
	public Transporter getTransporter() {
		return transporter;
	}
	public void setTransporter(Transporter transporter) {
		this.transporter = transporter;
	}
	
	public List<Deals> getTransporterDeals() {
		return transporterDeals;
	}
	public void setTransporterDeals(List<Deals> transporterDeals) {
		this.transporterDeals = transporterDeals;
	}
	public Vehicle(int vehicleId, String vehicleType, String vehicleName, String vehicleModel,
			String vehicleRegistrationNumber, String vehicleRegistrationCertificatePath,
			String vehicleInsurancePaperPath, String vehicleFitnessCertificatePath,
			String vehicleDriverDrivingLicencePath, String vehicleApproval, Transporter transporter,
			List<Deals> transporterDeals) {
		super();
		this.vehicleId = vehicleId;
		this.vehicleType = vehicleType;
		this.vehicleName = vehicleName;
		this.vehicleModel = vehicleModel;
		this.vehicleRegistrationNumber = vehicleRegistrationNumber;
		this.vehicleRegistrationCertificatePath = vehicleRegistrationCertificatePath;
		this.vehicleInsurancePaperPath = vehicleInsurancePaperPath;
		this.vehicleFitnessCertificatePath = vehicleFitnessCertificatePath;
		this.vehicleDriverDrivingLicencePath = vehicleDriverDrivingLicencePath;
		this.vehicleApproval = vehicleApproval;
		this.transporter = transporter;
		this.transporterDeals = transporterDeals;
	}

	public Vehicle(int vehicleId, String vehicleType, String vehicleName, String vehicleModel,
			String vehicleRegistrationNumber, String vehicleRegistrationCertificatePath,
			String vehicleInsurancePaperPath, String vehicleFitnessCertificatePath,
			String vehicleDriverDrivingLicencePath, String vehicleApproval) {
		super();
		this.vehicleId = vehicleId;
		this.vehicleType = vehicleType;
		this.vehicleName = vehicleName;
		this.vehicleModel = vehicleModel;
		this.vehicleRegistrationNumber = vehicleRegistrationNumber;
		this.vehicleRegistrationCertificatePath = vehicleRegistrationCertificatePath;
		this.vehicleInsurancePaperPath = vehicleInsurancePaperPath;
		this.vehicleFitnessCertificatePath = vehicleFitnessCertificatePath;
		this.vehicleDriverDrivingLicencePath = vehicleDriverDrivingLicencePath;
		this.vehicleApproval = vehicleApproval;
	}

	@Override
	public String toString() {
		return "Vehicle [vehicleId=" + vehicleId + ", vehicleType=" + vehicleType + ", vehicleName=" + vehicleName
				+ ", vehicleModel=" + vehicleModel + ", vehicleRegistrationNumber=" + vehicleRegistrationNumber
				+ ", vehicleRegistrationCertificatePath=" + vehicleRegistrationCertificatePath
				+ ", vehicleInsurancePaperPath=" + vehicleInsurancePaperPath + ", vehicleFitnessCertificatePath="
				+ vehicleFitnessCertificatePath + ", vehicleDriverDrivingLicencePath=" + vehicleDriverDrivingLicencePath
				+ ", vehicleApproval=" + vehicleApproval + "]";
	}
	public Vehicle(int vehicleId) {
		super();
		this.vehicleId = vehicleId;
	}
	public Vehicle() {
		super();
	}
	public Vehicle(List<Deals> transporterDeals) {
		super();
		this.transporterDeals = transporterDeals;
	}
	public Vehicle(Transporter transporter) {
		super();
		this.transporter = transporter;
	}
	
	
}
