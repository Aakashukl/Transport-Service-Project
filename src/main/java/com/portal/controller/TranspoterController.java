package com.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.portal.entity.Transporter;
import com.portal.service.TranspoterService;

@Controller
public class TranspoterController {

	@Autowired
	private TranspoterService transportService;

	@RequestMapping("HomeTransporter")
	public String homeTransporter() {
		return "HomeTransporter";
	}
	@RequestMapping("showNewTranspoterForm")
	public ModelAndView showNewCutomerForm() {
		ModelAndView modelAndView = new ModelAndView("TranspoterEntry");
		Transporter transporterObj = new Transporter();
		modelAndView.addObject("transporterObj", transporterObj);
		return modelAndView;
	}

	@RequestMapping(value = "saveTranspoterProcess", method = RequestMethod.POST)
	public ModelAndView saveTransportProcess(@RequestParam("PANPath") MultipartFile PanCard,
			@ModelAttribute("transporterObj") Transporter transporter) throws Exception {
		//System.out.println(transporter.getTransporterId());

		transportService.saveTranspoterObj(transporter, PanCard);
		ModelAndView modelAndView = new ModelAndView("TranspoterEntry");
		Transporter transporter2 = new Transporter();
		modelAndView.addObject("transporterObj", transporter2);
		return modelAndView;

	}
	
	@RequestMapping(value = "TransporterProfileUpdatePage")
	public String transporterProfileUpdatePage() {
		ModelAndView modelAndView = new ModelAndView("TransporterProfileUpdatePage");
		return "TransporterProfileUpdatePage";
	} 

}