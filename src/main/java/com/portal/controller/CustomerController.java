package com.portal.controller;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
			return "others/LogoutPage";
		} else
			return "customer/HomeCustomer";
	}

	// -------------Show All Deals to Customer-------------------------------

	@RequestMapping("ShowAllDeals")
	public ModelAndView showAllDeals(HttpServletRequest request) {

		if (request.getSession().getAttribute("CustomerID") != null) {
			Set<Deals> dealListObj = customerService.getAllDealsObj();
			ModelAndView modelAndView = new ModelAndView("customer/ShowAllDeals");
			modelAndView.addObject("dealsListObj", dealListObj);
			return modelAndView;
		} else {
			ModelAndView modelAndView = new ModelAndView("others/LogoutPage");
			return modelAndView;
		}
	}
	/*
	 * // ------------------------ Check Filtered-----------------------
	 * 
	 * @RequestMapping(value = "dealsFilter") public String
	 * showAllDeal(@RequestParam("fromCity") String
	 * fromCity, @RequestParam("toCity") String toCity,
	 * 
	 * @RequestParam("fromDate") Date fromDate, @RequestParam("toDate") Date toDate)
	 * { if(!fromCity.equals("null") && !toCity.equals("null")) { return
	 * "redirect:/dealsFilterCity?fromCity="+fromCity+"&&toCity="+toCity; } else
	 * return "redirect:/ShowAllDeals";
	 * 
	 * }
	 */

	// ------------------------ Check Filtered-----------------------

	@RequestMapping(value = "dealsFilter")
	public ModelAndView showAllDeal(@RequestParam("filter") String filters, HttpServletRequest request) {
		if (request.getSession().getAttribute("CustomerID") != null) {
			if (filters.equals("alldealsFilter")) {
				Set<Deals> dealListObj = customerService.getAllDealsObj();
				ModelAndView modelAndView = new ModelAndView("customer/allDealsFilter");
				modelAndView.addObject("dealsListObj", dealListObj);
				return modelAndView;
			} else if (filters.equals("dealsFilterCity")) {
				Set<Deals> dealListObj = customerService.getAllDealsObj();
				ModelAndView modelAndView = new ModelAndView("customer/dealsFilterCity");
				modelAndView.addObject("dealsListObj", dealListObj);
				return modelAndView;
			} else if (filters.equals("dealsFilterDate")) {
				Set<Deals> dealListObj = customerService.getAllDealsObj();
				ModelAndView modelAndView = new ModelAndView("customer/dealsFilterDate");
				modelAndView.addObject("dealsListObj", dealListObj);
				return modelAndView;
			} else {
				Set<Deals> dealListObj = customerService.getAllDealsObj();
				ModelAndView modelAndView = new ModelAndView("customer/ShowAllDeals");
				modelAndView.addObject("dealsListObj", dealListObj);
				return modelAndView;
			}
		} else {
			ModelAndView modelAndView = new ModelAndView("others/LogoutPage");
			return modelAndView;
		}
	}

	// ------------------------Filtered-----------------------------------------------------
	@RequestMapping(value = "alldealsFilter")
	public ModelAndView showAllDeals(@RequestParam("fromCity") String fromCity, @RequestParam("toCity") String toCity,
			@RequestParam("fromDate") Date fromDate, @RequestParam("toDate") Date toDate, HttpServletRequest request) {
		if (request.getSession().getAttribute("CustomerID") != null) {
			Set<Deals> dealListObj = customerService.getAllDealsObj(fromCity, toCity, fromDate, toDate);
			ModelAndView modelAndView = new ModelAndView("customer/allDealsFilter");
			modelAndView.addObject("dealsListObj", dealListObj);
			return modelAndView;
		} else {
			ModelAndView modelAndView = new ModelAndView("others/LogoutPage");
			return modelAndView;
		}

	}

	// ---------------------From CityA TOCityB---------------------------------

	@RequestMapping("dealsFilterCity")
	public ModelAndView showAllDeals(@RequestParam("fromCity") String fromCity, @RequestParam("toCity") String toCity,
			HttpServletRequest request) {
		if (request.getSession().getAttribute("CustomerID") != null) {
			Set<Deals> dealListObj = customerService.getAllDealsObj(fromCity, toCity);
			ModelAndView modelAndView = new ModelAndView("customer/ShowAllDeals");
			modelAndView.addObject("dealsListObj", dealListObj);
			return modelAndView;
		} else {
			ModelAndView modelAndView = new ModelAndView("others/LogoutPage");
			return modelAndView;
		}
	}

	// -------------------From DateA TO DateB----------------------

	@RequestMapping(value = "dealsFilterDate")
	public ModelAndView showAllDeals(@RequestParam("fromDate") Date fromDate, @RequestParam("toDate") Date toDate,
			HttpServletRequest request) {
		if (request.getSession().getAttribute("CustomerID") != null) {
			Set<Deals> dealListObj = customerService.getAllDealsObj(fromDate, toDate);
			ModelAndView modelAndView = new ModelAndView("customer/ShowAllDeals");
			modelAndView.addObject("dealsListObj", dealListObj);
			return modelAndView;
		} else {
			ModelAndView modelAndView = new ModelAndView("others/LogoutPage");
			return modelAndView;
		}
	}

	/*
	 * // ---------------------From City------------------------------------
	 * 
	 * @RequestMapping(value = "dealsFilter", params = { "fromCity" }) public
	 * ModelAndView showAllDeals(@RequestParam("fromCity") String fromCity) {
	 * Set<Deals> dealListObj = customerService.getAllDealsObj(fromCity);
	 * ModelAndView modelAndView = new ModelAndView("customer/ShowAllDeals");
	 * modelAndView.addObject("dealsListObj", dealListObj); return modelAndView; }
	 */

	/*
	 * // -------------------From DateA ---------------------
	 * 
	 * @RequestMapping("dealsFilter") public ModelAndView showAllDeals(
	 * 
	 * @RequestParam("fromDate") Date fromDate) { Set<Deals> dealListObj =
	 * customerService.getAllDealsObj(fromDate); ModelAndView modelAndView = new
	 * ModelAndView("customer/ShowAllDeals"); modelAndView.addObject("dealsListObj",
	 * dealListObj); return modelAndView; }
	 */

	// ---------------Customer Query Page------------------------------

	@RequestMapping(value = "customerQueryPage")
	public ModelAndView customerQueryPage(@SessionAttribute("CustomerID") int customerID,
			@RequestParam("dealId") int dealId, HttpServletRequest request) {
		if (request.getSession().getAttribute("CustomerID") != null) {
			int transporterId = dealsService.getDealObjById(dealId).getTransporter().getTransporterId();
			List<Query> customerAllQuery = customerService.getCustomerQueryListById(customerID);

			ModelAndView modelAndView = new ModelAndView("customer/customerQueryPage");
			Query queryObj = new Query();
			modelAndView.addObject("queryObj", queryObj);
			modelAndView.addObject("dealId", dealId);
			modelAndView.addObject("transporterId", transporterId);
			modelAndView.addObject("queryListObj", customerAllQuery);
			return modelAndView;
		} else {
			ModelAndView modelAndView = new ModelAndView("others/LogoutPage");
			return modelAndView;
		}
	}

	// --------------Save Customer Query in database--------------------

	@RequestMapping(value = "saveCustomerQuery")
	public String saveCustomerQuery(@SessionAttribute("CustomerID") int customerID, @RequestParam("dealId") int dealId,
			@RequestParam("transporterId") int transporterId, @ModelAttribute("queryObj") Query queryObj,
			HttpServletRequest request) {
		if (request.getSession().getAttribute("CustomerID") != null) {
			customerService.saveCustomerQuery(customerID, transporterId, dealId, queryObj);
			return "redirect:/customerQueryPage?dealId=" + dealId;
		}

		else {
			return "others/LogoutPage";
		}
	}

	// ---------------Without Booking Deal --> Redirect to Review Page-----------

	@RequestMapping(value = "BookDeal")
	public ModelAndView bookDeal(@RequestParam("dealId") int dealId, HttpServletRequest request) {
		if (request.getSession().getAttribute("CustomerID") != null) {
			Deals dealObj = dealsService.getDealObjById(dealId);
			ModelAndView modelAndView = new ModelAndView("customer/ReviewPage");
			modelAndView.addObject("dealObj", dealObj);
			return modelAndView;
		} else {
			ModelAndView modelAndView = new ModelAndView("others/LogoutPage");
			return modelAndView;
		}
	}

	// -------------Show SignUp From------------------

	@RequestMapping("showNewCutomerForm")
	public ModelAndView showNewCutomerForm() {
		ModelAndView modelAndView = new ModelAndView("customer/CustomerEntry");
		Customer customerObj = new Customer();
		modelAndView.addObject("customerObj", customerObj);
		return modelAndView;
	}
	// ---------------save Review Given By Customer------------

	@RequestMapping("saveReviewGivenByCustomer")
	public String saveReviewGivenByCustomer(@RequestParam("ratingValue") int rating,
			@SessionAttribute("CustomerID") int customerID, @RequestParam("dealId") int dealId,
			HttpServletRequest request) {
		try {
			if (request.getSession().getAttribute("CustomerID") != null) {
				System.out.println(customerID + " " + dealId);
				dealsService.setDealsRating(customerID, dealId, rating);
				return "redirect:/HomeCustomer";
			} else
				return "others/LogoutPage";
		} catch (javax.persistence.PersistenceException e) {
			System.out.println(e);
			return "others/alreadyReviewedException";
		}
	}

	// --------------------------------------------------------------
	/*
	 * @RequestMapping("saveCustomerProcess") public ModelAndView
	 * saveCustomerProcess(@Valid @ModelAttribute("customerObj") Customer customer,
	 * BindingResult result) { if(result.hasErrors()) { ModelAndView modelAndView =
	 * new ModelAndView("CustomerEntry"); return modelAndView; } else {
	 * customerService.saveCustomerObj(customer); ModelAndView modelAndView = new
	 * ModelAndView("CustomerEntry"); modelAndView.addObject("customerObj",
	 * customer); return modelAndView; } }
	 */
