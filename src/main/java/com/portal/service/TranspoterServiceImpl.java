package com.portal.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.portal.dao.TransporterDao;
import com.portal.entity.Deals;
import com.portal.entity.Query;
import com.portal.entity.Transporter;
import com.portal.entity.Vehicle;

@Service
public class TranspoterServiceImpl implements TranspoterService{
	
	@Autowired
	private TransporterDao transporterDao;
	
	@Autowired
	JavaMailSender mailSender;
	
	//------------Sending Mail Code---------------
	
	public void sendEmail(String to, String subject, String message) {
		SimpleMailMessage mailMessage=new SimpleMailMessage();
		mailMessage.setTo(to);
		mailMessage.setSubject(subject);
		mailMessage.setText(message);
		mailSender.send(mailMessage);
	}
	
	public static String hashPasswordConverter(String passwordPlainText) {
		String salt = BCrypt.gensalt(5); 
		String hashPassword = BCrypt.hashpw(passwordPlainText, salt);
		return hashPassword;
	}
	
	public static Boolean hashPasswordCheck(String loggerPassword, String passwordFromDatabase) {
 
		boolean hashPassword = BCrypt.checkpw(loggerPassword, passwordFromDatabase);
		return hashPassword;
	}
	
	public void saveTranspoterObj(Transporter transporterObj, MultipartFile panCard) throws Exception {
		
		FileOutputStream fos;
		try {
			String path = "D://New SSI Workspace//AkashTransportDealPortal//src//main//webapp//WEB-INF//Data/pan"+panCard.getName()+".pdf";
			fos = new FileOutputStream(path);
			byte[] byt = panCard.getBytes();
			fos.write(byt);
			transporterObj.setTransporterPANPath(path);
			
			String password = transporterObj.getTransporterPassword();
			String hashPassword = hashPasswordConverter(password);
			transporterObj.setTransporterPassword(hashPassword);
			transporterObj.setTransporterValidation("0");
			transporterObj.setTransporterRating(5);
			transporterDao.saveTransporterObj(transporterObj);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}

	//----------- Get Transporter all Data ----------------------
	
	public Transporter getTransporterObj(Transporter transporterObj) {
		Transporter transporterObject = transporterDao.getTransporterObj(transporterObj);
		return transporterObject;
	}

	//----------------Login--------------------------------------------
	public Transporter login(Transporter transporterObj) {
		Transporter transporterObject = getTransporterObj(transporterObj);
		if(transporterObject != null && transporterObject.getTransporterValidation().equals("1") && hashPasswordCheck(transporterObj.getTransporterPassword(), transporterObject.getTransporterPassword())) {
			return transporterObject;
		}
		return transporterObj;
	}
	//hashPasswordConverter(transporterObj.getTransporterPassword()).equals(transporterObject.getTransporterPassword())

	
	//-----------------Save Vehicle Data --------------------------
	public void saveVehicleObj(Vehicle vehicleObj, MultipartFile RegistrationCertificate, MultipartFile InsurancePaper,
			MultipartFile FitnessCertificate, MultipartFile DriverDrivingLicence,int transporterID) throws Exception {
		
		FileOutputStream fos;
		try {
			// RegistrationCertificate
			String pathRC = "D://New SSI Workspace//AkashTransportDealPortal//src//main//webapp//WEB-INF//Data/RegistrationCertificate"+vehicleObj.getVehicleRegistrationNumber()+".pdf";
			fos = new FileOutputStream(pathRC);
			byte[] bytRC = RegistrationCertificate.getBytes();
			fos.write(bytRC);
			vehicleObj.setVehicleRegistrationCertificatePath(pathRC);

			// InsurancePaper
			String pathIP = "D://New SSI Workspace//AkashTransportDealPortal//src//main//webapp//WEB-INF//Data/InsurancePaper"+vehicleObj.getVehicleRegistrationNumber()+".pdf";
			fos = new FileOutputStream(pathIP);
			byte[] bytIP = RegistrationCertificate.getBytes();
			fos.write(bytIP);
			vehicleObj.setVehicleInsurancePaperPath(pathIP);
			
			// FitnessCertificate
			String pathFC = "D://New SSI Workspace//AkashTransportDealPortal//src//main//webapp//WEB-INF//Data/FitnessCertificate"+vehicleObj.getVehicleRegistrationNumber()+".pdf";
			fos = new FileOutputStream(pathFC);
			byte[] bytFC = RegistrationCertificate.getBytes();
			fos.write(bytFC);
			vehicleObj.setVehicleFitnessCertificatePath(pathFC);
			
			// DriverDrivingLicence
			String pathDDL = "D://New SSI Workspace//AkashTransportDealPortal//src//main//webapp//WEB-INF//Data/DriverDrivingLicence_"+vehicleObj.getVehicleModel()+"_"+vehicleObj.getVehicleRegistrationNumber()+".pdf";
			fos = new FileOutputStream(pathDDL);
			byte[] bytDDL = RegistrationCertificate.getBytes();
			fos.write(bytDDL);
			vehicleObj.setVehicleDriverDrivingLicencePath(pathDDL);
			
			//Set Transporter Object to vehicle transporter column by creating new object 
			vehicleObj.setTransporterVehicle(new Transporter(transporterID));
			
			//Setting Flag in vehicle approval 
			vehicleObj.setVehicleApproval("0");
			
			//Completed Now save all uploaded file location in database
			transporterDao.saveVehicleObj(vehicleObj);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public List<Transporter> getListOfALLTransporter() {
		return transporterDao.getListOfALLTransporter();
	}
	
	public List<Vehicle> getListOfALLVehicle() {
		return transporterDao.getListOfALLVehicle();
	}
	
	public Transporter getTransporterObjByID(int transporterId) {
		Transporter transporterObj =transporterDao.getTransporterObjByID(transporterId);
		return transporterObj;
	}

	public void approveRecordOfTransporter(int transporterId) {
		Transporter transporterObj = getTransporterObjByID(transporterId);
		transporterObj.setTransporterValidation("1");
		//System.out.println("Trying to call updateTransportRecord method");
		transporterDao.updateTransportRecord(transporterObj);
		sendEmail(transporterObj.getTransporterEmail(), "Transporter Approved", "Transporter Approved Ho Gya hai Aab Aap Ja k Login Kr Sakte Hai");

	}
	public void approveRecordOfTransporterVehicle(int vehicleId) {
		Vehicle vehicleObj = getVehicleObjById(vehicleId);
		vehicleObj.setVehicleApproval("1");
		transporterDao.updateVehicleRecord(vehicleObj);
		sendEmail(vehicleObj.getTransporterVehicle().getTransporterEmail(), "Vehicle Approved", "Vehicle Add Ho Gya hai Aab Aap Ja k Deal Post Kr Sakte Hai");
		//System.out.println(vehicleObj.getTransporter().getTransporterEmail()+"@@@@@@@@@@@@@@@@");
	}

	private Vehicle getVehicleObjById(int vehicleId) {
		Vehicle vehicleObj = transporterDao.getVehicleObjById(vehicleId);
		return vehicleObj;
	}

	public void rejectRecordOfTransporter(int transporterId) {
		Transporter transporterObj = getTransporterObjByID(transporterId);
		sendEmail(transporterDao.getTransporterObjByID(transporterId).getTransporterEmail(), "Transporter Rejected", "Transporter Reject Ho Gya hai");
		new File(transporterObj.getTransporterPANPath()).delete();
		transporterDao.deleteTransportRecord(transporterObj);

	}

	public void rejectRecordOfTransporterVehicle(int vehicleId) {
		Vehicle vehicleObj = getVehicleObjById(vehicleId);
		sendEmail(vehicleObj.getTransporterVehicle().getTransporterEmail(), "Vehicle Rejected", "Vehicle Reject Ho Gyi hai ");
		new File(vehicleObj.getVehicleDriverDrivingLicencePath()).delete();
		new File(vehicleObj.getVehicleFitnessCertificatePath()).delete();
		new File(vehicleObj.getVehicleInsurancePaperPath()).delete();
		new File(vehicleObj.getVehicleRegistrationCertificatePath()).delete();
		transporterDao.deleteVehicleRecord(vehicleObj);
	}
	
	

	public void openFile(String openFile, HttpServletResponse response)throws Exception {
		File file = new File(openFile);
		FileInputStream fis = new FileInputStream(file);
		byte[] b = new byte[fis.available()];
		fis.read(b);
		ServletOutputStream sos = response.getOutputStream();
		sos.write(b);
		
	}

	@Override
	public List<Deals> getDealsofTransporter(int transporterId) {
		List<Deals> transporterDeals = transporterDao.getDealsofTransporter(transporterId);
		return transporterDeals;
	}

	//-----------------Customer ALL Query------------------------
	@Override
	public List<Query> getCustomerAllQueryList(int transporterId) {
		List<Query> listOfAllCustomerQuery = transporterDao.getCustomerAllQueryList(transporterId);
		return listOfAllCustomerQuery;
	}

	@Override
	public void saveResponseOfTransporter(int queryId, String transporterResponce) {
		transporterDao.saveResponseOfTransporter(queryId,transporterResponce);
		
	}

	

	
	
	
}
