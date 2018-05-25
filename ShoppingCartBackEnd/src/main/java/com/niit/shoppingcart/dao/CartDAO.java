package com.niit.shoppingcart.dao;

import java.util.List;

import com.niit.shoppingcart.domain.Cart;


//DAO-> Data Access Object
public interface CartDAO 
{
	//declare the methods
	
	//create the new cart
	
	public boolean save(Cart cart);
	
	//update the existing cart
	
	public boolean update(Cart cart);
	
	//related to order
	
	public boolean update(String emailID);
	
	//get the cart details
	
	public Cart get(int id);
	

	//delete the product
	
	public boolean delete(int id);
	
	
	//to get all the carts added by a particular user
	public  List<Cart>  list(String emailID);
	
	
	// delete all cart items which added by particular user
	public boolean deleteCart(String emailID);
	

	

}
