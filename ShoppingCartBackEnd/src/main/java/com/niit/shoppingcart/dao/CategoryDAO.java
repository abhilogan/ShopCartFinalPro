package com.niit.shoppingcart.dao;

import java.util.List;

import com.niit.shoppingcart.domain.Category;


//DAO-> Data Access Object
public interface CategoryDAO 
{
	//declare the methods
	
	//create the new category
	
	public boolean save(Category category);
	
	//update the existing category
	
	public boolean upadte(Category category);
	
	//get the category details
	
	public Category get(String id);
	

	//delete the product
	
	public boolean delete(String id);
	
	
	//to get all the categorys
	public  List<Category>  list();
	

	

}
