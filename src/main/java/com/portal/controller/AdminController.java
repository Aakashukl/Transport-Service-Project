package com.portal.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.portal.entity.Deals;
import com.portal.entity.Transporter;
import com.portal.entity.Vehicle;
import com.portal.service.AdminService;
import com.portal.service.CustomerService;
import com.portal.service.DealsService;
import com.portal.service.TranspoterService;

@Controller
public class AdminController {

	@Autowired
	private TranspoterService transpoterService;

	@Autowired
	private DealsService dealsService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private AdminService adminService;

	// --------------show All Transporter with Customer Review-----------
	@RequestMapping(value = "showAllTransporterReviews")
	public ModelAndView showAllTransporterReviews(HttpServletRequest request) {
		if (request.getSession().getAttribute("AdminUsername") != null) {
			List<Transporter> allTransporterObj = transpoterService.getListOfALLTransporter();
			ModelAndView modelAndView = new ModelAndView("admin/showAllTransporterReviews");
			modelAndView.addObject("allTransporterObj", allTransporterObj);
			return modelAndView;
		} else {
			ModelAndView modelAndView = new ModelAndView("others/LogoutPage");
			return modelAndView;
		}
	}

	// ---------------Show Transporter ALL Deals-------------------------------
	@RequestMapping(value = "dealsOfTransporter")
	public ModelAndView dealsOfTransporter(@RequestParam("transporterId") int transporterId,
			HttpServletRequest request) {
		if (request.getSession().getAttribute("AdminUsername") != null) {
			List<Deals> transporterDeals = transpoterService.getDealsofTransporter(transporterId);
			ModelAndView modelAndView = new ModelAndView("admin/showTransporterAllDeals");
			modelAndView.addObject("transporterDealsList", transporterDeals);
			return modelAndView;
		} else {
			ModelAndView modelAndView = new ModelAndView("others/LogoutPage");
			return modelAndView;
		}
	}

	// ----------------Delete Deal by Admin----------------------------------
	@RequestMapping(value = "deleteDeal")
	public String deleteDeal(@RequestParam("dealId") int dealId, @RequestParam("transporterId") int transporterId,
			@RequestParam("reason") String reason, HttpServletRequest request) {
		if (request.getSession().getAttribute("AdminUsername") != null) {
			adminService.dealDelete(dealId, reason);
			return "redirect:dealsOfTransporter?transporterId=" + transporterId;
		} else {

			return "others/LogoutPage";
		}
	}

	// --------------Home Admin link--------------------------------

	@RequestMapping(value = "HomeAdmin")
	public ModelAndView homeAdmin(HttpServletRequest request) {
		if (request.getSession().getAttribute("AdminUsername") != null) {
			ModelAndView modelAndView = new ModelAndView("admin/HomeAdmin");
			return modelAndView;
		} else {
			ModelAndView modelAndView = new ModelAndView("others/LogoutPage");
			return modelAndView;
		}
	}

	// ------------------Transporter Request Check-----------------

	@RequestMapping(value = "transporterRequestCheck")
	public ModelAndView transporterRequestCheck(HttpServletRequest request) {
		if (request.getSession().getAttribute("AdminUsername") != null) {
			ModelAndView modelAndView = new ModelAndView("admin/showAllTransporterRequest");
			List<Transporter> transporterListObj = transpoterService.getListOfALLTransporter();
			modelAndView.addObject("transporterListObj", transporterListObj);
			return modelAndView;
		} else {
			ModelAndView modelAndView = new ModelAndView("others/LogoutPage");
			return modelAndView;
		}

	}

	// ----------------transporter Vehicle RequestCheck-------------

	@RequestMapping(value = "transporterVehicleRequestCheck")
	public ModelAndView transporterVehicleRequestCheck(HttpServletRequest request) {
		if (request.getSession().getAttribute("AdminUsername") != null) {
			ModelAndView modelAndView = new ModelAndView("admin/showAllTransporterVehicleRequest");
			List<Vehicle> vehicleListObj = transpoterService.getListOfALLVehicle();
			modelAndView.addObject("vehicleListObj", vehicleListObj);
			return modelAndView;
		} else {
			ModelAndView modelAndView = new ModelAndView("others/LogoutPage");
			return modelAndView;
		}
	}

	// -------------------------Open Uploaded Files----------------------

	@RequestMapping(value = "openFile")
	public void openFile(@RequestParam("open") String openFile, HttpServletResponse response,
			HttpServletRequest request) throws FileNotFoundException, Exception {
		if (request.getSession().getAttribute("AdminUsername") != null) {
			transpoterService.openFile(openFile, response);
		} else
			response.sendRedirect("others/LogoutPage.jsp");

	}

	// -----------------Approve Record Of Transporter--------------------------
	@RequestMapping(value = "approveRecordOfTransporter")
	public String approveRecordOfTransporter(@RequestParam("transporterId") int transporterId,
			HttpServletRequest request) {
		if (request.getSession().getAttribute("AdminUsername") != null) {
			transpoterService.approveRecordOfTransporter(transporterId);
			return "redirect:/transporterRequestCheck";
		} else {
			return "redirect:/HomeAdmin";
		}
	}

	// -----------------Approve Record Of Transporter Vehicle---------------
	@RequestMapping(value = "approveRecordOfTransporterVehicle")
	public String approveRecordOfTransporterVehicle(@RequestParam("vehicleId") int vehicleId,
			HttpServletRequest request) {
		if (request.getSession().getAttribute("AdminUsername") != null) {
			System.out.println("Ye wali vehicle ID Approve hoge: " + vehicleId);
			transpoterService.approveRecordOfTransporterVehicle(vehicleId);
			return "redirect:/transporterVehicleRequestCheck";
		} else {
			return "redirect:/HomeAdmin";
		}
	}

	// -----------------Delete Record Of Transporter----------------------

	@RequestMapping(value = "deleteRecordOfTransporter")
	public String deleteRecordOfTransporter(@RequestParam("transporterId") int transporterId,
			HttpServletRequest request) {
		if (request.getSession().getAttribute("AdminUsername") != null) {
			transpoterService.rejectRecordOfTransporter(transporterId);
			return "redirect:/transporterRequestCheck";
		} else {
			return "redirect:/HomeAdmin";
		}
	}

	// -------------------Delete Record Of Transporter Vehicle---------------
	@RequestMapping(value = "deleteRecordOfTransporterVehicle")
	public String deleteRecordOfTransporterVehicle(@RequestParam("vehicleId") int vehicleId,
			HttpServletRequest request) {
		if (request.getSession().getAttribute("AdminUsername") != null) {
			transpoterService.rejectRecordOfTransporterVehicle(vehicleId);
			return "redirect:/transporterVehicleRequestCheck";
		} else {
			return "redirect:/HomeAdmin";
		}
	}

	/*
	 * //-----------------show Registration Page--------------
	 */
	@RequestMapping("showRegistrationPage")
	public String showRegistrationPage() {
		return "others/RegistrationPage";
	}
	
	// ---------------IOException Exception-----
	@ExceptionHandler(IOException.class)
	public ModelAndView iOException(IOException ex) {
		System.out.println(" parameter is missing");
		ModelAndView modelAndView = new ModelAndView("others/LogoutPage");
		return modelAndView;
	}
	

	// ---------------Missing Servlet Request Parameter Exception-----IOException e
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ModelAndView handleMissingParams(MissingServletRequestParameterException ex) {
		String name = ex.getParameterName();
		System.out.println(name + " parameter is missing");
		ModelAndView modelAndView = new ModelAndView("others/LogoutPage");
		return modelAndView;
	}

}
