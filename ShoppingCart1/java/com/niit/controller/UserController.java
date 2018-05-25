package com.niit.controller;

import java.util.List;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.niit.shoppingcart.dao.CartDAO;
import com.niit.shoppingcart.dao.UserDAO;
import com.niit.shoppingcart.domain.Cart;
import com.niit.shoppingcart.domain.User;

@Controller
public class UserController {
	
	//UserDAO - backend project
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private User user;
	
	@Autowired
	private CartDAO cartDAO;
	
	@Autowired
	private Cart cart;
	
	
	@Autowired
	HttpSession httpSession;
	

	@PostMapping("validate")
	public ModelAndView validate(@RequestParam("username") String username, @RequestParam("password") String password)
	{
		ModelAndView mv = new ModelAndView("home");
		
		user = userDAO.validate(username, password);

		if (user==null )
		{
			mv.addObject("errorMessage", "invalid credentials , please try again");
		}
		else
		{
			//valid credentials
			//mv.addObject("WelcomeMessage", "welcome Mr./Ms."+user.getName());
			httpSession.setAttribute("welcomeMessage", "Welcome Mr./Ms " + user.getName());
			httpSession.setAttribute("loggedInUserID", user.getEmailID());
			httpSession.setAttribute("isLoggedIn",true);
			//fetch how many products are added
			//this no. add to cart.
			
			List<Cart> carts = cartDAO.list(user.getEmailID());
			httpSession.setAttribute("size", carts.size());
			
			httpSession.setAttribute("carts", carts);
			
			
		}
		if(user.getRole()=='A')
		{
			httpSession.setAttribute("isAdmin" , true);
		}
		
		return mv;
		
		
		
	}
	
}
