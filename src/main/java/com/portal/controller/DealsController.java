package com.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.portal.entity.Deals;
import com.portal.entity.Vehicle;
import com.portal.service.DealsService;

@Controller
public class DealsController {

	@Autowired
	private DealsService dealsService;

	// --------------Transporter Home Page-------------------------------
	/* @RequestMapping("HomeTransporter") */
	public ModelAndView homeTransporter(HttpServletRequest request) {

		if (request.getSession().getAttribute("TransporterID") != null) {
			ModelAndView modelAndView = new ModelAndView("transporter/HomeTransporter");
			return modelAndView;
		}

		else {
			ModelAndView modelAndView = new ModelAndView("others/LogoutPage");
			return modelAndView;
		}
	}

	@RequestMapping("showDealPostPageToTransporter")
	public ModelAndView showDealPostPageToTransporter(@SessionAttribute("TransporterID") int transporterID,
			HttpServletRequest request) {

		if (request.getSession().getAttribute("TransporterID") != null) {
			ModelAndView modelAndView = new ModelAndView("showDealPostPageToTransporter");
			// List<Vehicle> vehicleID = (List<Vehicle>)
			// dealsService.getVehicleListByTransporterID(transporterID);
			List<Vehicle> vehicleID = dealsService.getApprovedDealsByTransporterId(transporterID);
			Deals dealsObj = new Deals();
			modelAndView.addObject("vehicleID", vehicleID);
			modelAndView.addObject("dealsObj", dealsObj);
			return modelAndView;
		} else {
			ModelAndView modelAndView = new ModelAndView("others/LogoutPage");
			return modelAndView;
		}
	}

//-------------------Save posted Deal---------------------------
	@RequestMapping("savePostedDeal")
	public String savePostedDeal(@ModelAttribute("dealsObj") Deals deals, @RequestParam("vehicleIdd") int vehicleIdd,
			@SessionAttribute("TransporterID") int transporterID, HttpServletRequest request) {

		if (request.getSession().getAttribute("TransporterID") != null) {
			dealsService.saveDeal(deals, vehicleIdd, transporterID);
			return "redirect:/transporterDealUpdateAndDelete";
		} else
			return "others/LogoutPage";
	}

	/*
	 * @RequestMapping("updatePostedDeal") public ModelAndView updatePostedDeal() {
	 * Deals dealsObj = dealsService.getDealsObjById() ModelAndView modelAndView =
	 * new ModelAndView("updatePostedDeal"); return modelAndView; }
	 */

	// ---------Show Transporter Specific Deals---------------------------------

	@RequestMapping(value = "transporterDealUpdateAndDelete")
	public ModelAndView transporterDealUpdateAndDelete(@SessionAttribute("TransporterID") int transporterID,
			HttpServletRequest request) {

		if (request.getSession().getAttribute("TransporterID") != null) {
			List<Deals> dealsListObjByVehicleID = dealsService.getDealListByVehicleIDList(transporterID);
			ModelAndView modelAndView = new ModelAndView("transporter/transporterDealUpdateAndDelete");
			modelAndView.addObject("alldealObj", dealsListObjByVehicleID);
			return modelAndView;
		} else {
			ModelAndView modelAndView = new ModelAndView("others/LogoutPage");
			return modelAndView;
		}
	}

	// ----------------Activate Deal------------------------------------------

	@RequestMapping(value = "activateDeal")
	public String activateDeal(@RequestParam("dealId") int dealId, HttpServletRequest request) {

		if (request.getSession().getAttribute("TransporterID") != null) {
			dealsService.dealActivateAndDeactivate(dealId, "activatedeal");
			return "redirect:/transporterDealUpdateAndDelete";
		} else
			return "others/LogoutPage";
	}

	// ---------------Deactivate Deal----------------------------------

	@RequestMapping(value = "deactivateDeal")
	public String deactivateDeal(@RequestParam("dealId") int dealId, HttpServletRequest request) {

		if (request.getSession().getAttribute("TransporterID") != null) {
			dealsService.dealActivateAndDeactivate(dealId, "deactivatedeal");
			return "redirect:/transporterDealUpdateAndDelete";
		} else
			return "others/LogoutPage";
	}

	// ----------------Delete Deal----------------------------------
	@RequestMapping(value = "deleteDeals")
	public String deleteDeals(@RequestParam("dealId") int dealId, HttpServletRequest request) {

		if (request.getSession().getAttribute("TransporterID") != null) {
			dealsService.dealDelete(dealId);
			return "redirect:/transporterDealUpdateAndDelete";
		} else
			return "others/LogoutPage";
	}

	// --------------Update Deals------------------------------

	@RequestMapping(value = "updateDeal")
	public ModelAndView updateDeal(@SessionAttribute("TransporterID") int transporterID,
			@RequestParam("dealId") int dealId, HttpServletRequest request) {

		if (request.getSession().getAttribute("TransporterID") != null) {
			Deals dealObj = dealsService.getDealObjById(dealId);
			List<Vehicle> vehicleID = (List<Vehicle>) dealsService.getVehicleListByTransporterID(transporterID);
			ModelAndView modelAndView = new ModelAndView("transporter/updateDealPostPageToTransporter");
			modelAndView.addObject("dealsObj", dealObj);
			modelAndView.addObject("vehicleID", vehicleID);
			return modelAndView;
		} else {
			ModelAndView modelAndView = new ModelAndView("others/LogoutPage");
			return modelAndView;
		}
	}

}
