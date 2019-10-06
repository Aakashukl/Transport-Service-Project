package com.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.portal.entity.Vehicle;
import com.portal.service.TranspoterService;

@Controller
public class VehicleController {
	
	@Autowired
	private TranspoterService transpoterService;
	
	@RequestMapping(value = "TransporterAddVehiclePage")
	public ModelAndView transporterAddVehiclePage() {
		ModelAndView modelAndView = new ModelAndView("TransporterAddVehiclePage");
		Vehicle vehicleObj = new Vehicle();
		modelAndView.addObject("vehicleObj", vehicleObj);
		return modelAndView;
	}

	@RequestMapping(value = "saveNewVehicleProcess")
	public ModelAndView saveNewVehicleProcess(@ModelAttribute("vehicleObj") Vehicle vehicle, 
			@RequestParam("RegistrationCertificatePath") MultipartFile RegistrationCertificate, 
			@RequestParam("InsurancePaperPath") MultipartFile InsurancePaper, 
			@RequestParam("FitnessCertificatePath") MultipartFile FitnessCertificate,
			@RequestParam("DriverDrivingLicencePath") MultipartFile DriverDrivingLicence,
			@RequestParam("transporterID") int transporterID) {
		try {
			transpoterService.saveVehicleObj(vehicle, RegistrationCertificate, InsurancePaper, FitnessCertificate, DriverDrivingLicence, transporterID);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView modelAndView = new ModelAndView("TransporterAddVehiclePage");
		Vehicle vehicleObj = new Vehicle();
		modelAndView.addObject("vehicleObj", vehicleObj);
		return modelAndView;
	}
}
