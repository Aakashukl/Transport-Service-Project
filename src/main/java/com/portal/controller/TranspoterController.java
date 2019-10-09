package com.portal.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.portal.entity.Query;
import com.portal.entity.Transporter;
import com.portal.service.CustomerService;
import com.portal.service.TranspoterService;

@Controller
public class TranspoterController {

	@Autowired
	private TranspoterService transportService;

	@Autowired
	private CustomerService customerService;

	// --------------Transporter Home Page-------------------------------
	@RequestMapping("HomeTransporter")
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

	// ---------------------Show New Transporter Form----------------------
	@RequestMapping("showNewTranspoterForm")
	public ModelAndView showNewCutomerForm() {
		ModelAndView modelAndView = new ModelAndView("TranspoterEntry");
		Transporter transporterObj = new Transporter();
		modelAndView.addObject("transporterObj", transporterObj);
		return modelAndView;
	}

	// -----------------Customer Query Response------------------------
	@RequestMapping(value = "customerQueryResponse")
	public ModelAndView customerQueryResponse(@SessionAttribute("TransporterID") int transporterID,
			HttpServletRequest request) {

		if (request.getSession().getAttribute("TransporterID") != null) {
			List<Query> listOfAllCustomerQuery = transportService.getCustomerAllQueryList(transporterID);
			ModelAndView modelAndView = new ModelAndView("transporter/customerQueryResponse");
			modelAndView.addObject("listOfAllCustomerQuery", listOfAllCustomerQuery);
			return modelAndView;
		} else {
			ModelAndView modelAndView = new ModelAndView("others/LogoutPage");
			return modelAndView;
		}
	}

	// ----------------Saving Transporter Response-------------
	@RequestMapping(value = "saveTransporterResponse")
	public String saveTransporterResponse(@RequestParam("queryId") int queryId,
			@RequestParam("transporterResponce") String transporterResponce,
			@SessionAttribute("TransporterID") int transporterID, HttpServletRequest request) {

		if (request.getSession().getAttribute("TransporterID") != null) {
			transportService.saveResponseOfTransporter(queryId, transporterResponce);
			return "redirect:/customerQueryResponse";
		} else {
			return "redirect:/HomeTransporter";
		}
	}

	// ----------------Save Transport Process-------------------
	@RequestMapping(value = "saveTranspoterProcess", method = RequestMethod.POST)
	public ModelAndView saveTransportProcess(@RequestParam("PANPath") MultipartFile PanCard,
			@Valid @ModelAttribute("transporterObj") Transporter transporter, BindingResult result) throws Exception {
		if(result.hasErrors()) {
			ModelAndView mv = new ModelAndView("TranspoterEntry");
			return mv;
		}

		transportService.saveTranspoterObj(transporter, PanCard);
		ModelAndView modelAndView = new ModelAndView("others/LoginPage");
		Transporter transporter2 = new Transporter();
		modelAndView.addObject("transporterObj", transporter2);
		return modelAndView;

	}

	// --------------------Transporter Profile Update Page-----------------

	@RequestMapping(value = "TransporterProfileUpdatePage")
	public ModelAndView transporterProfileUpdatePage(@SessionAttribute("TransporterID") int transporterID,
			HttpServletRequest request) {

		if (request.getSession().getAttribute("TransporterID") != null) {
			Transporter transporterObj = transportService.getTransporterObjByID(transporterID);
			ModelAndView modelAndView = new ModelAndView("transporter/TransporterProfileUpdatePage");
			transporterObj.setTransporterPassword("");
			modelAndView.addObject("transporterObj", transporterObj);
			return modelAndView;

		} else {
			ModelAndView modelAndView = new ModelAndView("others/LogoutPage");
			return modelAndView;
		}

	}

	// ----------------------Save Transporter Updated Record----------------
	@RequestMapping(value = "updateTranspoterProcess")
	public ModelAndView updateTranspoterProcess(@RequestParam("PANPath") MultipartFile PanCard,
			@ModelAttribute("transporterObj") Transporter transporterUpdated,
			@SessionAttribute("TransporterID") int transporterID, HttpServletRequest request) throws Exception {
		if (request.getSession().getAttribute("TransporterID") != null) {
			transportService.saveTranspoterObj(transporterUpdated, PanCard);
			ModelAndView modelAndView = new ModelAndView("transporter/HomeTransporter");
			return modelAndView;
		} else {
			ModelAndView modelAndView = new ModelAndView("others/LogoutPage");
			return modelAndView;
		}
	}

	// ---------------Index Out Of Bounds Exception--------------------
	@ExceptionHandler(java.lang.IndexOutOfBoundsException.class)
	public ModelAndView indexOutOfBoundsException(java.lang.IndexOutOfBoundsException ex) {
		System.out.println(" parameter is missing");
		ModelAndView modelAndView = new ModelAndView("others/LogoutPage");
		return modelAndView;
	}

	// ---------------IOException Exception--------------------
	@ExceptionHandler(IOException.class)
	public ModelAndView iOException(IOException ex) {
		System.out.println(" parameter is missing");
		ModelAndView modelAndView = new ModelAndView("others/LogoutPage");
		return modelAndView;
	}

	// ---------------javax.validation.ConstraintViolationException--------------------
	@ExceptionHandler(javax.validation.ConstraintViolationException.class)
	public ModelAndView constraintViolationException(javax.validation.ConstraintViolationException ex) {
		System.out.println(ex);
		ModelAndView modelAndView = new ModelAndView("others/LogoutPage");
		return modelAndView;
	}

	// ---------------Missing Servlet Request Parameter Exception-----
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ModelAndView handleMissingParams(MissingServletRequestParameterException ex) {
		String name = ex.getParameterName();
		System.out.println(name + " parameter is missing");
		ModelAndView modelAndView = new ModelAndView("others/LogoutPage");
		return modelAndView;
	}

}
