package com.niit.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.niit.shoppingcart.dao.CartDAO;
import com.niit.shoppingcart.dao.CategoryDAO;
import com.niit.shoppingcart.dao.ProductDAO;
import com.niit.shoppingcart.dao.UserDAO;
import com.niit.shoppingcart.domain.Cart;
import com.niit.shoppingcart.domain.Category;
import com.niit.shoppingcart.domain.Product;
import com.niit.shoppingcart.domain.User;

@Controller
public class HomeController {
	@Autowired
	private CategoryDAO categoryDAO;

	@Autowired
	private Category category;

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private Cart cart;

	@Autowired
	private CartDAO cartDAO;

	@Autowired
	private Product product;

	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private HttpSession httpSession;

	// C:\Users\lenovo\eclipse-workspace\LoginApp2\ShoppingCart1\src\main\webapp\resources\images
	// private static String imageDirectory = "resources" +File.separator +"images";

	private static String imageDirectory = "C:\\Users\\lenovo\\eclipse-workspace\\LoginApp2\\ShoppingCart1\\src\\main\\webapp\\resources\\images";

	@GetMapping("/")
	public ModelAndView home(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("home");
		// we need to fetch all the categories
		// Autowired CategoryDAO and categories

		List<Category> categories = categoryDAO.list();

		// mv.addObject("categories", categories);
		httpSession.setAttribute("categories", categories);
		httpSession.setAttribute("imageDirectory", imageDirectory);
		String p = request.getContextPath();
		// String path =httpSession.getServletContext().getContextPath();
		return mv;

	}

	@GetMapping("/login")
	public ModelAndView test2(@RequestParam(name = "logout", required = false) String msg) {
		ModelAndView mv = new ModelAndView("home");
		if (msg != null) {
			mv.addObject("logoutMessage", "You are successfully logged out");
		}
		mv.addObject("isUserClickedLogin", true);

		return mv;

	}

	@GetMapping("/logout")
	public ModelAndView logout() {
		// at the time of login, we add user id in http session
		// at the time of logout, we need to remove usre id from http session.
		ModelAndView mv = new ModelAndView("home");

		// we were not able to see menu items after logout
		// will modify this code.
		httpSession.invalidate();

		/*
		 * httpSession.removeAttribute("loggedInUserID");
		 * httpSession.removeAttribute("isLoggedIn");
		 * httpSession.removeAttribute("isAdmin");
		 */

		mv.addObject("logoutMessage", "You are successfully logged out");
		return mv;

	}

	@GetMapping("/registration")
	public ModelAndView test3() {
		ModelAndView mv = new ModelAndView("home");
		mv.addObject("isUserClickedRegister", true);
		mv.addObject("user", new User());

		return mv;

	}

	@PostMapping("/registration")
	public ModelAndView test4(@ModelAttribute("user") User user) {
		ModelAndView mv = new ModelAndView("home");
		if (userDAO.save(user)) {
			mv.addObject("isUserClickedLogin", true);
		} else {
			mv.addObject("isUserClickedRegister", true);
			mv.addObject("user", new User());
		}

		return mv;

	}

	@GetMapping("/continueShopping")
	public ModelAndView hom() {
		ModelAndView mv = new ModelAndView("home");
		List<Category> categories = categoryDAO.list();
		List<Product> products = productDAO.list();
		mv.addObject("categories", categories);
		mv.addObject("products", products);
		String loggedInUserID = (String) httpSession.getAttribute("loggedInUserID");
		List<Cart> cartList = cartDAO.list(loggedInUserID);
		int cartSize = cartList.size();
		httpSession.setAttribute("cartSize", cartSize);
		return mv;
	}

}
