package com.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.portal.entity.Vehicle;
import com.portal.service.TranspoterService;

@Controller
public class VehicleController {

	@Autowired
	private TranspoterService transportService;

	// ---------------------Transporter AddVehicle Page-----------------

	@RequestMapping(value = "TransporterAddVehiclePage")
	public ModelAndView transporterAddVehiclePage(HttpServletRequest request) {

		if (request.getSession().getAttribute("TransporterID") != null) {
			ModelAndView modelAndView = new ModelAndView("TransporterAddVehiclePage");
			Vehicle vehicleObj = new Vehicle();
			modelAndView.addObject("vehicleObj", vehicleObj);
			return modelAndView;
		} else {
			ModelAndView modelAndView = new ModelAndView("others/LogoutPage");
			return modelAndView;
		}
	}

	// ----------------------save New Vehicle Process----------------
	@RequestMapping(value = "saveNewVehicleProcess")
	public ModelAndView saveNewVehicleProcess(@ModelAttribute("vehicleObj") Vehicle vehicle,
			@RequestParam("RegistrationCertificatePath") MultipartFile RegistrationCertificate,
			@RequestParam("InsurancePaperPath") MultipartFile InsurancePaper,
			@RequestParam("FitnessCertificatePath") MultipartFile FitnessCertificate,
			@RequestParam("DriverDrivingLicencePath") MultipartFile DriverDrivingLicence,
			@RequestParam("transporterID") int transporterID, HttpServletRequest request) {

		if (request.getSession().getAttribute("TransporterID") != null) {
			try {
				transportService.saveVehicleObj(vehicle, RegistrationCertificate, InsurancePaper, FitnessCertificate,
						DriverDrivingLicence, transporterID);

			} catch (Exception e) {
				e.printStackTrace();
			}
			ModelAndView modelAndView = new ModelAndView("TransporterAddVehiclePage");
			Vehicle vehicleObj = new Vehicle();
			modelAndView.addObject("vehicleObj", vehicleObj);
			return modelAndView;
		} else {
			ModelAndView modelAndView = new ModelAndView("others/LogoutPage");
			return modelAndView;
		}
	}

	// ------------------transporter Update Vehicle Dropdown ---------------------
	@RequestMapping(value = "transporterUpdateVehicledropdown")
	public ModelAndView transporterUpdateVehicledropdown(HttpServletRequest request,
			@SessionAttribute("TransporterID") int transporterID) {

		if (request.getSession().getAttribute("TransporterID") != null) {
			List<Vehicle> vehicleListObj = transportService.getVehicleListByTransporterId(transporterID);
			ModelAndView modelAndView = new ModelAndView("transporter/transporterUpdateVehicledropdown");
			modelAndView.addObject("vehicleListObj", vehicleListObj);
			return modelAndView;

		} else {
			ModelAndView modelAndView = new ModelAndView("others/LogoutPage");
			return modelAndView;
		}

	}

	// -------------------update page Of Vehicle------------------------

	@RequestMapping(value = "showVehicleDetailForUpdation")
	public ModelAndView showVehicleDetailForUpdation(@RequestParam("vehicleIdd") int vehicleId,
			HttpServletRequest request) {

		if (request.getSession().getAttribute("TransporterID") != null) {
			Vehicle vehicleObj = transportService.getVehicleById(vehicleId);
			ModelAndView modelAndView = new ModelAndView("transporter/showVehicleDetailForUpdation");
			modelAndView.addObject("vehicleObj", vehicleObj);
			return modelAndView;
		} else {
			ModelAndView modelAndView = new ModelAndView("others/LogoutPage");
			return modelAndView;
		}
	}

	// ----------------------save Update Vehicle Process----------------
	@RequestMapping(value = "updateVehicleProcess")
	public ModelAndView updateVehicleProcess(@ModelAttribute("vehicleObj") Vehicle vehicle,
			@RequestParam("RegistrationCertificatePath") MultipartFile RegistrationCertificate,
			@RequestParam("InsurancePaperPath") MultipartFile InsurancePaper,
			@RequestParam("FitnessCertificatePath") MultipartFile FitnessCertificate,
			@RequestParam("DriverDrivingLicencePath") MultipartFile DriverDrivingLicence,
			@RequestParam("transporterID") int transporterID, HttpServletRequest request) throws Exception {
		if (request.getSession().getAttribute("TransporterID") != null) {
			transportService.saveVehicleObj(vehicle, RegistrationCertificate, InsurancePaper, FitnessCertificate,
					DriverDrivingLicence, transporterID);
			ModelAndView modelAndView = new ModelAndView("transporter/HomeTransporter");
			return modelAndView;
		} else {
			ModelAndView modelAndView = new ModelAndView("others/LogoutPage");
			return modelAndView;
		}
	}
}
