package com.niit.shoppingcart.dao;

import java.util.List;

import com.niit.shoppingcart.domain.User;


//DAO-> Data Access Object
public interface UserDAO 
{
	//declare the methods
	
	//create the new user
	
	public boolean save(User user);
	
	//update the existing user
	
	public boolean upadte(User user);
	
	//get the user details
	
	public User get(String emailID);
	
	//delete the user
	
	public boolean delete(String emailID);
	
	//to get all the users
	public  List<User>  list();
	
	//validate weather the credentials are correct or not
	
	public User validate(String emailID , String password);
	
	//we may required more methods .... will discuss
	
	
	
	
	

}
