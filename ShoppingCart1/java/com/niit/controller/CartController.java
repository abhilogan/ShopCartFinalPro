package com.niit.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.niit.shoppingcart.dao.CartDAO;
import com.niit.shoppingcart.dao.InvoiceDAO;
import com.niit.shoppingcart.dao.ProductDAO;
import com.niit.shoppingcart.dao.UserDAO;
import com.niit.shoppingcart.domain.Cart;
import com.niit.shoppingcart.domain.Product;
import com.niit.shoppingcart.domain.User;
import com.niit.shoppingcart.domain.invoice;

@Controller
public class CartController {

	Logger log = LoggerFactory.getLogger(CartController.class);

	@Autowired
	private CartDAO cartDAO;

	@Autowired
	private Cart cart;

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private invoice invoice;

	@Autowired
	private InvoiceDAO invoiceDAO;

	@Autowired
	private HttpSession httpSession;

	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private Product product;

	@GetMapping("/buy")
	public ModelAndView order() {
		ModelAndView mv = new ModelAndView("home");
		// there should me one update method which takes
		// email id as parameter
		String loggedInUserID = (String) httpSession.getAttribute("loggedInUserID");
		if (cartDAO.update(loggedInUserID)) {
			mv.addObject("successMessage", "Your order placed successfully...");
		} else {
			mv.addObject("errorMessage", "Your order could not placed.   please try after some time.");
		}

		return mv;

	}

	@GetMapping("/product/cart/add/{productID}")
	public ModelAndView addToCartGetMapping(@PathVariable("productID") String productID)

	{
		ModelAndView mv = new ModelAndView("home");
		String loggedInUserID = (String) httpSession.getAttribute("loggedInUserID");
		if (loggedInUserID == null) {
			mv.addObject("errorMessage", "Please login to add any product to cart");
			return mv;
		}

		// get the order details of productDAO.get()
		product = productDAO.get(productID);

		cart.setEmailID(loggedInUserID);
		cart.setPrice(product.getPrice());
		cart.setProductID(productID);
		cart.setProductName(product.getName());
		cart.setQuantity(1);
		cart.setId();// to set random no.

		if (cartDAO.save(cart)) {
			mv.addObject("successMessage", "The product add to cart successfully");
			List<Cart> cartList = cartDAO.list(loggedInUserID);
			httpSession.setAttribute("cartSize", cartList.size());
		} else {
			mv.addObject("errorMessage", "Could not add the product to cart..please try after some time");
		}
		return mv;
	}

	// get my cart details
	@GetMapping("/mycart")
	public ModelAndView getMyCartDetails() {
		log.debug("Starting of the method getMyCartDetails");
		ModelAndView mv = new ModelAndView("home");
		mv.addObject("isUserClickedMyCart", true);
		// it will return all the products which are added to cart
		// ??
		String loggedInUserID = (String) httpSession.getAttribute("loggedInUserID");
		log.info("Logged in user id : " + loggedInUserID);
		if (loggedInUserID == null) {
			mv.addObject("errorMessage", "Please login to see your cart details");
			return mv;
		}
		List<Cart> cartList = cartDAO.list(loggedInUserID);
		mv.addObject("cartList", cartList);
		mv.addObject("cartSize", cartList.size());
		log.debug("no. of products in cart " + cartList.size());
		log.debug("Ending of the method getMyCartDetails");
		return mv;
	}

	@GetMapping("/checkout")
	public ModelAndView checkout() {
		ModelAndView mv = new ModelAndView("home");
		mv.addObject("checkoutClicked", true);
		String loggedInUserID = (String) httpSession.getAttribute("loggedInUserID");
		invoice obj = new invoice();
		obj.setUser(userDAO.get(loggedInUserID));
		httpSession.setAttribute("invoice", obj);
		mv.addObject("invoice", obj);
		List<Cart> carts = cartDAO.list(loggedInUserID);
		httpSession.setAttribute("carts", carts);
		httpSession.setAttribute("cartsize", carts.size());

		return mv;

	}
	
	
	@PostMapping("/delete/From/Cart")
	public ModelAndView deleteFromCart(@RequestParam("id") int id) {
		log.debug("Starting of the method removeProductFromCart");
		ModelAndView mv = new ModelAndView("redirect:/mycart");
		String loggedInUserID = (String) httpSession.getAttribute("loggedInUserID");
		cartDAO.delete(id);
		List<Cart> cartList = cartDAO.list(loggedInUserID);
		int cartSize = cartList.size();
		httpSession.setAttribute("cartSize", cartSize);
		mv.addObject("cartList", cartList);
		return mv;
	}
	
	@PostMapping("/edit/cartqtym/{id}")
	public ModelAndView editProductQuantityminus(@PathVariable("id") int id)
	{
		log.debug("Starting of the method editProductQuantity");
		ModelAndView mv= new ModelAndView("redirect:/mycart");
		cart=cartDAO.get(id);
		int qty = cart.getQuantity();
		if(qty==1)
		{
			return mv;
		}
		qty = qty-1;
		/*cart.setTotal(cart.getPrice()*qty);*/
		cart.setQuantity(qty);
		cartDAO.update(cart);
		log.debug("Ending of the method editProductQuantity");
		return mv;
	}
	
	@PostMapping("/edit/cartqty/{id}")
	public ModelAndView editProductQuantity(@PathVariable("id") int id)
	{
		log.debug("Starting of the method editProductQuantity");
		ModelAndView mv= new ModelAndView("redirect:/mycart");
		cart=cartDAO.get(id);
		int qty = cart.getQuantity();
		qty = qty+1;
		/*cart.setSubtotal(cart.getPrice()*qty);*/
		cart.setQuantity(qty);
		cartDAO.update(cart);
		log.debug("Ending of the method editProductQuantity");
		return mv;
	}
	

	@PostMapping("/invoice")
	public ModelAndView invoice(@ModelAttribute("invoice") invoice obj, @RequestParam("country") String country,
			@RequestParam("state") String state, @RequestParam("pincode") String pincode) {
		ModelAndView mv = new ModelAndView("home");
		obj.setBillTo(obj.getBillTo() + "," + country + "," + state + "," + pincode);
		obj.setShippedTo(obj.getShippedTo() + "," + country + "," + state + "," + pincode);
		obj.setOrderDate(new Date());
		invoiceDAO.save(obj);
		mv.addObject("invoiceClicked", true);
		String loggedInUserID = (String) httpSession.getAttribute("loggedInUserID");
		User user = userDAO.get(loggedInUserID);
		List<Cart> carts = cartDAO.list(loggedInUserID);
		mv.addObject("invoice", obj);
		httpSession.setAttribute("billedUser", user);
		httpSession.setAttribute("carts", carts);
		return mv;

	}

}
