package com.niit.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.niit.shoppingcart.dao.CategoryDAO;
import com.niit.shoppingcart.dao.ProductDAO;
import com.niit.shoppingcart.dao.SupplierDAO;
import com.niit.shoppingcart.domain.Category;
import com.niit.shoppingcart.domain.Product;
import com.niit.shoppingcart.domain.Supplier;

@Controller
public class AdminController {
	private static Logger log = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	private CategoryDAO categoryDAO;

	@Autowired
	private SupplierDAO supplierDAO;
	
	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private Category category;
	
	@Autowired
	private Supplier supplier;
	
	@Autowired
	private Product product;
	
	
	@Autowired
	HttpSession httpSession;

	@GetMapping("/managecategories")
	public ModelAndView adminClickedCategories() {
		ModelAndView mv = new ModelAndView("home");
		//1- check weather user is logged or not
		String loggedInUserID =(String) httpSession.getAttribute("loggedInUserID");
		
		if(loggedInUserID==null)
		{
			mv.addObject("errorMessage", "Please login to do this operation");
			return mv;
		}
		//if not logged,"please logging to do this operation"
		
		//2- the already logged in
		//check what is role or user
		Boolean isAdmin = (Boolean)httpSession.getAttribute("isAdmin");
		
		//if the role of the user in not admin
		//"you are not authorized to do this operation "
		if(isAdmin==null || isAdmin==false)
		{
			mv.addObject("errorMessage","You are not autheroized to do this operation");
			return mv;
		}
		
		
		
		log.debug("starting of the method admincClickedCategories");
		mv.addObject("isAdminClickedManageCategories", true);
		// fetch all the categories again
		List<Category> categories = categoryDAO.list();
		// and set to http session.
		httpSession.setAttribute("categories", categories);
		log.debug("ending of the method admincClickedCategories");

		return mv;

	}

	@GetMapping("/managesuppliers")
	public ModelAndView adminClickedSupllier() {
		ModelAndView mv = new ModelAndView("home");
		log.debug("starting of the method admincClickedSupplier");
		mv.addObject("isAdminClickedManageSuppliers", true);
		List<Supplier> suppliers = supplierDAO.list();
		httpSession.setAttribute("suppliers", suppliers);
		log.debug("ending of the method admincClickedSupplier");

		//httpSession.setAttribute("suppliers", suppliers);
		return mv;

	}

	@GetMapping("/manageproducts")
	public ModelAndView adminClickedProducts() {
		ModelAndView mv = new ModelAndView("home");
		log.debug("starting of the method admincClickedProducts");
		mv.addObject("isAdminClickedManageProducts", true);
		//we supposed to fetch all categories and supplier 
		//and set it to httpSession
		List<Category> categories = categoryDAO.list();
		List<Supplier> suppliers = supplierDAO.list();
		List<Product> products = productDAO.list();
		
		httpSession.setAttribute("categories", categories);
		httpSession.setAttribute("suppliers", suppliers);
		httpSession.setAttribute("products", products);
		log.debug("ending of the method admincClickedProducts");
		return mv;

	}

}
