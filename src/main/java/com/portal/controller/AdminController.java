package com.portal.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.portal.entity.Deals;
import com.portal.entity.Transporter;
import com.portal.entity.Vehicle;
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
	
	//--------------show All Transporter with Customer Review-----------
	@RequestMapping(value="showAllTransporterReviews")
	public ModelAndView showAllTransporterReviews() {
		Set<Deals> allDealObj =  customerService.getAllDealsObj();
		
		List<Transporter> allTransporterObj = transpoterService.getListOfALLTransporter();
		
		for(Transporter t:allTransporterObj) {
			int rating = 0;
			int count = 0;
			for(Deals d: t.getDeals()) {
				count++; System.out.println(d.getDealReview()+" Ye Rating hai deals ki");
				rating =(rating + d.getDealReview())/count;
				System.out.println("rating obj :"+ rating + " count :" + count);
			}
			t.setTransporterRating(rating);
			System.out.println(t.getTransporterRating()+"AA TOH RHI HAI");
		}
		
		ModelAndView modelAndView = new ModelAndView("admin/showAllTransporterReviews");
		modelAndView.addObject("allTransporterObj", allTransporterObj);
		return modelAndView;
	}
	
	//--------------Home Admin link--------------------------------
	
		@RequestMapping(value = "HomeAdmin")
		public String homeAdmin() { 
			return "HomeAdmin";
		} 
	//---------------------------------------------------------------------------------
	@RequestMapping(value = "transporterRequestCheck")
	public ModelAndView transporterRequestCheck() {
		ModelAndView modelAndView = new ModelAndView("showAllTransporterRequest");
		List<Transporter> transporterListObj = transpoterService.getListOfALLTransporter();
		modelAndView.addObject("transporterListObj", transporterListObj);
		return modelAndView;
	}

	@RequestMapping(value = "transporterVehicleRequestCheck")
	public ModelAndView transporterVehicleRequestCheck() {
		ModelAndView modelAndView = new ModelAndView("showAllTransporterVehicleRequest");
		List<Vehicle> vehicleListObj = transpoterService.getListOfALLVehicle();
		modelAndView.addObject("vehicleListObj", vehicleListObj);
		return modelAndView;
	}

	@RequestMapping(value = "openFile")
	public void openFile(@RequestParam("open") String openFile, HttpServletResponse response) throws Exception {
		transpoterService.openFile(openFile,response);
	
	}

	@RequestMapping(value = "approveRecordOfTransporter")
	public String approveRecordOfTransporter(@RequestParam("transporterId") int transporterId) {
		transpoterService.approveRecordOfTransporter(transporterId);
		return "redirect:/transporterRequestCheck";
	}
	
	@RequestMapping(value = "approveRecordOfTransporterVehicle")
	public String approveRecordOfTransporterVehicle(@RequestParam("vehicleId") int vehicleId) {
		System.out.println("Ye wali vehicle ID Approve hoge: "+vehicleId);
		transpoterService.approveRecordOfTransporterVehicle(vehicleId);
		return "redirect:/transporterVehicleRequestCheck";
	}
	
	@RequestMapping(value = "deleteRecordOfTransporter")
	public String deleteRecordOfTransporter(@RequestParam("transporterId") int transporterId) {
		transpoterService.rejectRecordOfTransporter(transporterId);
		return "redirect:/transporterRequestCheck";
	}
	
	@RequestMapping(value ="deleteRecordOfTransporterVehicle")
	public String deleteRecordOfTransporterVehicle(@RequestParam("vehicleId") int vehicleId) {
		transpoterService.rejectRecordOfTransporterVehicle(vehicleId);
		return "redirect:/transporterVehicleRequestCheck";
	}
}
