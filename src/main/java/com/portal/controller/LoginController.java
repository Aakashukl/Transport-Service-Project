package com.portal.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.portal.entity.Customer;
import com.portal.entity.Transporter;
import com.portal.service.AdminService;
import com.portal.service.CustomerService;
import com.portal.service.TranspoterService;

@Controller
@SessionAttributes({ "CustomerID", "TransporterID", "AdminUsername" })
public class LoginController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private TranspoterService transpoterService;

	@Autowired
	private AdminService adminService;

	// ----------Show All Login Forms Link---------------

	@RequestMapping("ShowLoginPage")
	public ModelAndView showLoginForm() {
		ModelAndView modelAndView = new ModelAndView("others/LoginPage");
		return modelAndView;
	}

	// ----------Show Customer Login Page Link-----------

	@RequestMapping("ShowCustomerLoginPage")
	public ModelAndView showCustomerLoginPage() {
		ModelAndView modelAndView = new ModelAndView("customer/CustomerLoginPage");
		Customer customerObj = new Customer();
		modelAndView.addObject("customerObj", customerObj);
		return modelAndView;
	}

	// ----------Show Transporter Login Page Link------------

	@RequestMapping("ShowTransporterLoginPage")
	public ModelAndView showTransporterLoginPage() {
		ModelAndView modelAndView = new ModelAndView("transporter/TransporterLoginPage");
		Transporter transporterObj = new Transporter();
		modelAndView.addObject("transporterObj", transporterObj);
		return modelAndView;
	}

	// ---------Show Admin Login Page Link-------------
	@RequestMapping("ShowAdminLoginPage")
	public ModelAndView showAdminLoginPage() {
		ModelAndView modelAndView = new ModelAndView("admin/AdminLoginPage");
		return modelAndView;
	}

	// --------Transporter Login Page Check------------

	@RequestMapping("loginTranspoterProcess")
	public ModelAndView loginTranspoterProcess(@ModelAttribute("transporterObj") Transporter transporterObj) {
		Transporter transporterObject = transpoterService.login(transporterObj);
		if (transporterObject.getTransporterId() > 0) {
			ModelAndView modelAndView = new ModelAndView("transporter/HomeTransporter");
			modelAndView.addObject("TransporterID", transporterObject.getTransporterId());
			return modelAndView;
		} else {
			ModelAndView modelAndView = new ModelAndView("transporter/TransporterLoginPage");
			modelAndView.addObject("transporterObj", transporterObj);
			return modelAndView;
		}
	}

	// --------Customer Login PageCheck--------------

	@RequestMapping("loginCustomerProcess")
	public ModelAndView loginCustomerProcess(@ModelAttribute("customerObj") Customer customerObj) {
		// public ModelAndView loginCustomerProcess(@RequestParam String username,
		// @RequestParam String password) {

		// if (customerService.login(username, password)) {
		Customer customerObject = customerService.login(customerObj);
		if (customerObject.getCustomerId() > 0) {
			ModelAndView modelAndView = new ModelAndView("customer/HomeCustomer");
			modelAndView.addObject("CustomerID", customerObject.getCustomerId());
			return modelAndView;
		} else {
			ModelAndView modelAndView = new ModelAndView("customer/CustomerLoginPage");
			return modelAndView;
		}
	}

	// ------Admin Login Page Check----------------------------

	@RequestMapping("loginAdminProcess")
	public ModelAndView loginAdminProcess(@RequestParam String AdminUsername, @RequestParam String AdminPassword) {
		if (adminService.checkAdminUsernamePassword(AdminUsername, AdminPassword)) {
			ModelAndView modelAndView = new ModelAndView("admin/HomeAdmin");
			modelAndView.addObject("AdminUsername", AdminUsername);
			return modelAndView;
		} else {
			ModelAndView modelAndView = new ModelAndView("admin/AdminLoginPage");
			return modelAndView;
		}
	}

	// -----------------Logout----------------------------------
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, SessionStatus session) {
		session.setComplete();
		request.getSession().invalidate();
		return "redirect:/ShowLoginPage";
	}

	// ---------------Missing Servlet Request Parameter Exception-----
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ModelAndView handleMissingParams(MissingServletRequestParameterException ex) {
		String name = ex.getParameterName();
		System.out.println(name + " parameter is missing");
		ModelAndView modelAndView = new ModelAndView("others/LogoutPage");
		return modelAndView;
	}

	// --------- Exception Handling--------------------
	@ExceptionHandler(value = Exception.class)
	public ModelAndView handleException(Exception e) {
		System.out.println("Unkown Exception Occured: " + e);
		ModelAndView modelAndView = new ModelAndView("others/LoginPage");
		return modelAndView;
	}
}
