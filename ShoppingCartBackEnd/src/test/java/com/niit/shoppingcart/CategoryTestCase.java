package com.niit.shoppingcart;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.shoppingcart.dao.CategoryDAO;
import com.niit.shoppingcart.domain.Category;

public class CategoryTestCase {

	private static AnnotationConfigApplicationContext context;
	
	@Autowired
	private static CategoryDAO categoryDAO;
	
	@Autowired
	private static Category category;
	
	@BeforeClass// when my update is not working i delete @BeforeClass and re write it.Know my update query is working
	public static void init()
	{
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");//scan the complete package including sub packages
		//@component , @controller , @Repository , @Service
		context.refresh();//clear the context (bean factory and recreate all the
		//instances of the classes which are there in com.niit
		//with proper annotations.)		
		categoryDAO = (CategoryDAO) context.getBean("categoryDAO");//ask the context to get instance of USerDAO
		category = (Category) context.getBean("category");

	}
	
	
	@Test
	public void saveCategoryTestCase()
	{
		category = new Category();
		category.setId("Books-001");
		category.setName("Book");
		category.setDescription("This is Books category");
		
		boolean status = categoryDAO.save(category);
		
		assertEquals("save category test case", true, status);
	}
	
	@Test
	public void updateCategoryTestCase()
	{
		category.setId("Mob-001");
		category.setName("Mobile");
		category.setDescription("This is new Books category");
		boolean status = categoryDAO.upadte(category);
		assertEquals("update test case", true,status);
	}
	
	@Test
	public void getCategorySuccessTestCase()
	{
		category  = categoryDAO.get("hachiko1@gmail.com");
		
		assertNotNull("get category test case", category);
	}
	
	@Test
	public void getCategoryfaliurTestCase()
	{
		category  = categoryDAO.get("jaya@gmail.com");
		
		assertNull("get category test case", category);
	}
	
	@Ignore
	@Test
	public void deleteCategorysuccessTestCase()
	{
		boolean status = categoryDAO.delete("hachiko@gmail.com");
		
		assertEquals("delete category sucess test case", true, status);
		
	}
	
	@Test
	public void deletefailureCategoryTestCase()
	{
		boolean status = categoryDAO.delete("arpit@gmail.com");
		
		assertEquals("delete category sucess test case", false, status);
		
	}
	
	@Test
	public void getAllCategoryTestCase()
	{
		List<Category> categorys = categoryDAO.list();
		assertEquals("get all categorys" , 5 , categorys.size() );
		
	}
	

	
	
	
}
