package com.niit.shoppingcart;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.shoppingcart.dao.ProductDAO;
import com.niit.shoppingcart.domain.Product;

public class ProductDAOTestCase {

	private static AnnotationConfigApplicationContext context;
	
	@Autowired
	private static ProductDAO productDAO;
	
	@Autowired
	private static Product product;
	
	@BeforeClass// when my update is not working i delete @BeforeClass and re write it.Know my update query is working
	public static void init()
	{
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");//scan the complete package including sub packages
		//@component , @controller , @Repository , @Service
		context.refresh();//clear the context (bean factory and recreate all the
		//instances of the classes which are there in com.niit
		//with proper annotations.)		
		productDAO = (ProductDAO) context.getBean("productDAO");//ask the context to get instance of USerDAO
		product = (Product) context.getBean("product");

	}
	
	
	@Test
	public void saveProductTestCase()
	{
		product = new Product();
		product.setId("Lenevo-001");
		product.setName("Lenevo -- prodcut");
		product.setDescription("This is Lenevo product");
		product.setCategoryId("Mob-001");
		
	  boolean status = 	productDAO.save(product);
	  
	  assertEquals("save product test case", true, status);
	}
	
	
	@Test
	public void updateProductTestCase()
	{
		product.setId("Lenevo-001");
		product.setName("Lenevo -- prodcut");
		product.setDescription("This is Lenevo product");
		boolean status = productDAO.upadte(product);
		assertEquals("update test case", true,status );
	}
	
	@Test
	public void getProductSuccessTestCase()
	{
		
	product= productDAO.get("Lenevo-001");
	
	assertNotNull("get product test case", product);
	}
	
	@Test
	public void getProductFailureTestCase()
	{
		
	product= productDAO.get("Lenevo-001");
	
	assertNull("get product test case", product);
	}
	
	@Test
	public void deleteProductSuccessTestCase()
	{
	boolean status =	productDAO.delete("Lenevo-001");
	assertEquals("delete product succss test case" , true, status);
	
	}
	
	@Test
	public void deleteProductFailureTestCase()
	{
	boolean status =	productDAO.delete("Lenevo-001");
	assertEquals("delete product failure test case" , false, status);
	
	}
	
	
	@Test
	public void getAllProductsTestCase()
	{
	List<Product>	products = productDAO.list();
	
	assertEquals("get all products " , 3, products.size() );
	
	}
	
	
	
	
}
