package com.niit.shoppingcart;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.shoppingcart.dao.SupplierDAO;
import com.niit.shoppingcart.domain.Supplier;

public class SupplierDAOTestCase {

	private static AnnotationConfigApplicationContext context;
	
	@Autowired
	private static SupplierDAO supplierDAO;
	
	@Autowired
	private static Supplier supplier;
	
	@BeforeClass// when my update is not working i delete @BeforeClass and re write it.Know my update query is working
	public static void init()
	{
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");//scan the complete package including sub packages
		//@component , @controller , @Repository , @Service
		context.refresh();//clear the context (bean factory and recreate all the
		//instances of the classes which are there in com.niit
		//with proper annotations.)		
		supplierDAO = (SupplierDAO) context.getBean("supplierDAO");//ask the context to get instance of USerDAO
		supplier = (Supplier) context.getBean("supplier");

	}
	
	
	@Test
	public void saveSupplierTestCase()
	{
		supplier = new Supplier();
		supplier.setId("SUP-001");
		supplier.setName("BigC");
		supplier.setAddress("NH22,Chennai");
		boolean status = supplierDAO.save(supplier);
		
		assertEquals("save supplier test case", true, status);
	}
	
	@Test
	public void updateSupplierTestCase()
	{
		supplier.setId("SUP-001");
		supplier.setName("Abhishek Verma");
		supplier.setAddress("Indore,MP");
		boolean status = supplierDAO.upadte(supplier);
		
		assertEquals(" update supplier test case", true , status);
		
	}
	
	@Test
	public void getSupplierSuccessTestCase()
	{
		supplier  = supplierDAO.get("SUP-001");
		
		assertNotNull("get supplier test case", supplier);
	}
	
	@Test
	public void getSupplierfaliurTestCase()
	{
		supplier  = supplierDAO.get("SUP-001");
		
		assertNull("get supplier test case", supplier);
	}
	

	@Test
	public void deleteSuppliersuccessTestCase()
	{
		boolean status = supplierDAO.delete("SUP-001");
		
		assertEquals("delete supplier sucess test case", true, status);
		
	}
	
	@Test
	public void deletefailureSupplierTestCase()
	{
		boolean status = supplierDAO.delete("SUP-001");
		
		assertEquals("delete supplier sucess test case", false, status);
		
	}
	
	@Test
	public void getAllSupplierTestCase()
	{
		List<Supplier> suppliers = supplierDAO.list();
		assertEquals("get all suppliers" , 2 , suppliers.size() );
		
	}
	
	
	
	
	
}
