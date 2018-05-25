package com.niit.shoppingcart;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.shoppingcart.dao.UserDAO;
import com.niit.shoppingcart.domain.User;

public class UserDAOTestCase {

	private static AnnotationConfigApplicationContext context;
	
	@Autowired
	private static UserDAO userDAO;
	
	@Autowired
	private static User user;
	
	@BeforeClass// when my update is not working i delete @BeforeClass and re write it.Know my update query is working
	public static void init()
	{
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");//scan the complete package including sub packages
		//@component , @controller , @Repository , @Service
		context.refresh();//clear the context (bean factory and recreate all the
		//instances of the classes which are there in com.niit
		//with proper annotations.)		
		userDAO = (UserDAO) context.getBean("userDAO");//ask the context to get instance of USerDAO
		user = (User) context.getBean("user");

	}
	
	
	@Test
	public void saveUserTestCase()
	{
		user = new User();
		user.setEmailID("kiran@gmail.com");
		user.setMobile("3232323232");
		user.setName("Kiran Kumar");
		user.setPwd("kiran@123");
		
		boolean status = userDAO.save(user);
		
		assertEquals("save user test case", true, status);
	}
	
	@Test
	public void updateUserTestCase()
	{
		user.setEmailID("jaskaran@gmail.com");
		user.setMobile("5555555555");
		boolean status = userDAO.upadte(user);
		assertEquals("update test case", true,status);
	}
	@Ignore
	@Test
	public void getUserSuccessTestCase()
	{
		user  = userDAO.get("hachiko1@gmail.com");
		
		assertNotNull("get user test case", user);
	}
	
	@Ignore
	@Test
	public void getUserfaliurTestCase()
	{
		user  = userDAO.get("jaya@gmail.com");
		
		assertNull("get user test case", user);
	}
	
	@Ignore
	@Test
	public void deleteUsersuccessTestCase()
	{
		boolean status = userDAO.delete("abhishek1@gmail.com");
		
		assertEquals("delete user sucess test case", true, status);
		
	}
	@Ignore
	@Test
	public void deletefailureUserTestCase()
	{
		boolean status = userDAO.delete("arpit@gmail.com");
		
		assertEquals("delete user sucess test case", false, status);
		
	}
	@Ignore
	@Test
	public void getAllUserTestCase()
	{
		List<User> users = userDAO.list();
		assertEquals("get all users" , 12 , users.size() );
		
	}
	@Ignore
	@Test
	public void validateCredentailsTestCase()
	{
		user = userDAO.validate("jaskaran2@gmail.com", "jas@1234");
		assertNotNull("validate test case", user);
		
	}
	
	
	
	
	
	
	
	
	
}
