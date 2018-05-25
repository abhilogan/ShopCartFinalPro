package com.niit.shoppingcart.dao;

import java.util.List;

import com.niit.shoppingcart.domain.Product;


//DAO-> Data Access Object
public interface ProductDAO 
{
	//declare the methods
	
	//create the new product
	
	public boolean save(Product product);
	
	//update the existing product
	
	public boolean upadte(Product product);
	
	//get the product details
	
	public Product get(String id);
	
	//delete the product
	
	public boolean delete(String id);
	
	//to get all the products
	public  List<Product>  list();
	
	//validate weather the credentials are correct or not
	
	public List<Product> search(String searchString);
	
	public List<Product> search(String searchString , int maxPrice );
	
	public List<Product> search(String searchString  , int minPrice ,int maxPrice);
	

	

}
