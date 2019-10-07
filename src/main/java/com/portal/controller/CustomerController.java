package com.portal.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.portal.entity.Customer;
import com.portal.entity.Deals;
import com.portal.entity.Query;
import com.portal.service.CustomerService;
import com.portal.service.DealsService;

@Controller
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private DealsService dealsService;
	// -----------Show Customer Home Page----------------------------------

	@RequestMapping("HomeCustomer")
	public String homeCustomer(HttpServletRequest request) {
		if (request.getSession().getAttribute("CustomerID") == null) {
			return "LoginPage";
		} else
			return "customer/HomeCustomer";
	}

	// -------------Show All Deals to Customer-------------------------------

	@RequestMapping(value = "ShowAllDeals")
	public ModelAndView showAllDeals() {
		Set<Deals> dealListObj = customerService.getAllDealsObj();
		ModelAndView modelAndView = new ModelAndView("customer/ShowAllDeals");
		modelAndView.addObject("dealsListObj", dealListObj);
		return modelAndView;
	}

	// ---------------Customer Query Page------------------------------

	@RequestMapping(value = "customerQueryPage")
	public ModelAndView customerQueryPage(@SessionAttribute("CustomerID") int customerID,
			@RequestParam("dealId") int dealId) {
		int transporterId = dealsService.getDealObjById(dealId).getTransporter().getTransporterId();
		List<Query> customerAllQuery = customerService.getCustomerQueryListById(customerID);

		ModelAndView modelAndView = new ModelAndView("customer/customerQueryPage");
		Query queryObj = new Query();
		modelAndView.addObject("queryObj", queryObj);
		modelAndView.addObject("dealId", dealId);
		modelAndView.addObject("transporterId", transporterId);
		modelAndView.addObject("queryListObj", customerAllQuery);
		return modelAndView;
	}

	// --------------Save Customer Query in database--------------------

	@RequestMapping(value = "saveCustomerQuery")
	public String saveCustomerQuery(@SessionAttribute("CustomerID") int customerID,
			@RequestParam("dealId") int dealId, @RequestParam("transporterId") int transporterId,
			@ModelAttribute("queryObj") Query queryObj) {
		customerService.saveCustomerQuery(customerID, transporterId, dealId, queryObj);
		return "redirect:/customerQueryPage?dealId="+dealId;
	}

	// ---------------Without Booking Deal --> Redirect to Review Page-----------

	@RequestMapping(value = "BookDeal")
	public ModelAndView bookDeal(@RequestParam("dealId") int dealId) {
		Deals dealObj = dealsService.getDealObjById(dealId);
		ModelAndView modelAndView = new ModelAndView("customer/ReviewPage");
		modelAndView.addObject("dealObj", dealObj);
		return modelAndView;
	}

	// -------------Show SignUp From------------------

	@RequestMapping("showNewCutomerForm")
	public ModelAndView showNewCutomerForm() {
		ModelAndView modelAndView = new ModelAndView("customer/CustomerEntry");
		Customer customerObj = new Customer();
		modelAndView.addObject("customerObj", customerObj);
		return modelAndView;
	}
	// ---------------saveReviewGivenByCustomer------------

	@RequestMapping("saveReviewGivenByCustomer")
	public String saveReviewGivenByCustomer(@RequestParam("ratingValue") int rating,
			@SessionAttribute("CustomerID") int customerID, @RequestParam("dealId") int dealId) {
		System.out.println(customerID + " " + dealId);
		dealsService.setDealsRating(customerID, dealId, rating);
		return "redirect:/HomeCustomer";
	}

	/*
	 * @RequestMapping("saveCustomerProcess") public ModelAndView
	 * saveCustomerProcess(@Valid @ModelAttribute("customerObj") Customer customer,
	 * BindingResult result) { if(result.hasErrors()) { ModelAndView modelAndView =
	 * new ModelAndView("CustomerEntry"); return modelAndView; } else {
	 * customerService.saveCustomerObj(customer); ModelAndView modelAndView = new
	 * ModelAndView("CustomerEntry"); modelAndView.addObject("customerObj",
	 * customer); return modelAndView; } }
	 */

	@RequestMapping("saveCustomerProcess")
	public ModelAndView saveCustomerProcess(@ModelAttribute("customerObj") Customer customer) {
		customerService.saveCustomerObj(customer);
		ModelAndView modelAndView = new ModelAndView("LoginPage");
		// modelAndView.addObject("customerObj", customer);
		return modelAndView;
	}

	// ----------------------Update Customer Profile--------------------------

	@RequestMapping(value = "CustomerUpdateProfilePage")
	public ModelAndView customerUpdateProfilePage(@SessionAttribute("CustomerID") int cid) {
		Customer customerObj = customerService.getCustomerObjById(cid);
		ModelAndView modelAndView = new ModelAndView("customer/CustomerUpdateProfilePage");
		modelAndView.addObject("customerObj", customerObj);
		return modelAndView;
	}

	/*
	 * @ExceptionHandler(value = javax.persistence.PersistenceException.class)
	 * 
	 * public String handleException(javax.persistence.PersistenceException e) {
	 * System.out.println("Unkown Exception Occured: " + e); return
	 * "others/CustomerException"; }
	 */

}
