package com.niit.shoppingcart.dao;

import java.util.List;

import com.niit.shoppingcart.domain.Supplier;


//DAO-> Data Access Object
public interface SupplierDAO 
{
	//declare the methods
	
	//create the new supplier
	
	public boolean save(Supplier supplier);
	
	//update the existing supplier
	
	public boolean upadte(Supplier supplier);
	
	//get the supplier details
	
	public Supplier get(String id);
	
	//delete the supplier
	
	public boolean delete(String id);
	
	//to get all the suppliers
	public  List<Supplier>  list();
	
	//validate weather the credentials are correct or not
	

	

}
