package com.portal.service;

import java.io.FileNotFoundException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.portal.entity.Deals;
import com.portal.entity.Query;
import com.portal.entity.Transporter;
import com.portal.entity.Vehicle;

public interface TranspoterService {
	
	public void saveTranspoterObj(Transporter transporterObj, MultipartFile panCard) throws Exception;
	
	public Transporter getTransporterObj(Transporter transporterObj);
	
	public Transporter login(Transporter transporterObj);
	
	public void saveVehicleObj(Vehicle vehicleObj, MultipartFile RegistrationCertificate,
			MultipartFile InsurancePaper, MultipartFile FitnessCertificate, MultipartFile DriverDrivingLicence, int transporterID) throws Exception;

	public List<Transporter> getListOfALLTransporter();

	public Transporter getTransporterObjByID(int transporterId);
	
	public void approveRecordOfTransporter(int transporterId);

	public void rejectRecordOfTransporter(int transporterId);

	public List<Vehicle> getListOfALLVehicle();

	public void approveRecordOfTransporterVehicle(int vehicleId);

	public void rejectRecordOfTransporterVehicle(int vehicleId);

	public void openFile(String openFile, HttpServletResponse response) throws FileNotFoundException, Exception;

	public List<Deals> getDealsofTransporter(int transporterId);
	
	public void sendEmail(String to, String subject, String message);

	public List<Query> getCustomerAllQueryList(int transporterId);

	public void saveResponseOfTransporter(int queryId, String transporterResponce);

	public List<Vehicle> getVehicleListByTransporterId(int transporterID);

	public Vehicle getVehicleById(int vehicleId);

	
	
}