//------------------Save New Customer Process----------------
	@RequestMapping("saveCustomerProcess")
	public ModelAndView saveCustomerProcess(@Valid @ModelAttribute("customerObj") Customer customer,
			BindingResult result) {
		try {
			if (result.hasErrors()) {
				ModelAndView mv = new ModelAndView("customer/CustomerEntry");
				return mv;
			}
			customerService.saveCustomerObj(customer);
			ModelAndView modelAndView = new ModelAndView("others/LoginPage");
			// modelAndView.addObject("customerObj", customer);
			return modelAndView;
		} catch (javax.persistence.PersistenceException e) {
			ModelAndView modelAndView = new ModelAndView("customer/CustomerEntry");
			return modelAndView;
		}
	}

	// ---------------update Customer Process-----------------------

	@RequestMapping("updateCustomerProcess")
	public ModelAndView updateCustomerProcess(@ModelAttribute("customerObj") Customer customer) {
		try {
			customerService.saveCustomerObj(customer);
			ModelAndView modelAndView = new ModelAndView("customer/HomeCustomer");
			// modelAndView.addObject("customerObj", customer);
			return modelAndView;
		} catch (javax.persistence.PersistenceException e) {
			System.out.println(e);
			ModelAndView modelAndView = new ModelAndView("others/customerUniqueValueException");
			return modelAndView;
		}
	}

	// ----------------------Update Customer Profile--------------------------

	@RequestMapping(value = "CustomerUpdateProfilePage")
	public ModelAndView customerUpdateProfilePage(@SessionAttribute("CustomerID") int cid, HttpServletRequest request) {

		if (request.getSession().getAttribute("CustomerID") != null) {
			Customer customerObj = customerService.getCustomerObjById(cid);
			ModelAndView modelAndView = new ModelAndView("customer/CustomerUpdateProfilePage");
			modelAndView.addObject("customerObj", customerObj);
			return modelAndView;
		} else {
			ModelAndView modelAndView = new ModelAndView("others/LogoutPage");
			return modelAndView;

		}

	}

//-----------------------Handling Exception-----------------------

	@ExceptionHandler(value = Exception.class)
	public ModelAndView handleException(Exception e) {
		System.out.println("Unkown Exception Occured: " + e);
		ModelAndView modelAndView = new ModelAndView("others/CustomerException");
		return modelAndView;
	}

}
